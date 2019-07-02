package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main01032 {
	
	static String word;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(input.readLine());
		word = input.readLine();
		for(int i=1; i<count; ++i)
		{	// 단어를 비교하여 같은 문자가 아니면 ?로 처리
			String temp = input.readLine();
			for(int j=0; j<word.length(); ++j)
				if(word.charAt(j) != temp.charAt(j))
					word = word.substring(0, j) + "?" + word.substring(j+1);
		}
		System.out.println(word);
	}
}
