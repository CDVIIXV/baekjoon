package baekjoon;

import java.util.Scanner;

public class Main15684
{
	static final int maxAddLadder = 3;
	
	static int mrow, mcol, ladderCount, minAddLadder;
	static int[][] ladder;
	
	public static int get_manhattan()
	{
		int md = 0;
		for(int i=0; i<mcol; ++i)
		{
			int row = 0, col = i;
			while(row < mrow)
			{
				col += ladder[row][col];
				++row;
			}
			md += Math.abs(col - i);
		}
		return md;
	}
	
	public static void search(int deep, int prevCost)
	{	
		for(int i=0; i<mrow; ++i)
			for(int j=0; j<mcol-1; ++j)
			{
				if(ladder[i][j] == 0 && ladder[i][j+1] == 0)
				{
					ladder[i][j] = 1;
					ladder[i][j+1] = -1;
					
					int distance = get_manhattan();
					if(distance == 0 && deep < minAddLadder)
						minAddLadder = deep;
					else if(distance < prevCost && deep < maxAddLadder)
						search(deep+1, distance);
					
					ladder[i][j] = 0;
					ladder[i][j+1] = 0;
				}
			}
	}
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		mcol = scan.nextInt();
		ladderCount = scan.nextInt();
		mrow = scan.nextInt();
		ladder = new int[mrow][mcol];
		for(int i=0; i<ladderCount; ++i)
		{
			int row = scan.nextInt() - 1;
			int col = scan.nextInt() - 1;
			ladder[row][col] = 1;
			ladder[row][col+1] = -1;
		}
		scan.close();
		minAddLadder = maxAddLadder+1;
		int distance = get_manhattan();
		if(distance == 0)
			System.out.println(0);
		else
		{
			search(1, distance);
			if(minAddLadder == maxAddLadder+1)
				System.out.println(-1);
			else
				System.out.println(minAddLadder);
		}
	}
}
