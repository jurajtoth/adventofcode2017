package com.blekz.hourofcode2017.solutions;

import com.blekz.hourofcode2017.common.AbstractSolution;
import com.blekz.hourofcode2017.common.ParsedInput;

public class Solution11a extends AbstractSolution {

	@Override
	protected String doSolution(ParsedInput parsedInput) {
		String input = parsedInput.getSingleValue();
		int x = 0;
		int y = 0;
		int z = 0;
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
			
		}
		return ((Math.abs(x) + Math.abs(y) + Math.abs(z)) / 2) + "";
		
//		double sideMoves = Math.abs(x) * 2;
//		double upDownMoves = (Math.abs(y) - Math.abs(x));
//		int result = (int) (sideMoves + upDownMoves);
//		return result + "";
	}
	
	enum Moves {
		n, s, nw, ne, sw, se;
	}

}
