package baekjoon;

import java.util.Scanner;

public class Main01016
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		long min = input.nextLong();
		long max = input.nextLong();
		boolean[] save = new boolean[(int)(max - min + 1)];
		long i=2, square = i*i;
		for(; square <= max; ++i)
		{
			square = i*i;
			long q = ((min % square) == 0) ? (min / square) : ((min / square) + 1);
			while(q * square <= max)
			{
				save[(int)(q * square - min)] = true;
				++q;
			}
		}
		int count = 0;
		for(int j=0; j<save.length; ++j)
			if(!save[j])
				++count;
		System.out.println(count);
		input.close();
	}
}
