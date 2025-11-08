package com.mine.tradechamp.utils;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class MyUtils {

	public static double removeDecimalsFromDoubleValue(double dd) { 
		// format double value into string, format it and then convert to double value again 
		return Double.parseDouble(new DecimalFormat("0.0000").format(dd)); 
	}
	
	public static int getDividendFrequencyIntValue(String str) { 
		Map<String, Integer> divFrequencyMap = new HashMap<>(); 
		divFrequencyMap.put("Weekly", 52); 
		divFrequencyMap.put("4-Week", 13); 
		divFrequencyMap.put("Monthly", 12); 
		divFrequencyMap.put("Quarterly", 4);
		divFrequencyMap.put("None", 1);
		
		return divFrequencyMap.get(str); 
	} // end of function getDividendFrequencyIntValue
		
}
