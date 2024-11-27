package 개인공부;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 이분탐색
 * 블루레이의 최소 크기는 주어진 입력의 최대값이어야 모든 강의가 블루레이에 들어감
 * 블루레이 개수가 1개라면 입력을 다 더한 값이 답임
 * 입력의 최대값이 l, 다 더한 값이 r로 해서 이분탐색
 * 23056KB / 224ms
 */

public class 기타레슨 {

	static int N, M;
	static int[] arr;
	

	public static int binary(int l, int r) {
		while(l <= r) {
			int mid = (l + r) / 2;
			int cnt = 1;
			int sum = 0;
			for(int i=0; i<N; i++) {
				if(arr[i] + sum <= mid) {
					sum += arr[i];

				}
				else {
					if(arr[i] < mid)
						sum = arr[i];
						cnt++;
				}
			}
			if(cnt > M) l = mid + 1;
			else r = mid - 1;
			
		}
		
		return l;
	}
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		
		int maxBluRay = 0;
		int sum = 0;
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			maxBluRay = Math.max(maxBluRay, arr[i]);
			sum += arr[i];
		}
		
		
		System.out.println(binary(maxBluRay, sum));
		
		
	}

}
