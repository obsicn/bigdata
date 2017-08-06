package tools;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public class WordCountClient {
	
	public static final String[] words = new String[] { "cold", "dog", "has"};
	
    public static void main(String[] args) throws Exception {
        Configuration config = HBaseConfiguration.create();
        config.set("hbase.rootdir", "hdfs://data-srv001:8020/hbase");

        HTable table = new HTable(config, "WordCount");

        for (String word : words) {
            Get get = new Get(Bytes.toBytes(word));
            Result result = table.get(get);

            byte[] countBytes = result.getValue(Bytes.toBytes("cf"), Bytes.toBytes("count"));
            byte[] wordBytes = result.getValue(Bytes.toBytes("cf"), Bytes.toBytes("word"));

            String wordStr = Bytes.toString(wordBytes);
            System.out.println(wordStr);
            long count = Bytes.toLong(countBytes);
            System.out.println("Word: '" + wordStr + "', Count: " + count);
        }

    }
}
