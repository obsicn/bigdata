

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;



public class SortCountBolt extends BaseRichBolt{
/**
     * 
     */
    private static final long serialVersionUID = 4921144902730095910L;
    //    private OutputCollector collector; ReportBolt不需要发射tuple了
    private Map<String, Long> counts = new HashMap<String, Long>();
    
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
        
        List<Entry<String, Long>> list = 
        		new LinkedList<Entry<String, Long>>(this.counts.entrySet());
 
        
        Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {  
        	   public int compare(Map.Entry obj1, Map.Entry obj2) {//从高往低排序  
        	         
        	       if(Integer.parseInt(obj1.getValue().toString())<Integer.parseInt(obj2.getValue().toString()))  
        	           return 1;  
        	       if(Integer.parseInt(obj1.getValue().toString())==Integer.parseInt(obj2.getValue().toString()))  
        	           return 0;  
        	       else  
        	          return -1;  
        	   }  
        	});
        
        System.out.println("------ print sorted word counts ------");
        for(Entry<String, Long> element: list){
        	System.out.println(element.getKey() + ":" + element.getValue());
        }
        System.out.println("------ bye ------");
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // TODO Auto-generated method stub
        //不需要发出任何数据流
    }
    
    //Topology在storm集群中运行时，cleanup方法是不可靠的,并不能保证它一定会执行
    public void cleanup(){
 
    }
}