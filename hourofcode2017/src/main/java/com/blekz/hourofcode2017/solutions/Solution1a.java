package com.blekz.hourofcode2017.solutions;

import com.blekz.hourofcode2017.common.AbstractSolution;
import com.blekz.hourofcode2017.common.ParsedInput;

public class Solution1a extends AbstractSolution {

	@Override
	protected String doSolution(ParsedInput parsedInput) {
		// 1 number input
		String input = parsedInput.getSingleValue();
		Integer sum = 0;
		for(int index=0; index<input.length(); index++) {
			char c1 = input.charAt(index);
			char c2 = index+1 == input.length() ? input.charAt(0) : input.charAt(index+1);
			if(c1 == c2) {
				sum += Character.getNumericValue(c1);
			}
		}
		return sum.toString();
	}
	

}
