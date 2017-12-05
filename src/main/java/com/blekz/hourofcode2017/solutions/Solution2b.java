package com.blekz.hourofcode2017.solutions;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.blekz.hourofcode2017.common.AbstractSolution;
import com.blekz.hourofcode2017.common.ParsedInput;

public class Solution2b extends AbstractSolution {

	@Override
	protected String doSolution(ParsedInput parsedInput) {
		int sum = 0;
		for(int i=0; i<parsedInput.numberOfLines();i++) {
			List<Integer> sortedValues = parsedInput.getRowValuesAsInteger(i).stream().sorted().collect(Collectors.toList());
			sortedValues.sort(Collections.reverseOrder());
			for(int a=0; a<sortedValues.size(); a++) {
				for(int b=a+1; b<sortedValues.size(); b++) {
					Integer first = sortedValues.get(a);
					Integer second = sortedValues.get(b);
					if(first % second == 0) {
						sum += first / second;
					}
				}
			}
		}
		return sum+"";
	}

}
