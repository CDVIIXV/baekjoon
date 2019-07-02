package baekjoon;

import java.util.LinkedList;
import java.util.Scanner;

public class Main03190
{
	static final int apple = 1, left = 2, right = 3;
	static final int[][] move = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	
	static int mrow, mcol;
	static int[][] map;
	static LinkedList<Integer> snake, moveList;
	
	public static int start()
	{
		snake = new LinkedList<Integer>();
		snake.addFirst(0);
		int second = 0, direction = 3;
		int hrow = 0, hcol = 0;
		while(true)
		{
			int code = snake.peekFirst();
			
			if(!moveList.isEmpty() && moveList.peekFirst() / 10 == second)
				direction += moveList.removeFirst() % 10 == left ? 1 : 3;
			
			hrow = code / mcol + move[direction %= move.length][0];
			hcol = code % mcol + move[direction][1];
			
			if(hrow < 0 || hrow >= mrow || hcol < 0 || hcol >= mcol || snake.contains(hrow * mcol + hcol))
			{
				++second;
				break;
			}
			
			if(map[hrow][hcol] == 0)
			{
				snake.removeLast();
				snake.addFirst(hrow * mcol + hcol);
			}
			else if(map[hrow][hcol] == 1)
			{
				--map[hrow][hcol];
				snake.addFirst(hrow * mcol + hcol);
			}
			++second;
		}
		return second;
	}
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		mrow = mcol = scan.nextInt();
		map = new int[mrow][mcol];
		int appleCount = scan.nextInt();
		for(int i=0; i<appleCount; ++i)
		{
			int arow = scan.nextInt() - 1;
			int acol = scan.nextInt() - 1;
			map[arow][acol] = apple;
		}
		int moveCount = scan.nextInt();
		moveList = new LinkedList<Integer>();
		for(int i=0; i<moveCount; ++i)
		{
			int second = scan.nextInt();
			char direction = scan.next().charAt(0);
			if(direction == 'L')
				moveList.add(second * 10 + left);
			else if(direction == 'D')
				moveList.add(second * 10 + right);
		}
		System.out.println(start());
		scan.close();
	}
}
