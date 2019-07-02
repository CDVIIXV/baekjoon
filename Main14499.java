package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//	  1
//	3 0 2
//	  4
//	  5	
public class Main14499 {
	
	private static final int empty = 0, east = 1, west = 2, north = 3, south = 4, bottom = 5, length = 6;
	private static final int[][] movement = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	
	private static int crow, ccol;
	private static int[] dice = new int[length];
	private static int[][] map;
	
	private static void shift(int direction) {
		int temp = dice[bottom];		// save bottom
		if(direction == east) {
			dice[bottom] = dice[2];
			dice[2] = dice[0];
			dice[0] = dice[3];
			dice[3] = temp;
		} else if (direction == west) {
			dice[bottom] = dice[3];
			dice[3] = dice[0];
			dice[0] = dice[2];
			dice[2] = temp;
		} else if (direction == north) {
			dice[bottom] = dice[1];
			dice[1] = dice[0];
			dice[0] = dice[4];
			dice[4] = temp;
		} else if(direction == south) {
			dice[bottom] = dice[4];
			dice[4] = dice[0];
			dice[0] = dice[1];
			dice[1] = temp;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String[] data = input.readLine().split(" ");
		map = new int[Integer.parseInt(data[0])][Integer.parseInt(data[1])];
		crow = Integer.parseInt(data[2]);
		ccol = Integer.parseInt(data[3]);
		for(int i=0; i<map.length; ++i) {
			data = input.readLine().split(" ");
			for(int j=0; j<map[i].length; ++j)
				map[i][j] = Integer.parseInt(data[j]);
		}
		data = input.readLine().split(" ");
		input.close();
		for(int i=0; i<data.length; ++i) {
			int move = Integer.parseInt(data[i]) - 1;
			int nrow = crow + movement[move][0], ncol = ccol + movement[move][1];
			if(nrow >= 0 && nrow < map.length && ncol >= 0 && ncol < map[0].length) {
				crow = nrow; ccol = ncol;
				shift(move + 1);
				if(map[crow][ccol] == empty)
					map[crow][ccol] = dice[bottom];
				else {
					dice[bottom] = map[crow][ccol];
					map[crow][ccol] = empty;
				}
				System.out.println(dice[0]);
			}
		}
	}
}