package baekjoon;

import java.util.Scanner;

public class Main13458
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int areaCount = scan.nextInt();
		int[] area = new int[areaCount];
		for(int i=0; i<areaCount; ++i)
			area[i] = scan.nextInt();
		int main = scan.nextInt();
		int sub = scan.nextInt();
		long sum = 0;
		for(int i=0; i<areaCount; ++i)
		{
			area[i] -= main;
			sum++;
			if(area[i] > 0)
				sum += area[i] % sub == 0 ? area[i] / sub : area[i] / sub + 1;
		}
		System.out.println(sum);
		scan.close();
	}
}
