package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main13460
{
	static final char path = '.', wall = '#', red = 'R', blue = 'B', hole = 'O';
	static final int max_move = 10;
	static final int[][] move = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	
	static int mrow, mcol;
	static char[][] map;
	static boolean[][][][] passed;
	
	public static int[] get_location(char object)
	{
		for(int i=0; i<mrow; ++i)
			for(int j=0; j<mcol; ++j)
				if(map[i][j] == object)
				{
					if(object == red || object == blue)
						map[i][j] = path;
					return new int[] {i, j};
				}
		return null;
	}
	
	public static int bfsSolve()
	{
		int[] red_position = get_location(red), blue_position = get_location(blue);
		Queue<Integer> marbleQueue = new LinkedList<Integer>(), moveCountQueue = new LinkedList<Integer>();
		passed[red_position[0]][red_position[1]][blue_position[0]][blue_position[1]] = true;
		marbleQueue.add((red_position[0] * mcol + red_position[1]) * (mrow * mcol) + (blue_position[0] * mcol + blue_position[1]));
		int moveCount = 0;
		moveCountQueue.add(moveCount);
		while(!marbleQueue.isEmpty())
		{
			moveCount = moveCountQueue.remove();
			int code = marbleQueue.remove(), rpos = code / (mrow * mcol), bpos = code % (mrow * mcol);
			for(int i=0; i<move.length; ++i)
			{
				int rrow = rpos / mcol, rcol = rpos % mcol, brow = bpos / mcol, bcol = bpos % mcol;
				boolean rhole = false, bhole = false;
				while(map[rrow+move[i][0]][rcol+move[i][1]] != wall || map[brow+move[i][0]][bcol+move[i][1]] != wall)
				{
					if(!rhole)
					{
						rrow += move[i][0];
						rcol += move[i][1];
					}
					brow += move[i][0];
					bcol += move[i][1];
					if(map[brow][bcol] == wall)
					{
						brow -= move[i][0];
						bcol -= move[i][1];
						if(brow == rrow && bcol == rcol)
						{
							rrow -= move[i][0];
							rcol -= move[i][1];
							break;
						}
						if(rhole)
							break;
					}
					if(map[rrow][rcol] == wall)
					{
						rrow -= move[i][0];
						rcol -= move[i][1];
						if(rrow == brow && rcol == bcol)
						{
							brow -= move[i][0];
							bcol -= move[i][1];
							break;
						}
					}
					if(map[brow][bcol] == hole)
					{
						bhole = true;
						break;
					}
					if(map[rrow][rcol] == hole)
					{
						rhole = true;
						continue;
					}
				}
				if(bhole)
					continue;
				if(rhole)
					return moveCount+1;
				if(moveCount+1 < max_move && !passed[rrow][rcol][brow][bcol])
				{
					passed[rrow][rcol][brow][bcol] = true;
					marbleQueue.add((rrow * mcol + rcol) * (mrow * mcol) + (brow * mcol + bcol));
					moveCountQueue.add(moveCount+1);
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		mrow = scan.nextInt();
		mcol = scan.nextInt();
		passed = new boolean[mrow][mcol][mrow][mcol];
		map = new char[mrow][mcol];
		for(int i=0; i<mrow; ++i)
			map[i] = scan.next().toCharArray();
		System.out.println(bfsSolve());
		scan.close();
	}
}
