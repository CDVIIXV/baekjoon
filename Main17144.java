package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Main17144 {
	
	static final int PURIFIER_NUMBER = -1;
	static final int[][] MOVEMENT = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	static int mapRow, mapColumn, purifierRow;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		mapRow = input.nextInt();
		mapColumn = input.nextInt();
		int time = input.nextInt();
		int[][] map = new int[mapRow][mapColumn];
		for(int rowIndex = 0; rowIndex < mapRow; ++rowIndex)
			for(int columnIndex = 0; columnIndex < mapColumn; ++columnIndex) {
				map[rowIndex][columnIndex] = input.nextInt();
				if(map[rowIndex][columnIndex] == PURIFIER_NUMBER)
					purifierRow = rowIndex;
			}
		input.close();
		for(int t=1; t<=time; ++t) {
			map = spread(map);
			clear(map);
		}
		System.out.println(printSum(map));
	}
	
	static int[][] spread(int[][] map) {
		int[][] newMap = new int[mapRow][mapColumn];
		for(int rowIndex = 0; rowIndex < mapRow; ++rowIndex)
			newMap[rowIndex] = Arrays.copyOf(map[rowIndex], mapColumn);
		for(int rowIndex = 0; rowIndex < mapRow; ++rowIndex)
			for(int columnIndex = 0; columnIndex < mapColumn; ++columnIndex)
				if(canSpread(rowIndex, columnIndex) && map[rowIndex][columnIndex] > 0) {
					int spreadCount = 0, spreadAmount = map[rowIndex][columnIndex] / 5;
					for(int i=0; i<MOVEMENT.length; ++i)
						if(canSpread(rowIndex + MOVEMENT[i][0], columnIndex + MOVEMENT[i][1])) {
							newMap[rowIndex + MOVEMENT[i][0]][columnIndex + MOVEMENT[i][1]] += spreadAmount;
							++spreadCount;
						}
					newMap[rowIndex][columnIndex] -= spreadCount * spreadAmount;
				}
		return newMap;
	}
	
	static boolean canSpread(int row, int column) {
		return row >= 0 && row < mapRow && column >= 0 && column < mapColumn && 
				!(column == 0 && (row == purifierRow-1 || row == purifierRow));
	}
	
	static void clear(int[][] map) {
		// Up purifier
		directionMove(map, purifierRow-1, 0, 0, 0, 0);
		directionMove(map, 0, 0, 0, mapColumn-1, 1);
		directionMove(map, 0, mapColumn-1, purifierRow-1, mapColumn-1, 2);
		directionMove(map, purifierRow-1, mapColumn-1, purifierRow-1, 1, 3);
		map[purifierRow-1][0] = PURIFIER_NUMBER;
		map[purifierRow-1][1] = 0;
		
		// Down purifier
		directionMove(map, purifierRow, 0, mapRow-1, 0, 2);
		directionMove(map, mapRow-1, 0, mapRow-1, mapColumn-1, 1);
		directionMove(map, mapRow-1, mapColumn-1, purifierRow, mapColumn-1, 0);
		directionMove(map, purifierRow, mapColumn-1, purifierRow, 1, 3);
		map[purifierRow][0] = PURIFIER_NUMBER;
		map[purifierRow][1] = 0;
	}
	
	static void directionMove(int[][] map, int startRow, int startColumn, int endRow, int endColumn, int direction) {
		while(!(startRow == endRow && startColumn == endColumn)) {
			map[startRow][startColumn] = map[startRow + MOVEMENT[direction][0]][startColumn + MOVEMENT[direction][1]];
			startRow += MOVEMENT[direction][0];
			startColumn += MOVEMENT[direction][1];
		}
	}
	
	static int printSum(int[][] map) {
		int sum = 0;
		for(int rowIndex = 0; rowIndex < mapRow; ++rowIndex)
			for(int columnIndex = 0; columnIndex < mapColumn; ++columnIndex)
				sum += map[rowIndex][columnIndex];
		return sum - 2 * PURIFIER_NUMBER;
	}
}
