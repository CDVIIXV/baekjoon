package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main16236 {
	
	static final int empty = 0, shark = 9;
	static final int[][] movement = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	
	static int side, srow, scol, eat = 0, scale = 2;
	static int[][] map;
	static LinkedList<Integer> fish;
	
	public static int next_move() {
		Queue<Integer> queue = new LinkedList<Integer>();
		int moveCount = 0;
		queue.add(srow * side + scol);
		boolean[][] visited = new boolean[side][side];
		visited[srow][scol] = true;
		while(!queue.isEmpty()) {
			int size = queue.size();
			Queue<Integer> eatList = new LinkedList<Integer>();
			for(int i=0; i<size	; ++i) {
				int code = queue.remove(), crow = code / side, ccol = code % side;
				for(int j=0; j<movement.length; ++j) {
					int nrow = crow + movement[j][0], ncol = ccol + movement[j][1];
					if(nrow >= 0 && ncol >= 0 && nrow < side && ncol < side && !visited[nrow][ncol] && map[nrow][ncol] <= scale) {
						queue.add(nrow * side + ncol);
						visited[nrow][ncol] = true;
						if(map[nrow][ncol] > 0 && map[nrow][ncol] < scale)
							eatList.add(nrow * side + ncol);
					}
				}
			}
			++moveCount;
			if(eatList.size() > 0) {
				int min = side * side + side + 1;
				while(!eatList.isEmpty()) {
					int code = eatList.remove();
					if(min > code)
						min = code;
				}
				srow = min / side;
				scol = min % side;
				++eat;
				map[srow][scol] = 0;
				fish.remove();
				if(eat == scale) {
					eat = 0;
					++scale;
				}
				return moveCount;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line = input.readLine();
		side = Integer.parseInt(line);
		map = new int[side][side];
		fish = new LinkedList<Integer>();
		String[] data;
		for(int i=0; i<side; ++i) {
			line = input.readLine();
			data = line.split(" ");
			for(int j=0; j<side; ++j) {
				map[i][j] = Integer.parseInt(data[j]);
				if(map[i][j] > 0 && map[i][j] < 9)
					fish.add(map[i][j]);
				else if(map[i][j] == shark) {
					srow = i;
					scol = j;
					map[srow][scol] = 0;
				}
			}
		}
		Collections.sort(fish);
		int time = 0, value;
		// 먹을 물고기가 있어야하고, 그 먹을 물고기가 상어보다 작아야하고, 그 먹을 물고기에게 다가갈 수 있어야 함.
		while(fish.size() > 0 && fish.get(0) < scale && (value = next_move()) > 0)
			time += value;
		System.out.println(time);
	}
}