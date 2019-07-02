package baekjoon;

import java.util.Scanner;

public class Main15683
{
	static final int max_camera_count = 8, wall = 6;
	static final int[][] movement = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	static final int[][] camera_list = {{0, 0, 0, 1}, {0, 1, 0, 1}, {1, 0, 0, 1}, {1, 1, 0, 1}, {1, 1, 1, 1}};
	
	static int mrow, mcol, minHideCount, cameraCount;
	static int[][] map, camera;
	
	public static void cameraShift(int[] direction, int count)
	{
		if(count > 0)
		{
			int first = direction[0];
			for(int i=1; i<direction.length; ++i)
				direction[i-1] = direction[i];
			direction[direction.length-1] = first;
			cameraShift(direction, count-1);
		}
	}
	
	public static void search()
	{
		boolean[][] scan = new boolean[mrow][mcol];
		for(int j=0; j<cameraCount; ++j)
		{
			int kind = camera[j][2], shift = camera[j][3];
			int[] direction = camera_list[kind-1].clone();
			cameraShift(direction, shift);
			
			for(int k=0; k<movement.length; ++k)
			{
				if(direction[k] == 1)
				{
					int row = camera[j][0], col = camera[j][1];
					while(row >= 0 && row < mrow && col >= 0 && col < mcol && map[row][col] != wall)
					{
						scan[row][col] = true;
						row += movement[k][0];
						col += movement[k][1];
					}
				}
			}
		}
		int hideCount = 0;
		for(int i=0; i<mrow; ++i)
			for(int j=0; j<mcol; ++j)
				if(!scan[i][j] && map[i][j] != wall)
					++hideCount;
		if(hideCount < minHideCount)
			minHideCount = hideCount;
	}
	
	public static void spin(int camera_index)
	{
		if(camera_index < cameraCount)
		{
			if(camera[camera_index][2] == 5)
				spin(camera_index+1);
			else
				for(int i=0; i<4; ++i)
				{
					camera[camera_index][3] = i;
					spin(camera_index + 1);
				}
		}
		else if(camera_index == cameraCount)
			search();
	}
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		mrow = scan.nextInt();
		mcol = scan.nextInt();
		map = new int[mrow][mcol];
		camera = new int[max_camera_count][4];
		cameraCount = 0;
		for(int i=0; i<mrow; ++i)
			for(int j=0; j<mcol; ++j)
			{
				map[i][j] = scan.nextInt();
				if(map[i][j] > 0 && map[i][j] < 6)
				{
					camera[cameraCount][0] = i;
					camera[cameraCount][1] = j;
					camera[cameraCount][2] = map[i][j];
					camera[cameraCount][3] = 0;
					++cameraCount;
				}
			}
		scan.close();
		minHideCount = mrow * mcol;
		spin(0);
		System.out.println(minHideCount);
	}
}
