import org.apache.storm.Config;
import org.apache.storm.topology.TopologyBuilder;

import bolts.ExclamationBolt;
import spouts.TestWordSpout;

public class ExclamationTopology {
	
	public static void main(String[] args) throws Exception {
		Config conf = new Config();
				
		 TopologyBuilder builder = new TopologyBuilder();

		    builder.setSpout("word", new TestWordSpout(), 10);
		    builder.setBolt("exclaim1", new ExclamationBolt(), 3).shuffleGrouping("word");
		    builder.setBolt("exclaim2", new ExclamationBolt(), 2).shuffleGrouping("exclaim1");

		    
		    conf.setDebug(true);

		    String topologyName = "test";

		    conf.setNumWorkers(3);

		    if (args != null && args.length > 0) {
		      topologyName = args[0];
		    }
	}

}
