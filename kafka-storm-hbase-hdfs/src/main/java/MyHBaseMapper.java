import org.apache.storm.hbase.bolt.mapper.HBaseMapper;
import org.apache.storm.hbase.common.ColumnList;
import org.apache.storm.hbase.common.ColumnList.Column;
import org.apache.storm.tuple.Tuple;

public class MyHBaseMapper implements HBaseMapper {

	@Override
	public byte[] rowKey(Tuple tuple) {
		// TODO Auto-generated method stub
		String word = tuple.getStringByField("word");
		
		return word.getBytes();

	}

	@Override
	public ColumnList columns(Tuple tuple) {
		// TODO Auto-generated method stub
		
		String word = tuple.getStringByField("word");
		Long count = tuple.getLongByField("count");
		
		ColumnList cols = new ColumnList();
		
		cols.addColumn("cf".getBytes(), "count".getBytes(), count.toString().getBytes());
		
        return cols;    
	}

}
