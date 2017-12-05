package com.blekz.hourofcode2017.solutions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.blekz.hourofcode2017.common.AbstractSolution;
import com.blekz.hourofcode2017.common.ParsedInput;

public class Solution4b extends AbstractSolution {

	@Override
	protected String doSolution(ParsedInput parsedInput) {
		int count = 0;
		for(int i=0; i<parsedInput.numberOfLines();i++) {
			List<String> values = parsedInput.getRowValues(i);
			Set<String> setWithSortedWords = new HashSet<>();
			for(String word : values) {
				setWithSortedWords.add(sortLettersInWord(word));
			}
			
			if(values.size() == setWithSortedWords.size()) {
				count++;
			}
		}
		return count+"";
	}
	
	private String sortLettersInWord(String word) {
		char[] charArr = word.toCharArray();
		Arrays.sort(charArr);
		return new String(charArr);
	}

}
