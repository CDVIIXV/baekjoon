package baekjoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * longFloyd를 거쳐서 map[i][j] + map[j][i] > 0 이면 양의 사이클이 되는 원리로 사이클 판별
 * @author Hwiyong Chang
 */
public class Main01738 {
	
	static final int MIN = -1000000;
	static int n, m;
	static boolean legal;		// 경로가 사이클을 안 거치거나 도착지로 갈 수 있는지 확인
	static boolean[] isCycle; 	// 사이클인지 확인(isVisit의 상위호환)
	static int[][] map, via;	// 정점간의 거리(floyd를 거치면 값이 변함), 경유지 기록
	static ArrayList<Integer> path;	// 지나온 경로
	
	public static void main(String[] args) {
		input();
		longFloyd();
		checkPlusCycle();
		canRecordPath(0, n-1);
		if(legal)
			for(int i=0; i<path.size(); ++i) {
				System.out.print(path.get(i));
				if(i < path.size()-1)
					System.out.print(" ");
			}
		else
			System.out.print("-1");
	}
	
	static void input() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		m = input.nextInt();
		map = new int[n][n];
		via = new int[n][n];
		for(int i=0; i<n; ++i) {
			Arrays.fill(map[i], MIN);
			Arrays.fill(via[i], MIN);
		}
		while(m-- > 0) {
			int row = input.nextInt() - 1, col = input.nextInt() - 1, value = input.nextInt();
			if(map[row][col] < value)
				map[row][col] = value;
		}
		input.close();
	}
	
	static void longFloyd() {
		for(int k=0; k<n; ++k)
			for(int i=0; i<n; ++i)
				for(int j=0; j<n; ++j)
					if(canUpdate(k, i, j) && map[i][j] < map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
						via[i][j] = k;
					}
	}
	
	static boolean canUpdate(int k, int i, int j) {
		return i != j && i != k && j != k &&
				map[i][k] != MIN && map[k][j] != MIN;
	}
	
	// longFloyd를 수행한 map에서 map[i][j] + map[j][i] > 0 이면 양의 사이클이 된다.
	static void checkPlusCycle() {
		isCycle = new boolean[n];
		for(int i=0; i<n; ++i)
			for(int j=i+1; j<n; ++j)
				if(map[i][j] + map[j][i] > 0)
					isCycle[i] = isCycle[j] = true;
	}
	
	static void canRecordPath(int start, int end) { 
		path = new ArrayList<Integer>();
		path.add(start+1);
		legal = true;
		record(start, end);
		path.add(end+1);
		
		// 출발지와 도착지가 아닌 양의 사이클 정점 중에서
		// 출발지 -> 사이클 이나 사이클 -> 도착지 인 경우에는 비합법화한다.
		for(int i=0; i<n && legal; ++i)
			if(i != start && i != end && isCycle[i] && map[start][i] != MIN && map[i][end] != MIN)
				legal = false;
	}
	
	// 경로를 기록한다.
	static void record(int start, int end) {
		if(via[start][end] != MIN && legal) {
			if(isCycle[via[start][end]]) {
				legal = false;
				return;
			}
			record(start, via[start][end]);
			path.add(via[start][end] + 1);
			record(via[start][end], end);
		}
	}
}
