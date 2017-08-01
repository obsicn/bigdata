
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;



public class SplitSentenceBolt extends BaseRichBolt {
    /**
     * 
     */
    private static final long serialVersionUID = -2107029392155190729L;
    private OutputCollector collector;// 用来向其他Spout发射tuple的发射器

    /*
     * (non-Javadoc) prepare方法类似于open方法，prepare在bolt初始化时被调用
     */
    public void prepare(Map stormConf, TopologyContext context,
            OutputCollector collector) {
        // TODO Auto-generated method stub
        this.collector = collector;// 发射器初始化

    }

    public void execute(Tuple input) {
        // TODO Auto-generated method stub
        // 接收从SentenceSpout的发射器发射过来的tuple,因为SentenceSpout中声明的tuple字段为sentence,故getStringByField方法的参数为sentence
        String sentence = input.getStringByField("sentence");// 该tuple是一个包含
                                                                // 键为sentence
                                                                // 值为字符串
                                                                // 的列表List<Map<sentence,String>>
        String[] words = sentence.split(" ");// 将字符串分解成一个个的单词
        for (String word : words)
            this.collector.emit(new Values(word));// 将每个单词构造成tuple并发送给下一个Spout
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // TODO Auto-generated method stub
        declarer.declare(new Fields("word"));// 定义SplitSentenceBolt发送的tuple的字段("键值")为 word
    }
}