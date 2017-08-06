import java.util.HashMap;
import java.util.Map;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.hbase.bolt.HBaseBolt;
import org.apache.storm.hbase.bolt.mapper.SimpleHBaseMapper;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.apache.storm.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordCountTopology {
    private static final String SENTENCE_SPOUT_ID = "sentence-spout";
    private static final String SPLIT_BOLT_ID = "split-bolt";
    private static final String COUNT_BOLT_ID = "count-bolt";
    private static final String SORT_BOLT_ID = "sort-bolt";
    private static final String HBASE_BOLT_ID = "hbase-bolt";
    private static final String TOPOLOGY_NAME = "word-count-topology";
    
    private static final Logger logger = LoggerFactory.getLogger(WordCountTopology.class);
    
    public static void main(String[] args) throws Exception{
    	
        Config config = new Config();
		config.setDebug(false);
		
        Map<String, Object> hbConf = new HashMap<String, Object>();
        hbConf.put("hbase.rootdir", "hdfs://data-srv001:8020/hbase");
        config.put("hbase.conf", hbConf);
        
        SentenceSpout spout = new SentenceSpout();
        SplitSentenceBolt splitBolt = new SplitSentenceBolt();
        WordCountBolt countBolt = new WordCountBolt();
        SortCountBolt sortBolt = new SortCountBolt();
        
//        SimpleHBaseMapper mapper = new SimpleHBaseMapper()
//                .withRowKeyField("word")
//                .withColumnFields(new Fields("word"))
//                .withCounterFields(new Fields("count"))
//                .withColumnFamily("cf");
//
//        HBaseBolt hbaseBolt = new HBaseBolt("WordCount", mapper)
//                .withConfigKey("hbase.conf");
        
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout(SENTENCE_SPOUT_ID, spout);
        builder.setBolt(SPLIT_BOLT_ID, splitBolt).shuffleGrouping(SENTENCE_SPOUT_ID);
        builder.setBolt(COUNT_BOLT_ID, countBolt).fieldsGrouping(SPLIT_BOLT_ID, new Fields("word"));
        builder.setBolt(SORT_BOLT_ID, sortBolt).globalGrouping(COUNT_BOLT_ID);
//        builder.setBolt(HBASE_BOLT_ID,  hbaseBolt).fieldsGrouping(COUNT_BOLT_ID, new Fields("word"));
        
		
		if (args.length > 0 ){
			config.setNumWorkers(6);
			StormSubmitter.submitTopology(args[0], config, builder.createTopology());			
		} else {
	        logger.info("new LocalCluster");
	        LocalCluster cluster = new LocalCluster();
	        
	        logger.info("Before sumbitTopology");
	        cluster.submitTopology(TOPOLOGY_NAME, config, builder.createTopology());
	        logger.debug("After sumbitTopology");
	        Utils.sleep(10000);
	        cluster.killTopology(TOPOLOGY_NAME);
	        cluster.shutdown();		
		}        
    }
}