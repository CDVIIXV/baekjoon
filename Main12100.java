package baekjoon;

import java.util.Scanner;

public class Main12100
{
	static final int max_move = 5;
	
	static int best, side;
	
	public static int[][] spin(int[][] puzzle, int spinCount)
	{
		int[][] copy = new int[side][side];
		if(spinCount == 0)
		{
			for(int i=0; i<side; ++i)
				copy[i] = puzzle[i].clone();
			return copy;
		}
		else if(spinCount == 1)
		{
			for(int i=0; i<side; ++i)
				for(int j=0; j<side; ++j)
					copy[i][j] = puzzle[j][side-1-i];
			return copy;
		}
		else if(spinCount == 2)
		{
			for(int i=0; i<side; ++i)
				for(int j=0; j<side; ++j)
					copy[i][j] = puzzle[side-1-i][side-1-j];
			return copy;
		}
		else if(spinCount == 3)
		{
			for(int i=0; i<side; ++i)
				for(int j=0; j<side; ++j)
					copy[i][j] = puzzle[side-1-j][i];
			return copy;
		}
		return null;
	}
	
	public static void move(int moveCount, int[][] puzzle)
	{
		if(moveCount == max_move)
		{
			for(int i=0; i<side; ++i)
				for(int j=0; j<side; ++j)
					if(puzzle[i][j] > best)
						best = puzzle[i][j];
		}
		else if(moveCount < max_move)
		{	
			for(int i=0; i<4; ++i)
			{
				int[][] copy = spin(puzzle, i);
				for(int j=0; j<side; j++)
				{
					boolean sum = false;
					for(int k=1; k<side; ++k)
					{
						if(copy[j][k] == 0)
							continue;
						int l = k;
						while(l > 0 && copy[j][l-1] == 0)
						{
							copy[j][l-1] = copy[j][l];
							copy[j][l] = 0;
							--l;
						}
						if(l > 0 && copy[j][l-1] == copy[j][l] && !sum)
						{
							copy[j][l-1] *= 2;
							copy[j][l] = 0;
							sum = true;
						}
						else if(sum)
							sum = false;
					}
				}
				move(moveCount+1, copy);
			}
		}
	}
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		side = scan.nextInt();
		int[][] puzzle = new int[side][side];
		for(int i=0; i<side; ++i)
			for(int j=0; j<side; ++j)
				puzzle[i][j] = scan.nextInt();
		scan.close();
		best = 0;
		move(0, puzzle);
		System.out.println(best);
	}
}
