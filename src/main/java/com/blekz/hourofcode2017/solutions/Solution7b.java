package com.blekz.hourofcode2017.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.blekz.hourofcode2017.common.AbstractSolution;
import com.blekz.hourofcode2017.common.ParsedInput;

public class Solution7b extends AbstractSolution {

	@Override
	protected String doSolution(ParsedInput parsedInput) {
		List<String> unparsedLines = parsedInput.getUnparsedLines();
		List<Program> programs = parseInput(unparsedLines);
		linkPrograms(programs);
		
		Program bottomProgram = null;
		for(Program p : programs) {
			if(p.subPrograms.isEmpty()) {
				bottomProgram = p;
				break;
			}
		}
		
		while(bottomProgram.parent != null) {
			bottomProgram = bottomProgram.parent;
		}
		
		Program root = bottomProgram;
		Program unbalancedProgram = null;
		Program balancedProgram = null;
		while(root != null) {
			Map<Integer, List<Program>> balanceMap = new HashMap<>();
			for(Program p : root.subPrograms) {
				int weight = calculateWeight(p);
				if(!balanceMap.containsKey(weight)) {
					balanceMap.put(weight, new ArrayList<>());
				}
				balanceMap.get(weight).add(p);
			}
			
			if(balanceMap.size() == 1) {
				break;
			}
			
			for(Entry<Integer, List<Program>> entry : balanceMap.entrySet()) {
				if(entry.getValue().size() == 1) {
					unbalancedProgram = entry.getValue().get(0);
				} else {
					balancedProgram = entry.getValue().get(0);
				}
			}
			root = unbalancedProgram;
		}
		
		int unbalanceWeight = calculateWeight(unbalancedProgram);
		int balanceWeight = calculateWeight(balancedProgram);
		int balanceDiff = unbalanceWeight - balanceWeight;
		return unbalancedProgram.weight - balanceDiff + "";
		
	}
	
	private int calculateWeight(Program prog) {
		int weight = prog.weight;
		for(Program subProgram : prog.subPrograms) {
			weight += calculateWeight(subProgram);
		}
		return weight;
		
	}
	
	private void linkPrograms(List<Program> programs) {
		for(Program p : programs) {
			for(String subName : p.subNames) {
				Program subProgram = findByName(subName, programs);
				p.addSubProgram(subProgram);
				subProgram.setParent(p);
			}
		}
	}
	
	private List<Program> parseInput(List<String> input) {
		List<Program> programs = new ArrayList<>();
		for(String line : input) {
			line = line.replaceAll("\\s+","");
			line = line.replaceAll("\\)","");
			String[] parts = line.split("->");
			String[] part1 = parts[0].split("\\(");
			String name = part1[0];
			int weight = Integer.parseInt(part1[1]);
			
			Set<String> subNames = new HashSet<>();
			if(parts.length > 1) {
				subNames = Arrays.stream(parts[1].split(",")).collect(Collectors.toSet());
			}
			programs.add(new Program(name, weight, subNames));
		}
		return programs;
	}
	
	private Program findByName(String name, List<Program> programs) {
		for(Program p : programs) {
			if(p.name.equals(name)) {
				return p;
			}
		}
		throw new IllegalArgumentException("error name");
	}
	
	class Program {
		String name;
		int weight;
		Set<String> subNames;
		List<Program> subPrograms = new ArrayList<>();
		Program parent;
		
		public Program(String name, int weight, Set<String> subNames) {
			this.name = name;
			this.weight = weight;
			this.subNames = subNames;
		}
		
		public void addSubProgram(Program program) {
			subPrograms.add(program);
		}
		
		public void setParent(Program parent) {
			this.parent = parent;
		}
	}
	
}
