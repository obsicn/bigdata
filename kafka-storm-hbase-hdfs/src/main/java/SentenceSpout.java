

import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;



public class SentenceSpout extends BaseRichSpout {
    /**
     * 
     */
    private static final long serialVersionUID = 3444934973982660864L;
    private SpoutOutputCollector collector;// 用来向其他Spout发射tuple
    private String[] sentences = { "Hello Storm", "Hello Kafka",
            "Hello HBase", "Hello HDFS" };

    private int index = 0;

    /*
     * open() 方法在所有的Spout组件初始化时被调用
     * 
     * @param Map conf storm 配置信息
     * 
     * @context TopologyContext topology 组件信息
     */
    public void open(@SuppressWarnings("rawtypes") Map conf,
            TopologyContext context, SpoutOutputCollector collector) {
        // TODO Auto-generated method stub
        this.collector = collector;
    }

    /*
     * Values.java extends ArrayList Storm 调用该方法向输出的collector发射tuple
     */
    public void nextTuple() {
        // TODO Auto-generated method stub
        // 以字符串数组sentences 中的每个字符串 作为参数 构造tuple
        this.collector.emit(new Values(sentences[index]));// 通过emit方法将构造好的tuple发送出去
        index++;
        if (index >= sentences.length) {
            index = 0;
        }
        Utils.sleep(100);
    }

    /*
     * SentenceSpout 发送的tuple它是一个包含键值对的List，该方法声明了List中包含的键值对的键为 sentence
     */
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // TODO Auto-generated method stub
        declarer.declare(new Fields("value"));// 标记SentenceSpout发送的tuple的键为
                                                    // sentence
    }
}