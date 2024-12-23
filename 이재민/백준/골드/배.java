package algo1223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
    각 크레인은 시간다마 박스를 하나씩 옮길 수 있음
    무게제한이 가장 큰 크레인이 가장 큰 박스를 옮겨야 시간을 줄일 수 있음
    내림차순 정렬

    14928KB | 292ms
 */

public class 배 {

    static int N, M;
    static Integer[] box, crane;

    static int move() {
        int time = 0;
        int count = 0;

        while (count < M) {
            int idx = 0;
            for (int i = 0; i < M; i++) {
                if (box[i] == -1) continue;

                if (crane[idx] >= box[i]) {
                    box[i] = -1;
                    count++;
                    idx++;
                }

                if (idx == N) break;
            }
            time++;
        }

        return time;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        crane = new Integer[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            crane[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        box = new Integer[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            box[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(box, Collections.reverseOrder());
        Arrays.sort(crane, Collections.reverseOrder());

        // 가장 무거운 박스를 무게 제한이 가장 큰 크레인으로 못옮기면 -1
        if (box[0] > crane[0]) {
            System.out.println(-1);
            return;
        }

        System.out.println(move());
    }
}
