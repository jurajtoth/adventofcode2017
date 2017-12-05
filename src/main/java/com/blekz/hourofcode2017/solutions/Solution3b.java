package com.blekz.hourofcode2017.solutions;

import java.util.HashMap;
import java.util.Map;

import com.blekz.hourofcode2017.common.AbstractSolution;
import com.blekz.hourofcode2017.common.ParsedInput;

public class Solution3b extends AbstractSolution {
	
	private static final Integer INIT_X = 10000000;
	private static final Integer INIT_Y = 10000000;

	@Override
	protected String doSolution(ParsedInput parsedInput) {
		Integer input = parsedInput.getSingleValueAsInteger();
		return getSphereCollectionValue(input)+"";
	}
	
	enum Directions {
		RIGHT(1), UP(2), LEFT(3), DOWN(4);
		
		Integer index;
		
		Directions(Integer index) {
			this.index = index;
		}
		
		public Directions nextDirection() {
			Integer nextIndex = this.index + 1 > Directions.values().length ? 1 : this.index + 1;
			for(Directions direction : Directions.values()) {
				if(direction.index == nextIndex) {
					return direction;
				}
			}
			throw new IllegalArgumentException("Invalid index of direction: "+nextIndex);
		}
	}
	
	private Integer getSphereCollectionValue(Integer input) {
		Map<Coord, Integer> spiral = new HashMap<>();
		int currentX = INIT_X;
		int currentY = INIT_Y;
		// initial value
		Directions currentDirection = Directions.RIGHT;
		Coord lastCoord = null;
		int result = -1;
		while(input > result) {
			lastCoord = new Coord(currentX, currentY);
			result = calculateValue(spiral, currentX, currentY);
			spiral.put(lastCoord, result);
			// change current coord
			switch(currentDirection) {
				case RIGHT: currentX++; break;
				case UP: currentY--; break;
				case LEFT: currentX--; break;
				case DOWN: currentY++; break;
			}
			// switch direction
			int checkX=-1, checkY=-1;
			switch(currentDirection) {
				case RIGHT:
					checkX = currentX;
					checkY = currentY-1;
					break;
				case UP:
					checkX = currentX-1;
					checkY = currentY;
					break;
				case LEFT:
					checkX = currentX;
					checkY = currentY+1;
					break;
				case DOWN:
					checkX = currentX+1;
					checkY = currentY;
					break;
			}
			if(spiral.get(new Coord(checkX, checkY)) == null) {
				currentDirection = currentDirection.nextDirection();
			}
		}
		return result;
	}
	
	private Integer calculateValue(Map<Coord, Integer> spiral, Integer currentX, Integer currentY) {
		if(currentX.equals(INIT_X) && currentY.equals(INIT_Y)) {
			return 1;
		}
		int sum = 0;
		sum += getValueFromSpiral(spiral, currentX+1, currentY+1);
		sum += getValueFromSpiral(spiral, currentX+1, currentY);
		sum += getValueFromSpiral(spiral, currentX+1, currentY-1);
		sum += getValueFromSpiral(spiral, currentX, currentY-1);
		sum += getValueFromSpiral(spiral, currentX, currentY+1);
		sum += getValueFromSpiral(spiral, currentX-1, currentY-1);
		sum += getValueFromSpiral(spiral, currentX-1, currentY);
		sum += getValueFromSpiral(spiral, currentX-1, currentY+1);
		return sum;
	}
	
	private Integer getValueFromSpiral(Map<Coord, Integer> spiral, Integer x, Integer y) {
		Integer value = spiral.get(new Coord(x, y));
		return value != null ? value : 0;
	}
	
	class Coord {
		int x;
		int y;
		
		public Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Coord other = (Coord) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		private Solution3b getOuterType() {
			return Solution3b.this;
		}
		
		
		
	}

}
