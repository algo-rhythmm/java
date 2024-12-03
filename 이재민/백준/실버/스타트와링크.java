package algo1203;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 조합문제
 * 14120KB | 236ms
 */
public class 스타트와링크 {

	static int res, n;
	static int[][] arr;
	static boolean[] visited;
	static void combi(int cnt, int idx) {
		if(cnt == n/2) {
			int start = 0;
			int link = 0;
			for(int i=0; i<n; i++) {
				for(int j=i+1; j<n; j++) {
					if(visited[i] && visited[j]) 
						start += arr[i][j];
					else if(!visited[i] && !visited[j])
						link += arr[i][j];
				}
			}
			
			res = Math.min(res, Math.abs(start-link));
			
			return ;
		}
		
		for(int i=idx; i<n; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			combi(cnt+1, i+1);
			visited[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		arr = new int[n][n];
		res = Integer.MAX_VALUE;
		visited = new boolean[n+1];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				arr[i][j] = arr[i][j] + arr[j][i];
			}
		}
		visited[0] = true;
		combi(1, 1);
		
		System.out.println(res);
		
		
	}

}