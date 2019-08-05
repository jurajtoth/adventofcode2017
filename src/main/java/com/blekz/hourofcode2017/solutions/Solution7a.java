package com.blekz.hourofcode2017.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.blekz.hourofcode2017.common.AbstractSolution;
import com.blekz.hourofcode2017.common.ParsedInput;

public class Solution7a extends AbstractSolution {

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
		
		return bottomProgram.name;
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
	
//	class ProgramStruct {
//		Program program;
//		List<Program> subPrograms;
//		
//		public ProgramStruct(Program program) {
//			this.program = program;
//		}
//		
//		public void addSubProgram(Program program) {
//			subPrograms.add(program);
//		}
//	}
	
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
