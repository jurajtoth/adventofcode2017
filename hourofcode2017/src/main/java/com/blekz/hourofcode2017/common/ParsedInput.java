package com.blekz.hourofcode2017.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class ParsedInput {
	
	private Map<Integer, Map<Integer, String>> data = new TreeMap<>();
	
	public void addData(Integer row, Integer column, String val) {
		if(data.get(row) == null) {
			data.put(row, new TreeMap<Integer, String>());
		}
		data.get(row).put(column, val);
	}
	
	public String getValue(Integer row, Integer column) {
		return data.get(row).get(column);
	}
	
	public String getSingleValue() {
		return data.get(0).get(0);
	}
	
	public List<String> getRowValues(Integer row) {
		return new ArrayList<String>(data.get(row).values());
	}
	
	public List<String> getColumnValues(Integer column) {
		List<String> values = new ArrayList<>();
		for(Map<Integer, String> row : data.values()) {
			String value = row.get(column);
			if(value != null) {
				values.add(value);
			}
		}
		return values;
	}
	
	public Integer numberOfLines() {
		return data.size();
	}
	
	public static ParsedInput parseInputFile(File inputFile) {
		ParsedInput parsedInput = new ParsedInput();
		try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
			int row = 0;
			while(reader.ready()) {
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line);
				int column = 0;
				while(tokenizer.hasMoreTokens()) {
					parsedInput.addData(row, column, tokenizer.nextToken());
					column++;
				}
				row++;
			}
		} catch (IOException e) {
			throw new IllegalStateException("Failed parsing input", e);
		}
		return parsedInput;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Map<Integer, String> row : data.values()) {
			for(String val : row.values()) {
				sb.append(val);
				sb.append(" ");
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

}
