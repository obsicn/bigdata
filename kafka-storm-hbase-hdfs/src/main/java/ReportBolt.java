

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;



public class ReportBolt extends BaseRichBolt{
/**
     * 
     */
    private static final long serialVersionUID = 4921144902730095910L;
    //    private OutputCollector collector; ReportBolt不需要发射tuple了
    private HashMap<String, Long> counts = null;
    
    public void prepare(Map stormConf, TopologyContext context,
            OutputCollector collector) {
        // TODO Auto-generated method stub
        this.counts = new HashMap<String, Long>();
    }

    public void execute(Tuple input) {
        // TODO Auto-generated method stub
        String word = input.getStringByField("word");
        Long count = input.getLongByField("count");
        this.counts.put(word, count);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // TODO Auto-generated method stub
        //不需要发出任何数据流
    }
    
    //Topology在storm集群中运行时，cleanup方法是不可靠的,并不能保证它一定会执行
    public void cleanup(){
        System.out.println("------ print counts ------");
        List<String> keys = new ArrayList<String>();
        keys.addAll(counts.keySet());//将HashMap中所有的键都添加到一个集合中
        Collections.sort(keys);//对键(单词)进行排序
        for(String key : keys)//输出排好序的每个单词的出现次数
            System.out.println(key + " : " + this.counts.get(key));
        System.out.println("--------bye----------");
    }
}