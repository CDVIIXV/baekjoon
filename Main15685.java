package baekjoon;

import java.util.Scanner;
import java.util.Stack;

public class Main15685
{	
	static final int maxRow = 101, maxCol = 101;
	static final int[][] movement = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
	
	static int answer, endRow, endCol;
	static boolean[][] map;
	
	public static Stack<Integer> dragon_curve(Stack<Integer> moved)
	{
		Stack<Integer> moreMoved = new Stack<Integer>();
		Stack<Integer> temp = new Stack<Integer>();
		while(!moved.isEmpty())
			temp.push(moved.pop());
		while(!temp.isEmpty())
		{
			int direction = temp.pop();
			moved.push(direction);
			moreMoved.push(direction);
		}
		while(!moved.isEmpty())
		{
			int direction = (moved.pop() + 1) % 4;
			endRow += movement[direction][0];
			endCol += movement[direction][1];
			map[endRow][endCol] = true;
			moreMoved.push(direction);
		}
		return moreMoved;
	}
	
    public static void main(String[] args)
    {
    	map = new boolean[maxRow][maxCol];
    	Scanner input = new Scanner(System.in);
    	int count = input.nextInt();
    	for(int i=0; i<count; ++i)
    	{
    		int col = input.nextInt(), row = input.nextInt(), direction = input.nextInt(), step = input.nextInt();
    		map[row][col] = true;
    		endRow = row + movement[direction][0];
    		endCol = col + movement[direction][1];
    		map[endRow][endCol] = true;
    		Stack<Integer> moved = new Stack<Integer>();
    		moved.add(direction);
    		for(int j=1; j<=step; ++j)
    			moved = dragon_curve(moved);
    	}
    	input.close();
    	answer = 0;
    	for(int i=0; i<maxRow-1; ++i)
    		for(int j=0; j<maxCol-1; ++j)
    			if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1])
    				++answer;
    	System.out.println(answer);
    }
}
