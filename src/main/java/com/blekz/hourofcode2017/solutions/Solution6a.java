package com.blekz.hourofcode2017.solutions;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.blekz.hourofcode2017.common.AbstractSolution;
import com.blekz.hourofcode2017.common.ParsedInput;

public class Solution6a extends AbstractSolution {
	
	@Override
	protected String doSolution(ParsedInput parsedInput) {
		Set<String> memory = new HashSet<>();
		List<Integer> input = parsedInput.getRowValuesAsInteger(0);;
		String currentConfiguration =  listToMemoryString(input);
		
		Integer iteration = 0;
		do {
			memory.add(currentConfiguration);
			iteration++;
			int max = Collections.max(input);
			int maxIndex = findMaxIndex(input, max);
			input.set(maxIndex, 0);
			int nextIndex = maxIndex + 1;
			
			while(max != 0) {
				if(nextIndex == input.size()) {
					nextIndex = 0;
				}
				input.set(nextIndex, input.get(nextIndex) + 1);
				max--;
				nextIndex++;
			}
			
			currentConfiguration = listToMemoryString(input);
		} while(!memory.contains(currentConfiguration));
		
		return iteration.toString();
	}
	
	private static int findMaxIndex(List<Integer> list, int max) {
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).equals(max)) {
				return i;
			}
		}
		throw new IllegalArgumentException("no max");
	}
	
	private static String listToMemoryString(List<Integer> list) {
		return list.stream().map(i -> i.toString()).collect(Collectors.joining(","));
	}

	

}
