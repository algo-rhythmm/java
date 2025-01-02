package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 기타콘서트_1497 {
    static int N, M, answer, max;
    static boolean[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new boolean[N][M];
        answer = -1;
        max = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String guitar = st.nextToken();
            String songs = st.nextToken();

            for (int j = 0; j < M; j++) {
                if (songs.charAt(j) == 'Y') {
                    arr[i][j] = true;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            boolean[] selected = new boolean[N];
            combi(0, 0, i, selected);
        }

        System.out.println(answer);

    }

    public static void combi(int start, int depth, int targetDepth, boolean[] selected) {

        if (depth == targetDepth) {
            check(selected);
            return;
        }

        for (int i = start; i < N; i++) {
            selected[i] = true;
            combi(i + 1, depth + 1, targetDepth, selected);
            selected[i] = false;
        }
    }

    static void check(boolean[] selected) {
        boolean[] canPlay = new boolean[M];
        int count = 0;
        int able = 0;

        for (int i = 0; i < N; i++) {
            if (!selected[i]) continue;
            count++;

            for (int j = 0; j < M; j++) {
                if (arr[i][j]) {
                    canPlay[j] = true;
                }
            }

        }

        for (int i = 0; i < M; i++) {
            if (canPlay[i]) able++;
        }

        if (able > 0) {
            if (able > max || (able == max && (answer == -1 || count < answer))) {
                max = able;
                answer = count;
            }
        }
    }
}
