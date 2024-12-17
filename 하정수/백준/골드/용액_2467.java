package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *   용액_2467_골드5
 *   이분탐색 문제
 *   근데 음수까지 있는데 이게 일반적으로 풀면 풀리나?
 * */

public class 용액_2467 {
    static int N;
    static int a, b;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());


        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = N - 1;
        int min = Integer.MAX_VALUE;

        while (start < end) {
            int mid = arr[start] + arr[end];
            if (Math.abs(mid) < min) {
                min = Math.abs(mid);
                a = arr[start];
                b = arr[end];
            }
            if (mid > 0) end--;
            else start++;
        }

        System.out.println(a + " " + b);

    }
}
