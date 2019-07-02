package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Main14889{
    
    static int people, answer = Integer.MAX_VALUE / 2;
    static int[][] table;
    
    public static int score(int[] team) {
    	int score = 0;
    	for(int i=0; i<team.length; ++i)
    		for(int j=i+1; j<team.length; ++j)
    			score += table[team[i]][team[j]] + table[team[j]][team[i]];
    	return score;
    }
    
    public static void dfs(int index, boolean[] team, int count) {
    	if(count >= people / 2) {
    		int team1Index = 0, team2Index = 0;
    		int[] team1 = new int[people/2], team2 = new int[people/2];
    		for(int i=0; i<people; ++i) {
    			if(team[i])
    				team1[team1Index++] = i;
    			else
    				team2[team2Index++] = i;
    		}
    		int score1 = score(team1), score2 = score(team2), difference = Math.abs(score1 - score2);
    		if(difference < answer)
    			answer = difference;
    	} else if(index < people && count + (people - 1 - index) >= people / 2){
    		boolean[] copy = Arrays.copyOf(team, people);
    		copy[index] = true;
    		dfs(index + 1, copy, count + 1);
    		dfs(index + 1, team, count);
    	}
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        people = input.nextInt();
        table = new int[people][people];
        for(int i=0; i<people; ++i)
        	for(int j=0; j<people; ++j)
        		table[i][j] = input.nextInt();
        input.close();
        dfs(0, new boolean[people], 0);
        System.out.println(answer);
    }
}