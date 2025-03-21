package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int a, b, w;
	Edge (int a, int b, int w) {
		this.a = a;
		this.b = b;
		this.w = w;
	}
	
	@Override
	public int compareTo (Edge o) {
		return this.w - o.w;
	}
}

public class Main_B_16398_행성연결 {
	static int N;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		parent = new int[N];
		
		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			parent[i] = i;
			for(int j=0; j<N; j++) {
				int n = Integer.parseInt(st.nextToken());
				if(j>i) {
					q.add(new Edge(i, j, n));					
				}
			}
		}
		
		int cnt =0;
		long answer =0;
		while(cnt != N-1) {
			Edge edge = q.poll();

			if(find(edge.a) != find(edge.b)) {
				union(edge.a, edge.b);
				cnt++;
				answer += edge.w;
			}
		}
		System.out.println(answer);
	}
	
	static int find(int a) {
		
		if(a==parent[a]) return a;
		
		return parent[a]= find(parent[a]);
		
	}
	static void union(int a, int b) {
		
		a = find(a);
		b = find(b);
		
		if(a==b) return;
		
		if(a<b) parent[b] = a;
		else parent[a] = b;
	}
}
