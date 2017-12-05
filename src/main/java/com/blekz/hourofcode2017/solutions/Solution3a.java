package com.blekz.hourofcode2017.solutions;

import java.util.HashMap;
import java.util.Map;

import com.blekz.hourofcode2017.common.AbstractSolution;
import com.blekz.hourofcode2017.common.ParsedInput;

public class Solution3a extends AbstractSolution {
	
	private static final Integer INIT_X = 10000000;
	private static final Integer INIT_Y = 10000000;

	@Override
	protected String doSolution(ParsedInput parsedInput) {
		Integer input = parsedInput.getSingleValueAsInteger();
		Coord coord = getSpehereCollectionCoord(input);
		return Math.abs(coord.x - INIT_X) + Math.abs(coord.y - INIT_Y) + "";
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
	
	private Coord getSpehereCollectionCoord(Integer input) {
		Map<Coord, Integer> spiral = new HashMap<>();
		int currentX = INIT_X;
		int currentY = INIT_Y;
		int currentVal = 1;
		Directions currentDirection = Directions.RIGHT;
		Coord lastCoord = null;
		for(int i=0; i<input; i++) {
			lastCoord = new Coord(currentX, currentY);
			spiral.put(lastCoord, currentVal);
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
		return lastCoord;
	}
	
	class Coord {
		int x;
		int y;
		
		public Coord(int x, int y) {
			super();
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

		private Solution3a getOuterType() {
			return Solution3a.this;
		}
		
		
		
	}

}
