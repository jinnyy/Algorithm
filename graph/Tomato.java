package graph;
/*
 * [백준] 토마토
 * https://www.acmicpc.net/problem/7576
 */
import java.util.Scanner;
import java.util.LinkedList;


public class Tomato {
	static int M, N, graph[][], count;
	static LinkedList<Point> Q;
	static final int[] dX = {0, 0, 1, -1};
	static final int[] dY = {1, -1, 0, 0};
	

	public static void main(String[] args) {		
		Scanner reader = new Scanner(System.in);
		M = reader.nextInt();
		N = reader.nextInt();
		graph = new int[N][M];
		Q = new LinkedList<Point>();

		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				graph[i][j] = reader.nextInt();
				if (graph[i][j]==1)
					Q.add(new Point(j, i));
			}
		}
		reader.close();
		
		
		// 내용이 1인 칸에서부터 시작해서 bfs
		count = 0;
		
		while(!Q.isEmpty()) {
			Point point = Q.poll();

			int x, y;
			for(int i=0; i<4; i++) {
				x = point.x + dX[i];
				y = point.y + dY[i];
				
				if(x>=0 && x<M && y>=0 && y<N && graph[y][x]==0) {
					graph[y][x] = graph[point.y][point.x] + 1;
					count = Math.max(graph[y][x], count);
					Q.add(new Point(x, y));
				}
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(graph[i][j]==0) {
					System.out.println(-1);
					return;
				}
			}
		}
		if(count==0) System.out.println(0);
		else System.out.println(count-1);
	}
}



class Point {
	int x, y;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	boolean equals(Point point) {
		return point.x==this.x && point.y==this.y;
	}
}
