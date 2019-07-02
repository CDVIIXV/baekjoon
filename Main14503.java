package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main14503 {
	
	private static final int path = 0, wall = 1, clean = 2;
	private static final int[][] movement = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	private static int crow, ccol, direction;
	private static int[][] map;
	
	private static int move() {
		int cleanCount = 0;
		boolean stop = false;
		while(!stop) {
			if(map[crow][ccol] == path) {
				map[crow][ccol] = clean;
				++cleanCount;
			}
			boolean move = false;
			for(int i=0; i<movement.length; ++i) {
				if(i < movement.length) {
					direction = (direction + 3) % 4;
					int nrow = crow + movement[direction][0], ncol = ccol + movement[direction][1];
					if(map[nrow][ncol] == path) {
						crow = nrow;
						ccol = ncol;
						move = true;
						break;
					}
				}
			}
			
			if(!move) {
				direction = (direction + 2) % 4;
				int nrow = crow + movement[direction][0], ncol = ccol + movement[direction][1];
				direction = (direction + 2) % 4;
				if(map[nrow][ncol] == wall)
					stop = true;
				else {
					crow = nrow;
					ccol = ncol;
				}
			}
		}
		return cleanCount;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String[] data = input.readLine().split(" ");
		map = new int[Integer.parseInt(data[0])][Integer.parseInt(data[1])];
		data = input.readLine().split(" ");
		crow = Integer.parseInt(data[0]);
		ccol = Integer.parseInt(data[1]);
		direction = Integer.parseInt(data[2]);
		for(int i=0; i<map.length; ++i) {
			data = input.readLine().split(" ");
			for(int j=0; j<map[i].length; ++j)
				map[i][j] = Integer.parseInt(data[j]);
		}
		System.out.println(move());
	}
}