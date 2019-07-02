package baekjoon;

import java.util.Scanner;

public class Main14921
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		int count = input.nextInt();
		int[] value = new int[count];
		for(int i=0; i<count; ++i)
			value[i] = input.nextInt();
		input.close();
		
		int leftIndex = 0, rightIndex = count-1, min = value[leftIndex] + value[rightIndex];
		do
		{
			int temp = value[leftIndex] + value[rightIndex];
			if(temp == 0)
			{
				min = 0;
				break;
			}
			if(temp > 0)
				--rightIndex;
			else
				++leftIndex;
			if(Math.abs(min) > Math.abs(temp))
				min = temp;
		}while(leftIndex < rightIndex);
		System.out.println(min);
	}
}