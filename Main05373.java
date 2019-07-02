package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//			44 43 42		
//			41 40 39
//			38 37 36
//15 12 9	0  1  2	 29 32 35
//16 13 10	3  4  5	 28 31 34
//17 14 11	6  7  8	 27 30 33
//			18 19 20
//			21 22 23
//			24 25 26
//
//			51 52 53
//			48 49 50
//			45 46 47
public class Main05373 {
	
	private static final char[] init_cube = {
			'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w',
			'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g',
			'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r',
			'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b',
			'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o',
			'y', 'y', 'y', 'y', 'y', 'y', 'y', 'y', 'y'};
										
	private static class RubiksCube {
		
		private static final char up = 'U', left = 'L', front = 'F', right = 'R', back = 'B', down = 'D';
		private static final int[][][] fixSideIndex = {		// clock-direction
				{{38, 37, 36}, {29, 28, 27}, {20, 19, 18}, {11, 10, 9}}, 
				{{0, 3, 6}, {18, 21, 24}, {45, 48, 51}, {44, 41, 38}},
				{{6, 7, 8}, {27, 30, 33}, {47, 46, 45}, {17, 14, 11}}, 
				{{8, 5, 2}, {36, 39, 42}, {53, 50, 47}, {26, 23, 20}}, 
				{{2, 1, 0}, {9, 12, 15}, {51, 52, 53}, {35, 32, 29}}, 
				{{24, 25, 26}, {33, 34, 35}, {42, 43, 44}, {15, 16, 17}}
		};
		
		private char[] state;
		
		private RubiksCube(char[] init) {
			state = init;
		}
		
		// direction == true면 시계, false면 반시계
		private void move(char face, boolean direction) {
			int look;
			switch(face) {
				case up : look = 0; break;
				case left : look = 1; break;
				case front : look = 2; break;
				case right : look = 3; break;
				case back : look = 4; break;
				case down : look = 5; break;
				default : look = -1;	// do not occurrence.
			}
			int index = look * 9;
			int[][] sideIndex = fixSideIndex[look];
			if(direction) {
				// look
				char temp1 = state[index], temp2 = state[index+1], temp3;
				
				state[index] = state[index+6];
				state[index+1] = state[index+3];
				
				state[index+6] = state[index+8];
				state[index+3] = state[index+7];
				
				state[index+8] = state[index+2];
				state[index+7] = state[index+5];
				
				state[index+2] = temp1;
				state[index+5] = temp2;
				
				// side
				temp1 = state[sideIndex[sideIndex.length-1][0]];
				temp2 = state[sideIndex[sideIndex.length-1][1]];
				temp3 = state[sideIndex[sideIndex.length-1][2]];
				for(int i=sideIndex.length-1; i>=1; --i)
					for(int j=0; j<sideIndex[i].length; ++j)
						state[sideIndex[i][j]] = state[sideIndex[i-1][j]];
				state[sideIndex[0][0]] = temp1;
				state[sideIndex[0][1]] = temp2;
				state[sideIndex[0][2]] = temp3;
			} else {
				// look
				char temp1 = state[index], temp2 = state[index+1], temp3;
				
				state[index] = state[index+2];
				state[index+1] = state[index+5];
				
				state[index+2] = state[index+8];
				state[index+5] = state[index+7];
				
				state[index+8] = state[index+6];
				state[index+7] = state[index+3];
				
				state[index+6] = temp1;
				state[index+3] = temp2;
				
				// side
				temp1 = state[sideIndex[0][0]];
				temp2 = state[sideIndex[0][1]];
				temp3 = state[sideIndex[0][2]];
				for(int i=1; i<sideIndex.length; ++i)
					for(int j=0; j<sideIndex[i].length; ++j)
						state[sideIndex[i-1][j]] = state[sideIndex[i][j]];
				state[sideIndex[3][0]] = temp1;
				state[sideIndex[3][1]] = temp2;
				state[sideIndex[3][2]] = temp3;
			}
		}
		
		private String getState(int dir) {
			StringBuilder result = new StringBuilder();
			for(int i=9*dir; i<9*(dir+1); ++i) {
				result.append(state[i]);
				if(i % 3 == 2 && i < 9*(dir+1)-1)
					result.append("\n");
			}
			return result.toString();
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line = input.readLine();
		int testCount = Integer.parseInt(line);
		while(testCount-- > 0) {
			RubiksCube cube = new RubiksCube(init_cube.clone());
			int moveCount = Integer.parseInt(input.readLine());
			String[] move = input.readLine().split(" ");
			for(int i=0; i<moveCount; ++i)
				cube.move(move[i].charAt(0), move[i].charAt(1) == '+');
			System.out.println(cube.getState(0));
		}
	}
}