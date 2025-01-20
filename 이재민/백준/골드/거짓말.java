package algo0120;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
	disjointset
	11704KB | 80ms
 */

public class 거짓말 {
	
	static int N, M;
	static boolean[] truth;
	static List<Integer>[] edges;
	static int[] p;
	static int res;
	
	static void make() {
		p = new int[N+1];
		for(int i=1; i<=N; i++) {
			p[i] = i;
		}
	}
	
	static int find(int x) {
		if(p[x] == x) return x;
		return p[x] = find(p[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return ;
		if(truth[x]) p[y] = x;
		else if(truth[y]) p[x] = y;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		res = M;
		edges = new List[M];
		truth = new boolean[N+1];
		
		for(int i=0; i<M; i++) {
			edges[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		
		int tSize = Integer.parseInt(st.nextToken());
		for(int i=0; i<tSize; i++) {
			truth[Integer.parseInt(st.nextToken())] = true;
		}
		
		make();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int eSize = Integer.parseInt(st.nextToken());
			for(int j=0; j<eSize; j++) {
				edges[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for(int p=0; p<N; p++) {
			for(int i=0; i<M; i++) {
				for(int j=0; j<edges[i].size()-1; j++) {
					for(int k=j+1; k<edges[i].size(); k++)
						union(edges[i].get(j), edges[i].get(k));
				}
			}
		}
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<edges[i].size(); j++) {
				int x = find(edges[i].get(j));
				if(truth[x]) {
					res--;
					break;
				}
			}
		}
		
		System.out.println(res);
		
	}

}