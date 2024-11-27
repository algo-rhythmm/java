package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2343_기타레슨 {
	static int N, M;
	static int[] lessons;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		 N = Integer.parseInt(st.nextToken());
		 M = Integer.parseInt(st.nextToken());
		 lessons = new int[N];
		
		st = new StringTokenizer(br.readLine());
		int total = 0;
		int start =1;
		for(int i=0; i<N; i++) {
			lessons[i] = Integer.parseInt(st.nextToken());
			total += lessons[i];
			start = Math.max(start, lessons[i]);
		}
		
		binarysearch(start, total);
		System.out.print(answer);
	}
	static void binarysearch(int start, int end) {
		if(start>=end) {
			answer = start;
			return;
		}
		
		int lessonNum=1;
		int length=0;
		int mid = (start+end)/2;
		for(int i=0; i<N; i++) {
			length+=lessons[i];
			
			if(length>mid) {
				lessonNum++;
				length=lessons[i];
				if(length>mid) binarysearch(mid, end);
			}
		}
		
		if(lessonNum>M) binarysearch(mid+1, end);
		else binarysearch(start, mid);
		
	}

}
