package baekjoon;

import java.util.Scanner;

public class Main15686
{	
	static final int max_chicken_count = 13, max = Integer.MAX_VALUE/2;
	
	static int N, M, chickenIndex, answer;
	static int[][] map, chicken;
	
	public static void r(int index, int now, int[] array)
	{
		if(index < array.length)
			for(; now<chickenIndex; ++now)
			{
				int[] copy = array.clone();
				copy[index] = now;
				r(index+1, now+1, copy);
			}
		else
		{
			int temp = 0;
			for(int i=0; i<N; ++i)
				for(int j=0; j<N; ++j)
					if(map[i][j] == 1)
					{
						int min = max;
						for(int k=0; k<index; ++k)
						{
							int dis = Math.abs(i - chicken[array[k]][0]) + Math.abs(j - chicken[array[k]][1]);
							if(dis < min)
								min = dis;
						}
						temp += min;
					}
			if(answer > temp)
				answer = temp;
		}
	}
	
    public static void main(String[] args)
    {
    	Scanner input = new Scanner(System.in);
    	N = input.nextInt();
    	M = input.nextInt();
    	map = new int[N][N];
    	chicken = new int[max_chicken_count][2];
    	chickenIndex = 0;
    	answer = max;
    	for(int i=0; i<N; ++i)
    		for(int j=0; j<N; ++j)
    		{
    			map[i][j] = input.nextInt();
    			if(map[i][j] == 2)
    			{
    				chicken[chickenIndex][0] = i;
    				chicken[chickenIndex][1] = j;
    				++chickenIndex;
    			}
    		}
    	input.close();
    	r(0, 0, new int[M]);
    	System.out.println(answer);
    }
}

