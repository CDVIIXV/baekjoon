package baekjoon;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Main14502{
    
    static final int path = 0, wall = 1, virus = 2, max_wall = 3;
    static final int[][] movement = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    
    static int row, col, answer = 0, pathCount, virusCount;
    static int[][] map, virusZ;
    
    public static int[][] copyMap(int[][] oriMap) {
        int[][] newMap = new int[row][col];
        for(int i=0; i<row; ++i)
            for(int j=0; j<col; ++j)
                newMap[i][j] = oriMap[i][j];
        return newMap;
    }
    
    public static int check(int[][] cmap) {
        int result = pathCount - max_wall;
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[][] visit = new boolean[row][col];
        for(int i=0; i<virusCount; ++i) {
            queue.add(virusZ[i][0] * col + virusZ[i][1]);
            visit[virusZ[i][0]][virusZ[i][1]] = true;
        }
        while(!queue.isEmpty()) {
            int code = queue.remove(), crow = code / col, ccol = code % col;
            for(int i=0; i<movement.length; ++i) {
                int nrow = crow + movement[i][0], ncol = ccol + movement[i][1];
                if(nrow >= 0 && nrow < row && ncol >= 0 && ncol < col && cmap[nrow][ncol] == path && !visit[nrow][ncol]) {
                    --result;
                    cmap[nrow][ncol] = virus;
                    visit[nrow][ncol] = true;
                    queue.add(nrow * col + ncol);
                }
            }
        }
        return result;
    }
    
    public static void search(int index, int wallNumber, int[][] cmap) {
        if(wallNumber >= max_wall) {
            int value = check(cmap);
            if(value > answer)
                answer = value;
        } else if (index < row * col){
            while(map[index / col][index % col] != path && index < row * col - 1)
                ++index;
            int[][] copymap = copyMap(cmap);
            copymap[index / col][index % col] = wall;
            search(index + 1, wallNumber + 1, copymap);
            search(index + 1, wallNumber, cmap);
        }
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        row = input.nextInt();
        col = input.nextInt();
        map = new int[row][col];
        pathCount = virusCount = 0;
        virusZ = new int[10][2];
        for(int i=0; i<row; ++i)
            for(int j=0; j<col; ++j) {
                map[i][j] = input.nextInt();
                if(map[i][j] == path)
                    ++pathCount;
                else if(map[i][j] == virus) {
                    virusZ[virusCount][0] = i;
                    virusZ[virusCount++][1] = j;
                }
            }
        input.close();
        search(0, 0, copyMap(map));
        System.out.println(answer);
    }
}
