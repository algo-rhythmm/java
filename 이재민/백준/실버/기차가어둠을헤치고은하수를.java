package algo0103;

import java.io.*;
import java.util.*;

/*
    구현
    명령 수행 후 Set을 활용한 중복체크
    108984KB | 772ms
 */

public class 기차가어둠을헤치고은하수를 {

    static int N, M;
    static int[][] train;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        train= new int[N][20];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken()) - 1;

            if (command == 1) {
                int seat = Integer.parseInt(st.nextToken()) - 1;
                train[idx][seat] = 1;
            } else if (command == 2) {
                int seat = Integer.parseInt(st.nextToken()) - 1;
                train[idx][seat] = 0;
            } else if (command == 3) {
                for (int j = 19; j > 0; j--) {
                    train[idx][j] = train[idx][j - 1];
                }
                train[idx][0] = 0;
            } else if (command == 4) {
                for (int j = 0; j < 19; j++) {
                    train[idx][j] = train[idx][j + 1];
                }
                train[idx][19] = 0;
            }
        }
        br.close();

        Set<String> res = new HashSet<>();

        for (int[] t : train) {
            res.add(Arrays.toString(t));
        }

        System.out.println(res.size());
    }
}
