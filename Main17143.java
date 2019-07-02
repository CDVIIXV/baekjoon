package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Main17143 {
	
	static final int ROW_INDEX = 0, COLUMN_INDEX = 1, SPEED_INDEX = 2, DIRECTION_INDEX = 3, SIZE_INDEX = 4, ALIVE_INDEX = 5;
	static final int[][] MOVEMENT = {{0, 0}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	
	static int mapRow, mapColumn, sharkCount;
	static int[] topColumnIndex;
	static int[][] sharkMap, sharkInfo;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		mapRow = input.nextInt();
		mapColumn = input.nextInt();
		sharkCount = input.nextInt();
		sharkMap = new int[mapRow+1][mapColumn+1];
		sharkInfo = new int[sharkCount+1][6];	// row, column, speed, direction, size, alive
		topColumnIndex = new int[mapColumn+1];
		Arrays.fill(topColumnIndex, mapRow+1);
		for(int sharkIndex=1; sharkIndex<=sharkCount; ++sharkIndex) {
			int row = input.nextInt(), column = input.nextInt();
			sharkMap[row][column] = sharkIndex;
			sharkInfo[sharkIndex][ROW_INDEX] = row;
			sharkInfo[sharkIndex][COLUMN_INDEX] = column;
			sharkInfo[sharkIndex][SPEED_INDEX] = input.nextInt();
			sharkInfo[sharkIndex][DIRECTION_INDEX] = input.nextInt();
			sharkInfo[sharkIndex][SIZE_INDEX] = input.nextInt();
			if(sharkInfo[sharkIndex][DIRECTION_INDEX] <= 2)
				sharkInfo[sharkIndex][SPEED_INDEX] %= (2*(mapRow-1));
			else
				sharkInfo[sharkIndex][SPEED_INDEX] %= (2*(mapColumn-1));
			sharkInfo[sharkIndex][ALIVE_INDEX] = 1;
			if(topColumnIndex[column] > row)
				topColumnIndex[column] = row;
		}
		input.close();
		int sizeSum = 0;
		for(int columnIndex = 1; columnIndex <= mapColumn; ++columnIndex) {
			sizeSum += movePerson(columnIndex);
			if(columnIndex < mapColumn)
				moveShark();
		}
		System.out.println(sizeSum);
	}
	
	static int movePerson(int columnIndex) {	
		if(topColumnIndex[columnIndex] <= mapRow && sharkInfo[sharkMap[topColumnIndex[columnIndex]][columnIndex]][ALIVE_INDEX] == 1){
			sharkInfo[sharkMap[topColumnIndex[columnIndex]][columnIndex]][ALIVE_INDEX] = 0;
			return sharkInfo[sharkMap[topColumnIndex[columnIndex]][columnIndex]][SIZE_INDEX];
		}
		return 0;
	}
	
	static void moveShark() {
		// Disable sharkMap, topColumn
		for(int sharkIndex = 1; sharkIndex <= sharkCount; ++sharkIndex)
			sharkMap[sharkInfo[sharkIndex][ROW_INDEX]][sharkInfo[sharkIndex][COLUMN_INDEX]] = 0;
		Arrays.fill(topColumnIndex, mapRow+1);
		
		// Calculate
		for(int sharkIndex = 1; sharkIndex <= sharkCount; ++sharkIndex)
			if(sharkInfo[sharkIndex][ALIVE_INDEX] == 1) {
				for(int moveIndex = 0; moveIndex < sharkInfo[sharkIndex][SPEED_INDEX]; ++moveIndex) {
					sharkInfo[sharkIndex][ROW_INDEX] += MOVEMENT[sharkInfo[sharkIndex][DIRECTION_INDEX]][ROW_INDEX];
					if(sharkInfo[sharkIndex][ROW_INDEX] < 1) {
						sharkInfo[sharkIndex][ROW_INDEX] = 2;
						sharkInfo[sharkIndex][DIRECTION_INDEX] = 2;
					}
					else if(sharkInfo[sharkIndex][ROW_INDEX] > mapRow) {
						sharkInfo[sharkIndex][ROW_INDEX] = mapRow-1;
						sharkInfo[sharkIndex][DIRECTION_INDEX] = 1;
					}
					sharkInfo[sharkIndex][COLUMN_INDEX] += MOVEMENT[sharkInfo[sharkIndex][DIRECTION_INDEX]][COLUMN_INDEX];
					if(sharkInfo[sharkIndex][COLUMN_INDEX] < 1) {
						sharkInfo[sharkIndex][COLUMN_INDEX] = 2;
						sharkInfo[sharkIndex][DIRECTION_INDEX] = 3;
					}
					else if(sharkInfo[sharkIndex][COLUMN_INDEX] > mapColumn) {
						sharkInfo[sharkIndex][COLUMN_INDEX] = mapColumn-1;
						sharkInfo[sharkIndex][DIRECTION_INDEX] = 4;
					}
				}
				
				// Enable sharkMap
				if(sharkMap[sharkInfo[sharkIndex][ROW_INDEX]][sharkInfo[sharkIndex][COLUMN_INDEX]] > 0) {
					int anotherSharkIndex = sharkMap[sharkInfo[sharkIndex][ROW_INDEX]][sharkInfo[sharkIndex][COLUMN_INDEX]];
					sharkMap[sharkInfo[sharkIndex][ROW_INDEX]][sharkInfo[sharkIndex][COLUMN_INDEX]] = sharkInfo[sharkIndex][SIZE_INDEX] > sharkInfo[anotherSharkIndex][SIZE_INDEX] ? sharkIndex : anotherSharkIndex;
					sharkInfo[sharkInfo[sharkIndex][SIZE_INDEX] > sharkInfo[anotherSharkIndex][SIZE_INDEX] ? anotherSharkIndex : sharkIndex][ALIVE_INDEX] = 0;
				}
				else
					sharkMap[sharkInfo[sharkIndex][ROW_INDEX]][sharkInfo[sharkIndex][COLUMN_INDEX]] = sharkIndex;
			}
		
		// Enable topColumn
		for(int sharkIndex=1; sharkIndex<=sharkCount; ++sharkIndex)
			if(sharkInfo[sharkIndex][ALIVE_INDEX] == 1 && topColumnIndex[sharkInfo[sharkIndex][COLUMN_INDEX]] > sharkInfo[sharkIndex][ROW_INDEX])
				topColumnIndex[sharkInfo[sharkIndex][COLUMN_INDEX]] = sharkInfo[sharkIndex][ROW_INDEX];
	}
}