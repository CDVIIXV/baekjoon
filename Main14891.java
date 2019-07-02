package baekjoon;

import java.util.Scanner;

public class Main14891 
{
	static final int cogwheelCount = 4, cogCount = 8, left = 6, right = 2;
	
	static int[][] cogwheel;
	
	public static void spin(int cogwheelNumber, int direction, int taken)
	{
		if(cogwheelNumber-2 >= 0 && taken < 1 && cogwheel[cogwheelNumber-1][left] != cogwheel[cogwheelNumber-2][right])
			spin(cogwheelNumber-1, direction * -1, taken-1);
		if(cogwheelNumber < cogwheelCount && taken > -1 && cogwheel[cogwheelNumber-1][right] != cogwheel[cogwheelNumber][left])
			spin(cogwheelNumber+1, direction * -1, taken+1);
		if(direction == -1)
		{
			int temp = cogwheel[cogwheelNumber-1][0];
			for(int i=0; i<cogCount-1; ++i)
				cogwheel[cogwheelNumber-1][i] = cogwheel[cogwheelNumber-1][i+1];
			cogwheel[cogwheelNumber-1][cogCount-1] = temp;
		}
		else
		{
			int temp = cogwheel[cogwheelNumber-1][cogCount-1];
			for(int i=cogCount-1; i>0; --i)
				cogwheel[cogwheelNumber-1][i] = cogwheel[cogwheelNumber-1][i-1];
			cogwheel[cogwheelNumber-1][0] = temp;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		cogwheel = new int[cogwheelCount][cogCount];
		for(int i=0; i<cogwheelCount; ++i)
		{
			String number = scan.next();
			for(int j=0; j<cogCount; ++j)
				cogwheel[i][j] = Integer.parseInt(number.charAt(j)+"");
		}
		int spinCount = scan.nextInt();
		for(int i=0; i<spinCount; ++i)
			spin(scan.nextInt(), scan.nextInt(), 0);
		int score = 0;
		for(int i=0; i<cogwheelCount; ++i)
			score += cogwheel[i][0] == 1 ? (int)Math.pow(2, i) : 0;
		System.out.println(score);
		scan.close();
	}
}
