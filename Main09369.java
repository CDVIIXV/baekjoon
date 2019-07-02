package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Main09369
{
	static final int alphabet_count = 26;
	static final char question_mark = '?';
	
	public static boolean can_mapping(String cryptogram, String decryptogram)
	{
		int clength = cryptogram.length(), dlength = decryptogram.length();
		if(clength != dlength)
			return false;
		char[] encodeTemp = new char[alphabet_count], decodeTemp = new char[alphabet_count];
		for(int i=0; i<clength; ++i)
		{
			char cletter = cryptogram.charAt(i), dletter = decryptogram.charAt(i);
			if((decodeTemp[cletter - 'a'] > 0 && decodeTemp[cletter - 'a'] != dletter) ||
					(encodeTemp[dletter - 'a'] > 0 && encodeTemp[dletter - 'a'] != cletter))
				return false;
			else
			{
				decodeTemp[cletter - 'a'] = dletter;
				encodeTemp[dletter - 'a'] = cletter;
			}	
		}
		return true;
	}
	
	public static String solution(String[] cryptograms, String decryptogram, String object)
	{
		String[] availableWord = new String[cryptograms.length];
		int availableIndex = 0;
		for(int i=0; i<cryptograms.length; ++i)
			if(can_mapping(cryptograms[i], decryptogram))
				availableWord[availableIndex++] = cryptograms[i];
		if(availableIndex == 0)
			return "IMPOSSIBLE";
		
		int left = alphabet_count;
		char[] encode = new char[alphabet_count], decode = new char[alphabet_count];
		Arrays.fill(decode, question_mark);	
		for(int i=0; i<decryptogram.length(); ++i)
		{
			boolean availableLetter = true;
			char decodeKey = availableWord[0].charAt(i), decodeValue = decryptogram.charAt(i);
			for(int j=1; j<availableIndex; ++j)
				if(decodeKey != availableWord[j].charAt(i))
				{
					availableLetter = false;
					break;
				}
			if(availableLetter)
			{
				decode[decodeKey - 'a'] = decodeValue;
				encode[decodeValue - 'a'] = decodeKey;
				--left;
			}
		}
		
		if(left == 1)	// 알파벳 26개 중에서 25개의 해독을 알 경우, 나머지 하나를 자동으로 알 수 있음.
		{
			int decodeKey = 0, decodeValue = 0;
			for(int i=0; i<alphabet_count; ++i)
			{
				if(encode[i] == 0)
					decodeValue = i + 'a';
				if(decode[i] == question_mark)
					decodeKey = i;
			}
			decode[decodeKey] = (char)decodeValue;
		}
		
		StringBuilder answer = new StringBuilder();
		for(int i=0; i<object.length(); ++i)
			answer.append(decode[object.charAt(i) - 'a']);
		return answer.toString();
	}
	
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		int testCount = input.nextInt();
		for(int t=1; t<=testCount; ++t)
		{
			int cryptogramCount = input.nextInt();
			String[] cryptograms = new String[cryptogramCount];
			for(int i=0; i<cryptogramCount; ++i)
				cryptograms[i] = input.next();
			String decryptogram = input.next();
			String object = input.next();
			System.out.println(solution(cryptograms, decryptogram, object));
		}
		input.close();
	}
}
