package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Main16235 {
	
	static final int basic_nourishment = 5;
	static final int[][] movement = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
	
	static int side, treeCount, afterYear, totalTreeCount;
	static int[][] nourishment, addNourishment;
	// search best is ArrayList, add&remove best is LinkedList
	static ArrayList<LinkedList<Integer>> map;	// (row * side + col) : treeList
	
	static int[][] spring_summer() {
		int[][] Tree5count = new int[side][side];
		for(int i=0; i<side*side; ++i) {
			LinkedList<Integer> list = map.get(i);
			int size = list.size();
			if(size > 1)
				Collections.sort(list);
			int deadNourishment = 0;
			for(int j=0; j<size; ++j) {
				int treeAge = list.remove();
				if(nourishment[i/side][i%side] < treeAge) {
					deadNourishment += (treeAge / 2);
					--totalTreeCount;
				} else {
					nourishment[i/side][i%side] -= treeAge;
					list.add(treeAge+1);
					if((treeAge+1) % 5 == 0)
						++Tree5count[i/side][i%side];
				}
			}
			nourishment[i/side][i%side] += deadNourishment;
		}
		return Tree5count;
	}
	
	static void autumn_winter(int[][] Tree5count) {
		for(int i=0; i<side; ++i)
			for(int j=0; j<side; ++j) {
				if(Tree5count[i][j] > 0) {
					int count = Tree5count[i][j];
					for(int k=0; k<movement.length; ++k) {
						int nrow = i + movement[k][0], ncol = j + movement[k][1];
						if(nrow >= 0 && ncol >= 0 && nrow < side && ncol < side) {
							LinkedList<Integer> list = map.get(nrow * side + ncol);
							for(int l=0; l<count; ++l)
								list.addFirst(1);
							totalTreeCount += count;
						}
					}
				}
				nourishment[i][j] += addNourishment[i][j];
			}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line = input.readLine();
		String[] data = line.split(" ");
		side = Integer.parseInt(data[0]);
		treeCount = Integer.parseInt(data[1]);
		afterYear = Integer.parseInt(data[2]);
		totalTreeCount = treeCount;
		
		nourishment = new int[side][side];
		for(int i=0; i<side; ++i)
			Arrays.fill(nourishment[i], basic_nourishment);
		
		addNourishment = new int[side][side];
		for(int i=0; i<side; ++i) {
			line = input.readLine();
			data = line.split(" ");
			for(int j=0; j<side; ++j)
				addNourishment[i][j] = Integer.parseInt(data[j]);
		}

		map = new ArrayList<LinkedList<Integer>>();
		for(int i=0; i<side*side; ++i)
			map.add(new LinkedList<Integer>());
		
		while(treeCount-- > 0) {
			line = input.readLine();
			data = line.split(" ");
			int row = Integer.parseInt(data[0]) - 1;
			int col = Integer.parseInt(data[1]) - 1;
			int age = Integer.parseInt(data[2]);
			map.get(row * side + col).add(age);
		}
		
		while(afterYear-- > 0)
			autumn_winter(spring_summer());
		System.out.println(totalTreeCount);
	}
}
