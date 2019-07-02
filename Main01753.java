package baekjoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main01753
{	
	static class Edge01753 implements Comparable<Edge01753>
	{
		int arrive, weight;
		Edge01753(int arrive, int weight)
		{
			this.arrive = arrive;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge01753 other)
		{
			return this.weight >= other.weight ? 1 : -1;
		}
	}
	
	static final int max_weight = 11, infinity = Integer.MAX_VALUE/2;
	
	static int vertexCount, edgeCount;
	static ArrayList<ArrayList<Integer>> edgeList;
	
	public static int[] dijkstra(int startVertex)
	{
		PriorityQueue<Edge01753> pq = new PriorityQueue<Edge01753>();
		int[] distance = new int[vertexCount];
		Arrays.fill(distance, infinity);
		
		pq.add(new Edge01753(startVertex, 0));
		distance[startVertex] = 0;
		while(!pq.isEmpty())
		{
			Edge01753 edge = pq.poll();
			if(edge.weight > distance[edge.arrive])
				continue;
			
			for(int code : edgeList.get(edge.arrive))
			{
				int arrive = code / max_weight, weight = code % max_weight;
				if(distance[arrive] > distance[edge.arrive] + weight)
				{
					distance[arrive] = distance[edge.arrive] + weight;
					pq.add(new Edge01753(arrive, distance[arrive]));
				}
			}
		}
		return distance;
	}
	
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		vertexCount = input.nextInt();
		edgeCount = input.nextInt();
		int startVertex = input.nextInt() - 1;
		edgeList = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<vertexCount; ++i)
			edgeList.add(new ArrayList<Integer>());
		for(int i=0; i<edgeCount; ++i)
		{
			int start = input.nextInt()-1, arrive = input.nextInt()-1, weight = input.nextInt();
			edgeList.get(start).add(arrive * max_weight + weight);
		}
		input.close();
		int[] result = dijkstra(startVertex);
		for(int i=0; i<result.length; ++i)
			System.out.println(result[i] == infinity ? "INF" : result[i]);
	}
}
