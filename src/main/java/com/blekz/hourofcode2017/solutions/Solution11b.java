package com.blekz.hourofcode2017.solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.blekz.hourofcode2017.common.AbstractSolution;
import com.blekz.hourofcode2017.common.ParsedInput;

public class Solution11b extends AbstractSolution {

	@Override
	protected String doSolution(ParsedInput parsedInput) {
		String input = parsedInput.getSingleValue();
		int x = 0;
		int y = 0;
		int z = 0;
		List<Integer> distances = new ArrayList<>();
		for(String moveString : input.split(",")) {
			Moves move = Moves.valueOf(moveString);
			switch(move) {
				case n: y++; z--; break;
				case s: y--; z++; break;
				case nw: x--; y++; break;
				case ne: x++; z--; break;
				case sw: x--; z++; break;
				case se: x++; y--; break;
			}
			distances.add((Math.abs(x) + Math.abs(y) + Math.abs(z)) / 2);
		}
		
		return Collections.max(distances).toString();
	}
	
	enum Moves {
		n, s, nw, ne, sw, se;
	}

}
