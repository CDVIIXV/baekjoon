package baekjoon;

import java.util.Scanner;

public class Main01359 {
	
	static int[][] combi;
	
	public static int combination(int n, int r) {
		if(combi[n] == null)
		{
			combi[n] = new int[n+1];
			combi[n][0] = combi[n][n] = 1;
		}
		if(combi[n][r] > 0)
			return combi[n][r];
		return combi[n][r] = combination(n-1, r-1) + combination(n-1, r);
	}
	
    public static void main(String[] args)
    {
    	Scanner input = new Scanner(System.in);
		int n = input.nextInt(), m = input.nextInt(), k = input.nextInt();
		input.close();
    	combi = new int[n+1][];
    	double answer = 0;
		for(int i=k; i<=m; ++i)
            if(n-m >= m-i)		// ex) n=4, m=3, k=1에서 i=1일 때, 4개 중 3개의 번호를 뽑는 경우에서 1개를 맞고 2개를 틀리는 확률은 0이다. (더하지 않음) 
			    answer += combination(m, i) * combination(n-m, m-i);
		System.out.println(answer/combination(n, m));
    }
}
