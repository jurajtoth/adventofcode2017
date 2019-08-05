package com.blekz.hourofcode2017.solutions;

import com.blekz.hourofcode2017.common.AbstractSolution;
import com.blekz.hourofcode2017.common.ParsedInput;

public class Solution9b extends AbstractSolution {
	
	private static final char START_GROUP = '{';
	private static final char END_GROUP = '}';
	private static final char JUNK_START = '<';
	private static final char JUNK_END = '>';
	private static final char IGNORE_JUNK = '!';
	private static final char COMMA = ',';

	@Override
	protected String doSolution(ParsedInput parsedInput) {
		String input = parsedInput.getSingleValue();
		
		boolean insideJunk = false;
		boolean ignoreNext = false;
		int junkCount = 0;
		for(int i=0; i<input.length(); i++) {
			char actualChar = input.charAt(i);
			
			if(ignoreNext) {
				ignoreNext = false;
				continue;
			}
			
			if(insideJunk) {
				if(actualChar == JUNK_END || actualChar == IGNORE_JUNK) {
					// just go forward
				} else {
					junkCount++;
					continue;
				}
			}
			
			if(actualChar == START_GROUP) {
				continue;
			}
			
			if(actualChar == END_GROUP) {
				continue;
			}
			
			if(actualChar == JUNK_START) {
				insideJunk = true;
				continue;
			}
			
			if(actualChar == JUNK_END) {
				insideJunk = false;
				continue;
			}
			
			if(actualChar == IGNORE_JUNK) {
				ignoreNext = true;
				continue;
			}
			
			if(actualChar == COMMA) {
				continue;
			}
			throw new IllegalStateException("wut @ "+i);
		}
		return junkCount + "";
	}

}
