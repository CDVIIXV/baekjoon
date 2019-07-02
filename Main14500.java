package baekjoon;

import java.util.Scanner;

public class Main14500
{
	static final int[][] movement = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	
	static int mrow, mcol, best;
	static int[][] map;
	
	public static void search(int sum, int count, int[] path)
	{
		if(count == 4)
		{
			if(sum > best)
				best = sum;
		}
		else if(count < 4)
		{
			for(int i=0; i<count; ++i)
			{
				int row = path[i] / mcol, col = path[i] % mcol;
				for(int j=0; j<movement.length; ++j)
				{
					int nrow = row + movement[j][0], ncol = col + movement[j][1];
					if(nrow < 0 || nrow >= mrow || ncol < 0 || ncol >= mcol)
						continue;
					
					boolean past = false;
					int code = nrow * mcol + ncol;
					for(int k=0; k<count; ++k)
						if(path[k] == code)
						{
							past = true;
							break;
						}
					if(past)
						continue;
					
					int[] cpath = path.clone();
					cpath[count] = code;
					search(sum + map[nrow][ncol], count+1, cpath);
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		mrow = scan.nextInt();
		mcol = scan.nextInt();
		best = 0;
		map = new int[mrow][mcol];
		for(int i=0; i<mrow; ++i)
			for(int j=0; j<mcol; ++j)
				map[i][j] = scan.nextInt();
		for(int i=0; i<mrow; ++i)
			for(int j=0; j<mcol; ++j)
			{
				int[] path = new int[4];
				path[0] = i * mcol + j;
				search(map[i][j], 1, path);
			}
		System.out.println(best);
		scan.close();
	}
}
