package baekjoon;

import java.util.Scanner;

public class Main10542 {
	
	static int answer;
	static boolean[] judge;
	static int[] vote, count;

	static void judgement(int curr, boolean mafia) {
		if (!judge[curr]) {
			judge[curr] = true;
			if(mafia) ++answer;					// 마피아라면 정답+=1
			int next = vote[curr];
			if (--count[next] == 0 || mafia)	// 리프노드가 되거나 마피아라면, 더 탐색
				judgement(next, !mafia);
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		judge = new boolean[n];
		vote = new int[n];
		count = new int[n];
		for(int i=0; i<n; ++i) {
			vote[i] = input.nextInt() - 1;
			++count[vote[i]];
		}
		input.close();
		for(int i=0; i<n; ++i)
			if(count[i] == 0)		// 리프 노드들을 마피아로 설정하여 탐색.
				judgement(i, true);
		for(int i=0; i<n; ++i)		// 위에서 마피아들은 judge가 끝났으므로 시민들을 탐색.
			judgement(i, false);
		System.out.println(answer);
	}
}