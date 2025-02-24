package algo0224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    11054
    12360KB | 108ms

    앞에서 가장 긴 증가하는 수열을 구하고
    뒤에서부터 가장 긴 증가하는 수열을 구하면
    가장 큰 맞물리는 지점이 존재
    그 지점에서 값을 더해서 가장 큰것이 수열의 길이 (-1 해줘야함)
 */

public class 가장긴바이토닉부분수열 {

    static int N;
    static int[] ldp, rdp, arr;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ldp = new int[N];
        rdp = new int[N];
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            ldp[i] = 1;
            rdp[i] = 1;
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < i; j++) {
                if(arr[i] > arr[j])
                    ldp[i] = Math.max(ldp[i], ldp[j] + 1);
            }
        }

        for(int i = N-1; i >= 0; i--) {
            for(int j = N-1; j > i; j--) {
                if(arr[i] > arr[j])
                    rdp[i] = Math.max(rdp[i], rdp[j] + 1);
            }
        }

        for(int i = 0; i < N; i++) {
            res = Math.max(res, rdp[i] + ldp[i]);
        }

        System.out.println(res-1);
    }

}
