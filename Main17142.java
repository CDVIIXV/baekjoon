package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main17142 {
	
	static final int PATH = 0, WALL = 1, VIRUS = 2, MAX_VIRUS_COUNT = 10;
	static final int[][] MOVEMENT = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	static int N, M, pathCount, virusCount, bestTime;
	static int[][] map, virusLocation;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		N = input.nextInt();
		M = input.nextInt();
		pathCount = virusCount = 0;
		virusLocation = new int[MAX_VIRUS_COUNT][2];
		map = new int[N][N];
		for(int i=0; i<N; ++i)
			for(int j=0; j<N; ++j) {
				map[i][j] = input.nextInt();
				if(map[i][j] != WALL)
					++pathCount;
				if(map[i][j] == VIRUS) {
					virusLocation[virusCount][0] = i;
					virusLocation[virusCount][1] = j;
					++virusCount;
				}
			}
		input.close();
		bestTime = N*N;
		select(new int[M], 0, 0, new boolean[virusCount]);
		System.out.println(bestTime == N*N ? -1 : bestTime);
	}
	
	static void select(int[] choiceNumber, int index, int contIndex, boolean[] choice) {
		if(index < M) {
			for(int i=contIndex; i<virusCount; ++i)
				if(!choice[i]) {
					int[] copyChoiceNumber = choiceNumber.clone();
					boolean[] copyChoice = choice.clone();
					copyChoiceNumber[index] = i;
					copyChoice[i] = true;
					select(copyChoiceNumber, index+1, i+1, copyChoice);
				}
		}
		else
			spread(choice);
	}
	
	static void spread(boolean[] choice) {
		boolean[][] isVisit = new boolean[N][N];
		int visitCount = 0;
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i=0; i<virusCount; ++i) {
			int row = virusLocation[i][0], column = virusLocation[i][1];
			if(choice[i]) {
				queue.add(row * N + column);
				isVisit[row][column] = true;
			}
			++visitCount;
		}
		int second = 0, addSize = queue.size();
		boolean end = visitCount == pathCount;
		while(!queue.isEmpty() && !end) {
			int code = queue.poll();
			--addSize;
			for(int d=0; d<MOVEMENT.length && !end; ++d) {
				int nrow = code / N + MOVEMENT[d][0], ncol = code % N + MOVEMENT[d][1];
				if(nrow >= 0 && nrow < N && ncol >= 0 && ncol < N && map[nrow][ncol] != WALL && !isVisit[nrow][ncol]) {
					queue.add(nrow * N + ncol);
					isVisit[nrow][ncol] = true;
					if(map[nrow][ncol] == PATH)
						++visitCount;
					if(visitCount == pathCount) {
						++second;
						end = true;
					}
				}
			}
			if(!end && addSize == 0) {
				addSize = queue.size();
				++second;
			}
		}
		if(end && second < bestTime)
			bestTime = second;
	}
}