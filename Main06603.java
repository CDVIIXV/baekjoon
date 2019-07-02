package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main06603 {
	
	static final int maxPrintCount = 6;
	
	static int n;
	static int[] array;
	static BufferedWriter bw;
	
	public static void dfs(int index, int printCount, String print) throws IOException {
		if(printCount < maxPrintCount) {
			if(index < n) {
				dfs(index+1, printCount+1, print + " " + array[index]);
				dfs(index+1, printCount, print);
			}
		} else if(printCount == maxPrintCount)
			bw.write(print.substring(1) + "\n");
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	while(true) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		n = Integer.parseInt(st.nextToken());
    		if(n == 0)
    			break;
    		array = new int[n];
    		for(int i=0; i<n; ++i)
    			array[i] = Integer.parseInt(st.nextToken());
    		dfs(0, 0, "");
    		bw.newLine();
    	}
    	bw.flush();
    	br.close();
    	bw.close();
    }
}