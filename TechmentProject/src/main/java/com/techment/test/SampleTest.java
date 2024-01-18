package com.techment.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SampleTest {
	
	public static void main(String[] args) {
		String s="6762247310";
		
		Map<Character,Integer> m=new HashMap<Character, Integer>();
		
		for(char c:s.toCharArray()) {
			if(m.containsKey(c)) {
				m.put(c, m.get(c)+1);
			}else {
				m.put(c, 1);
			}
		}
		
		
		for (Entry<Character, Integer> e : m.entrySet()) {
			if(e.getValue()>1) {
				System.out.println(e);
			}
		}
		
	}

}
