package algo1227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
	구현
	11824KB / 80ms
 */

public class 빗물 {

	static int H, W;
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		int[] arr = new int[W];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < W; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < W - 1; i++) {
			
			int left = 0;
			int right = 0;
			
			for(int j=0; j<i; j++) {
				left = Math.max(left, arr[j]);
			}

			for(int j=i+1; j<W; j++) {
				right = Math.max(right, arr[j]);
			}
			
			res += (Math.min(left, right)  > arr[i]) ?
					Math.min(left, right) - arr[i] : 0;
		}
		
		System.out.println(res);
	}

}