package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main01079 {
	
	private static int N, myself, answer;
	private static int[][] addGuiltyScore;
	
	static void rec(boolean isNight, boolean[] alive, int aliveCount, int[] guiltyScore, int nightCount) {
		if(N > 1) {
			if(isNight) {
				for(int i=0; i<alive.length; ++i)
					if(alive[i] && myself != i) {
						boolean[] copyAlive = alive.clone();
						int[] copyScore = guiltyScore.clone();
						copyAlive[i] = false;
						for(int j=0; j<guiltyScore.length; ++j)
							if(copyAlive[j])
								copyScore[j] += addGuiltyScore[i][j];
						rec(!isNight, copyAlive, N-1, copyScore, nightCount + 1);
					}
			} else {
				int index = -1, maxScore = -1;
				for(int i=0; i<alive.length; ++i)
					if(alive[i] && maxScore < guiltyScore[i]) {
						index = i;
						maxScore = guiltyScore[i];
					}
				alive[index] = false;
				if(!alive[myself]) {
					if(nightCount > answer)
						answer = nightCount;
				} else
					rec(!isNight, alive, N-1, guiltyScore, nightCount);
			}
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] guiltyScore = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; ++i)
        	guiltyScore[i] = Integer.parseInt(st.nextToken());
        addGuiltyScore = new int[N][N];
        for(int i=0; i<N; ++i) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<N; ++j)
        		addGuiltyScore[i][j] = Integer.parseInt(st.nextToken());
        }
        myself = Integer.parseInt(br.readLine());
        br.close();
        
        boolean[] alive = new boolean[N];
        Arrays.fill(alive, true);
        
        answer = 0;
        rec(N % 2 == 0, alive, N, guiltyScore, 0);
        System.out.println(answer);
    }
}
