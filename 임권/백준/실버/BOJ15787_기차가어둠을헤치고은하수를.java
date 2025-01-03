import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15787_기차가어둠을헤치고은하수를 {
    static int[] train;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        train = new int[N + 1];
        int cmd, i, x;
        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());

            if(cmd == 1) {
                i = Integer.parseInt(st.nextToken());
                x = Integer.parseInt(st.nextToken());
                one(i, x);

            } else if(cmd == 2) {
                i = Integer.parseInt(st.nextToken());
                x = Integer.parseInt(st.nextToken());
                two(i, x);

            } else if(cmd == 3) {
                i = Integer.parseInt(st.nextToken());
                three(i);
            } else {
                i = Integer.parseInt(st.nextToken());
                four(i);
            }
        }

        boolean[] set = new boolean[1 << 21];
        int result = 0;
        for(int n = 1; n <= N; n++) {
            if(!set[train[n]]) {
                set[train[n]] = true;
                result++;
            }
        }

        System.out.println(result);
    }

    static void one(int i, int x) {
        train[i] |= (1 << x);
    }

    static void two(int i, int x) {
        train[i] &= ~(1 << x);
    }

    static void three(int i) {
        train[i] <<= 1;
        train[i] &= ~(1 << 21); // 21번째 비트를 0으로 바꾸어준다. 그렇지 않으면 기차 상태가 같지만 비트값은 32비트라서 다르다고 판단하게 된다.
    }

    static void four(int i) {
        train[i] >>>= 1;
        train[i] &= ~(1);   // 마찬가지로 1번째 비트를 0으로 바꾸어준다.
    }
}
