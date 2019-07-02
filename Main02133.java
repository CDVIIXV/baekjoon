package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * n이 짝수일 때만 계산한다. (n % 2 == 1 이면 0)
 * 점화식 a(n) = 3 * a(n-2) + {[0 ~ (n-4)]:2}sum(2 * a(n-i))
 * @author HY
 */
public class Main02133 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(input.readLine());
		input.close();
		int[] dp = new int[n+1];
		dp[0] = 1;
		for(int i=2; i<=n; i+=2) {
		    dp[i] = 3 * dp[i-2];
		    for(int j=4; j<=i; j+=2)
		        dp[i] += 2 * dp[i-j];
		}
		System.out.println(dp[n]);
	}
}