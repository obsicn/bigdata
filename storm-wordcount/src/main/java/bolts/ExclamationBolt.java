package bolts;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public  class ExclamationBolt extends BaseRichBolt {
    OutputCollector _collector;

	@Override
	public void execute(Tuple tuple) {
		// TODO Auto-generated method stub
	     _collector.emit(tuple, new Values(tuple.getString(0) + "!!!"));
	     _collector.ack(tuple);
		
	}

	@Override
	public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
		// TODO Auto-generated method stub
		 _collector = collector;
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("word"));
		
	}

  


  }
