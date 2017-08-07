

import java.util.HashMap;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;



public class WordCountBolt extends BaseRichBolt{

    private OutputCollector collector;
    private HashMap<String, Long>counts = null;//统计每个单词出现的次数,放到HashMap中保存起来
    
    public void prepare(Map stormConf, TopologyContext context,
            OutputCollector collector) {
        // TODO Auto-generated method stub
        this.collector = collector;
        this.counts = new HashMap<String, Long>();//初始化HashMap,因为prepare会被自动调用的
    }

    public void execute(Tuple input) {
        // TODO Auto-generated method stub
        String word = input.getStringByField("word");
        Long count = this.counts.get(word);
        if(count == null)//HashMap中没有word这个单词
            count = 0L;
        count++;
        this.counts.put(word, count);//更新该单词在HashMap中的统计次数
        //此处发射的tuple包含了两个元素:单词和计数，它每次发送的是一个长度为2的List，
        //可理解为：List.add(new HashMap("word",word)); List.add(new HashMap(("count",count));
        System.out.println(word + ":" + count.toString());
        this.collector.emit(new Values(word, count));//第一个元素的键为 "word"，值为该单词(a string)，第二个键为 "count",值为单词的计数
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // TODO Auto-generated method stub
        declarer.declare(new Fields("word", "count"));
    }
}