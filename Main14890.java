package baekjoon;

import java.util.Scanner;

public class Main14890
{
	static int moveCount, side, ramp;
	static int[][] map;
	
	public static void move()
	{
		for(int i=0; i<side; ++i)	// row
		{
			boolean success = true;
			boolean[] rampSet = new boolean[side];
			for(int j=1; j<side && success; ++j)
			{
				if(Math.abs(map[i][j] - map[i][j-1]) > 1)
				{
					success = false;
					break;
				}
				
				if(map[i][j] > map[i][j-1])
				{
					int k = j-1;
					int count = 0;
					while(k>=0 && count < ramp)
					{
						if(map[i][k] != map[i][j-1] || rampSet[k] == true)
						{
							success = false;
							break;
						}
						rampSet[k] = true;
						++count;
						--k;
					}
					if(count < ramp)
					{
						success = false;
						break;
					}
				}
				else if(map[i][j] < map[i][j-1])
				{
					int k = j;
					int count = 0;
					while(k<side && count < ramp)
					{
						if(map[i][k] != map[i][j] || rampSet[k] == true)
						{
							success = false;
							break;
						}
						rampSet[k] = true;
						++count;
						++k;
					}
					if(count < ramp)
					{
						success = false;
						break;
					}
				}
			}
			if(success)
				moveCount++;
		}
		
		for(int i=0; i<side; ++i)	// column
		{
			boolean success = true;
			boolean[] rampSet = new boolean[side];
			for(int j=1; j<side && success; ++j)
			{
				if(Math.abs(map[j][i] - map[j-1][i]) > 1)
				{
					success = false;
					break;
				}
				
				if(map[j][i] > map[j-1][i])
				{
					int k = j-1;
					int count = 0;
					while(k>=0 && count < ramp)
					{
						if(map[k][i] != map[j-1][i] || rampSet[k] == true)
						{
							success = false;
							break;
						}
						rampSet[k] = true;
						++count;
						--k;
					}
					if(count < ramp)
					{
						success = false;
						break;
					}
				}
				else if(map[j][i] < map[j-1][i])
				{
					int k = j;
					int count = 0;
					while(k<side && count < ramp)
					{
						if(map[k][i] != map[j][i] || rampSet[k] == true)
						{
							success = false;
							break;
						}
						rampSet[k] = true;
						++count;
						++k;
					}
					if(count < ramp)
					{
						success = false;
						break;
					}
				}
			}
			if(success)
				moveCount++;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		side = scan.nextInt();
		ramp = scan.nextInt();
		map = new int[side][side];
		for(int i=0; i<side; ++i)
			for(int j=0; j<side; ++j)
				map[i][j] = scan.nextInt();
		scan.close();
		moveCount = 0;
		move();
		System.out.println(moveCount);
	}
}
