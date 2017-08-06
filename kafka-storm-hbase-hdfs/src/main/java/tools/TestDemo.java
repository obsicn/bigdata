package tools;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.storm.tuple.Tuple;

public class TestDemo {
	private static String[] sentences = {"hello world", 
			"hello storm"};	
	Tuple t1;
	
	private final static Logger logger = LoggerFactory.getLogger(TestDemo.class);
	
	public static void main(String[] args){
		String words[];
		Values t;
		
		logger.info("In main:" + args.length);
	
		for(int index = 0; index < sentences.length; index++){
			words = sentences[index].split(" ");
			System.out.println(new Values(sentences[index].toString()));
			for(int i = 0; i < words.length; i++){
				t = new Values(words[i]);
				System.out.println(t.toString());
			}
		}
		System.out.println("hello world");
	}

}
