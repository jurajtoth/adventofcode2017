package com.blekz.hourofcode2017.solutions;

import com.blekz.hourofcode2017.common.AbstractSolution;
import com.blekz.hourofcode2017.common.ParsedInput;

public class Solution9a extends AbstractSolution {
	
	private static final char START_GROUP = '{';
	private static final char END_GROUP = '}';
	private static final char JUNK_START = '<';
	private static final char JUNK_END = '>';
	private static final char IGNORE_JUNK = '!';
	private static final char COMMA = ',';

	@Override
	protected String doSolution(ParsedInput parsedInput) {
		String input = parsedInput.getSingleValue();
		
		int currentGroupScore = 0;
		int totalScore = 0;
		boolean insideJunk = false;
		boolean ignoreNext = false;
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
					continue;
				}
			}
			
			if(actualChar == START_GROUP) {
				currentGroupScore++;
				continue;
			}
			
			if(actualChar == END_GROUP) {
				totalScore += currentGroupScore;
				currentGroupScore--;
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
		return totalScore + "";
	}

}
