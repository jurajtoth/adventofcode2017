package com.blekz.hourofcode2017.solutions;

import com.blekz.hourofcode2017.common.AbstractSolution;
import com.blekz.hourofcode2017.common.ParsedInput;

public class Solution5b extends AbstractSolution {

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
			Integer newOffSetValue = null;
			if(jumpOffset >= 3) {
				newOffSetValue = jumpOffset-1;
			} else {
				newOffSetValue = jumpOffset+1;
			}
			parsedInput.setValue(currentPosition, 0, newOffSetValue);
			currentPosition = currentPosition + jumpOffset;
			stepCounter++;
		}
		return stepCounter+"";
	}

}
