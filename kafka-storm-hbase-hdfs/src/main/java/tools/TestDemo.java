package tools;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import org.apache.storm.tuple.Tuple;

public class TestDemo {
	private static String[] sentences = {"hello world  ", 
			""};	
	
	
	Tuple t1;
	
	Map map=new HashMap<String, String>();
	
	
	private final static Logger logger = LoggerFactory.getLogger(TestDemo.class);
	
	public static void main(String[] args){
		String words[];
		Values t;
		

		
		logger.info("In main:" + args.length);
		
       words = sentences[0].split(" ");// 将字符串分解成一个个的单词
        for (String word : words)
        	System.out.println("Word:" + word + "!");
        
        words = sentences[1].split(" ");// 将字符串分解成一个个的单词
        for (String word : words)
        	System.out.println("Word:" + word + "!");
	}

}
