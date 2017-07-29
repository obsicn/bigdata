package spouts;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.storm.Config;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestWordSpout extends BaseRichSpout {
	public static Logger log = LoggerFactory.getLogger(TestWordSpout.class);
	boolean _isDistributed;
	SpoutOutputCollector _collector;

	public TestWordSpout() {
		this(true);
	}

	public TestWordSpout(boolean isDistributed) {
		_isDistributed = isDistributed;
	}

	public void close() {

	}

	@Override
	public void nextTuple() {
		// TODO Auto-generated method stub
		Utils.sleep(100);
		final String[] words = new String[] { "nathan", "mike", "jackson", "golda", "bertels" };
		final Random rand = new Random();
		final String word = words[rand.nextInt(words.length)];
		_collector.emit(new Values(word));

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("word"));

	}

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		// TODO Auto-generated method stub
		_collector = collector;
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		if (!_isDistributed) {
			Map<String, Object> ret = new HashMap<String, Object>();
			ret.put(Config.TOPOLOGY_MAX_TASK_PARALLELISM, 1);
			return ret;
		} else {
			return null;
		}
	}

}
