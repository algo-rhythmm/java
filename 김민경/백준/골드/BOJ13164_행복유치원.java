package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ13164_행복유치원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        Integer[] sub = new Integer[N-1];

        st = new StringTokenizer(br.readLine());
        arr[0] = Integer.parseInt(st.nextToken());
        int answer = 0;
        for(int i=1; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sub[i-1] = arr[i]-arr[i-1];
            answer+= sub[i-1];
        }

        Arrays.sort(sub, (a, b) -> b-a);

        for(int k=0; k<K-1; k++) {
            answer -= sub[k];
        }
        System.out.println(answer);


    }
}
