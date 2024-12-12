package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2096_내려가기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());;

        int[] min = new int[3];
        int[] max = new int[3];
        int[][] board = new int[N][3];

        min[0] = max[0] = Integer.parseInt(st.nextToken());
        min[1] = max[1] = Integer.parseInt(st.nextToken());
        min[2] = max[2] = Integer.parseInt(st.nextToken());

        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            int[] new_max = new int[3];
            int[] new_min = new int[3];

            int a = Integer.parseInt(st.nextToken());
            int temp_max = Math.max(max[0], max[1]);
            int temp_min = Math.min(min[0], min[1]);
            new_max[0] = temp_max+a;
            new_min[0] = temp_min+a;

            a = Integer.parseInt(st.nextToken());
            temp_max = Math.max(Math.max(max[0], max[1]), max[2]);
            temp_min = Math.min(Math.min(min[0], min[1]), min[2]);
            new_max[1] = temp_max+a;
            new_min[1] = temp_min+a;

            a = Integer.parseInt(st.nextToken());
            temp_max = Math.max(max[1], max[2]);
            temp_min = Math.min(min[1], min[2]);
            new_max[2] = temp_max+a;
            new_min[2] = temp_min+a;

            max = new_max;
            min = new_min;

        }

        StringBuilder sb = new StringBuilder();
        sb.append(Math.max(Math.max(max[0], max[1]), max[2])).append(" ").append(Math.min(Math.min(min[0], min[1]), min[2]));
        System.out.println(sb);
    }
}
