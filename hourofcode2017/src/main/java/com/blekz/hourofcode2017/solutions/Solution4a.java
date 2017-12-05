package com.blekz.hourofcode2017.solutions;

import java.util.List;
import java.util.stream.Collectors;

import com.blekz.hourofcode2017.common.AbstractSolution;
import com.blekz.hourofcode2017.common.ParsedInput;

public class Solution4a extends AbstractSolution {

	@Override
	protected String doSolution(ParsedInput parsedInput) {
		int count = 0;
		for(int i=0; i<parsedInput.numberOfLines();i++) {
			List<String> values = parsedInput.getRowValues(i);
			Integer listSize = values.size();
			Integer setSize = values.stream().collect(Collectors.toSet()).size();
			if(listSize == setSize) {
				count++;
			}
		}
		return count+"";
	}

}
