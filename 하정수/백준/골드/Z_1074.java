package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *   Z_1074_골드5
 *   분할정복 문제
 *
 *
 * */

public class Z_1074 {

    static int N, R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int size = (int) Math.pow(2, N);

        findCnt(size, R, C, 0);
    }

    public static void findCnt(int size, int r, int c, int cnt) {
        if (size == 1) {
            System.out.println(cnt);
            return;
        }

        if (r < size / 2 && c < size / 2) {
            findCnt(size / 2, r, c, cnt);
        } else if (r < size / 2 && c >= size / 2) {
            findCnt(size / 2, r, c - size / 2, cnt + (size * size) / 4);
        } else if (r >= size / 2 && c < size / 2) {
            findCnt(size / 2, r - size / 2, c, cnt + (size * size) / 4 * 2);
        } else {
            findCnt(size / 2, r - size / 2, c - size / 2, cnt + (size * size) / 4 * 3);
        }

    }
}
