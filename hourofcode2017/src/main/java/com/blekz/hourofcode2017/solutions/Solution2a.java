package com.blekz.hourofcode2017.solutions;

import java.util.List;

import com.blekz.hourofcode2017.common.AbstractSolution;
import com.blekz.hourofcode2017.common.ParsedInput;

public class Solution2a extends AbstractSolution {

	@Override
	protected String doSolution(ParsedInput parsedInput) {
		int sum = 0;
		for(int i=0; i<parsedInput.numberOfLines();i++) {
			List<Integer> rowValues = parsedInput.getRowValuesAsInteger(i);
			Integer max = rowValues.stream().max(Integer::compare).get();
			Integer min = rowValues.stream().min(Integer::compare).get();
			sum += Math.abs(max - min);
		}
		return sum+"";
	}

}
