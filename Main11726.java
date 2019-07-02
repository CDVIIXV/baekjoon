package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 점화식 a(n) = a(n-1) + a(n-2), a(0) = a(1) = 1
 * @author HwiYong Chang
 */
public class Main11726 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(input.readLine());
		input.close();
		int[] dp = new int[n+1];
		dp[0] = dp[1] = 1;
		for(int i=2; i<=n; ++i)
			dp[i] = (dp[i-1] + dp[i-2]) % 10007;
		System.out.println(dp[n]);
	}
}