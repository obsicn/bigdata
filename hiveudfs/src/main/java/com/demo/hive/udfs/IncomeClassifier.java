package com.demo.hive.udfs;

import org.apache.hadoop.io.Text;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.IntWritable;

public class IncomeClassifier extends UDF {
	public Text evaluate(IntWritable income) {
		Text incomeGroup = new Text();
		if (income.get() <= 5000) {
			incomeGroup.set("lower");
		} else if (income.get() >= 5001 &&income.get() <= 15000) {
			incomeGroup.set("middle");
		} else if (income.get() >= 15001) {
			incomeGroup.set("upper");
		}
		return incomeGroup;
	}
}