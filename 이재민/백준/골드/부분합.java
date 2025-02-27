package algo0214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    1806 부분합
    22232KB | 188ms
 */
public class 부분합 {

    static int N, S;
    static int[] arr;
    static int res = 999999999;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0;
        int e = 0;
        int sum = 0;

        // e가 끝에 도달해도 sum >= S라면 s를 +해가면서 값과 길이를 줄여봄
        while(s < N) {
            if(sum >= S) {
                sum -= arr[s];
                res = Math.min(res, e - s);
                s++;
            }
            // sum이 S보다 작은데 e가 끝에 도달했다면 s를 늘려도 의미가 없음 (값이 더 줄어든다)
            else {
                if(e == N) break;
                sum += arr[e];
                e++;
            }

        }

        System.out.println(res == 999999999 ? 0 : res);
    }

}
