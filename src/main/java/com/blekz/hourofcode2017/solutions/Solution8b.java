package com.blekz.hourofcode2017.solutions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blekz.hourofcode2017.common.AbstractSolution;
import com.blekz.hourofcode2017.common.ParsedInput;

public class Solution8b extends AbstractSolution {
	
	
	
	@Override
	protected String doSolution(ParsedInput parsedInput) {
		Integer highestValue = -1;
		Map<String, Integer> regValues = new HashMap<>();
		
		for(int lineIndex = 0; lineIndex < parsedInput.numberOfLines(); lineIndex++) {
			List<String> row = parsedInput.getRowValues(lineIndex);
			String registerName = row.get(0);
			IncDec incDec = IncDec.valueOf(row.get(1));
			Integer incDecvalue = Integer.parseInt(row.get(2));
			String compareRegister = row.get(4);
			CompareOperator compareOperator = CompareOperator.fromString(row.get(5));
			Integer compareValue = Integer.parseInt(row.get(6));
			
			if(!regValues.containsKey(registerName)) {
				regValues.put(registerName, 0);
			}
			if(!regValues.containsKey(compareRegister)) {
				regValues.put(compareRegister, 0);
			}
			
			int value1 = regValues.get(compareRegister);
			boolean result = compare(value1, compareOperator, compareValue);
			if(result == true) {
				int regValue = regValues.get(registerName);
				int newValue = doIncDec(regValue, incDec, incDecvalue);
				regValues.put(registerName, newValue);
				if(highestValue < newValue) {
					highestValue = newValue;
				}
			}
			
			
		}
		return highestValue.toString();
	}
	
	private boolean compare(int value1, CompareOperator operator, int value2) {
		switch(operator) {
			case EQ: return value1 == value2;
			case NE: return value1 != value2;
			case GE: return value1 >= value2;
			case LE: return value1 <= value2;
			case GT: return value1 > value2;
			case LT: return value1 < value2;
		}
		throw new IllegalArgumentException("errror compare");
	}
	
	private int doIncDec(int value1, IncDec incDec, int value2) {
		switch(incDec) {
		case inc: return value1 + value2;
		case dec: return value1 - value2;
		}
		throw new IllegalArgumentException("errror incdec");
	}
	
	enum IncDec {
		inc, dec
	}
	
	enum CompareOperator {
		
		GT(">"), LT("<"), GE(">="), LE("<="), EQ("=="), NE("!=");
		
		private String operator;
		
		private CompareOperator(String operator) {
			this.operator = operator;
		}
		
		public static CompareOperator fromString(String operator) {
			for(CompareOperator val : values()) {
				if(operator.equals(val.operator)) {
					return val;
				}
			}
			throw new IllegalArgumentException("error");
		}
	}	

}
