package com.blekz.hourofcode2017.solutions;

import com.blekz.hourofcode2017.common.AbstractSolution;
import com.blekz.hourofcode2017.common.ParsedInput;

public class Solution5a extends AbstractSolution {

	@Override
	protected String doSolution(ParsedInput parsedInput) {
		int currentPosition = 0;
		int stepCounter = 0;
		while(true) {
			Integer jumpOffset = parsedInput.getValueAsInteger(currentPosition, 0);
			// we are out of bounds
			if(jumpOffset == null) {
				break;
			}
			parsedInput.setValue(currentPosition, 0, jumpOffset+1);
			currentPosition = currentPosition + jumpOffset;
			stepCounter++;
		}
		return stepCounter+"";
	}

}
