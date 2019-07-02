package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main16234 {
	
	static final int[][] movement = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	
	static int N, L, R, unionCount;
	static int[][] map, unionMap;
	
	static boolean move() {
		unionCount = 1;
		unionMap = new int[N][N];
		for(int i=0; i<N; ++i)
			for(int j=0; j<N; ++j) {
				if(unionMap[i][j] == 0) {
					unionMap[i][j] = unionCount++;
                    int sum = map[i][j];
                    ArrayList<Integer> union = new ArrayList<Integer>();
                    union.add(i * N + j);
					Queue<Integer> queue = new LinkedList<Integer>();
					queue.add(i * N + j);
					while(!queue.isEmpty()) {
						int code = queue.remove(), row = code / N, col = code % N;
						for(int k=0; k<movement.length; ++k) {
							int nrow = row + movement[k][0], ncol = col + movement[k][1];
							if(nrow >= 0 && ncol >= 0 && nrow < N && ncol < N && unionMap[nrow][ncol] == 0) {
								int difference = Math.abs(map[row][col] - map[nrow][ncol]);
								if(difference >= L && difference <= R) {
									unionMap[nrow][ncol] = unionMap[i][j];
                                    union.add(nrow * N + ncol);
									queue.add(nrow * N + ncol);
                                    sum += map[nrow][ncol];
								}
							}
						}
					}
                    if(union.size() > 1) {
                        int average = sum / union.size();
                        for(int k=0; k<union.size(); ++k) {
                            int code = union.get(k), row = code / N, col = code % N;
                            map[row][col] = average;
                        }                            
                    }
				}
			}
		return unionCount <= N * N;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line = input.readLine();
		String[] data = line.split(" ");
		N = Integer.parseInt(data[0]);
		L = Integer.parseInt(data[1]);
		R = Integer.parseInt(data[2]);
		map = new int[N][N];
		for(int i=0; i<N; ++i) {
			line = input.readLine();
			data = line.split(" ");
			for(int j=0; j<N; ++j)
				map[i][j] = Integer.parseInt(data[j]);
		}
		
		int count = 0;
		while(move())
			++count;
		System.out.println(count);
	}
}