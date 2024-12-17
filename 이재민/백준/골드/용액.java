package algo1217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 32756KB | 232ms
 */
public class 용액 {
	static int N, a, b;
	static int[] arr;
	static int min_value;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int s = 0;
		int e = N-1;
		min_value = Integer.MAX_VALUE;
		while(s<e) {
			int m = arr[s] + arr[e];
			if(Math.abs(m) < min_value) {
				min_value = Math.abs(m);
				a = arr[s];
				b = arr[e];
			}
			// 만약 m이 0보다 크거나 같으면 
			if(m > 0) e--;
			else s++;
		}
		System.out.println(a + " " + b);
	}

}