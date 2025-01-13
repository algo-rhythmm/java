package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  탈출_3055_골드4
 *
 *  BFS로 최단시간 찾기 문제인 것 같다.
 *  R,C 변수 선언
 *  맵 입력받고 S면 고슴도치 큐에 삽입
 *  *이면 물 큐에 삽입
 *  count 변수 선언하고 count 증가시키면서 while문 시작
 *  물부터 맵에 퍼지게 하고 다음으로 고슴도치 이동
 *  고슴도치가 D에 도착하면 그 즉시 break 하고 count값 출력
 *  도착하지 못했는데 큐가 비었다면 KAKTUS 출력
 *
 * */

public class 탈출_3055 {

    static int R, C, count;
    static char[][] arr;
    static Queue<int[]> hedgehogQ = new LinkedList<>();
    static Queue<int[]> waterQ = new LinkedList<>();
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = str.charAt(j);
                arr[i][j] = c;
                if (c == '*') {
                    waterQ.add(new int[]{i, j});
                } else if (c == 'S') {
                    hedgehogQ.add(new int[]{i, j});
                }
            }
        }

        count = 0;
        boolean isEscape = false;

        Q:while (!hedgehogQ.isEmpty()) {
            count++;

            int waterSize = waterQ.size();
            for (int i = 0; i < waterSize; i++) {
                int[] water = waterQ.poll();
                for (int d = 0; d < 4; d++) {
                    int rr = dr[d] + water[0];
                    int cc = dc[d] + water[1];
                    if (rr >= 0 && rr < R && cc >= 0 && cc < C && (arr[rr][cc] == '.' || arr[rr][cc] == 'S')) {
                        arr[rr][cc] = '*';
                        waterQ.add(new int[]{rr, cc});
                    }
                }
            }

            int size = hedgehogQ.size();
            for (int i = 0; i < size; i++) {
                int[] hedgehog = hedgehogQ.poll();
                for (int d = 0; d < 4; d++) {
                    int rr = dr[d] + hedgehog[0];
                    int cc = dc[d] + hedgehog[1];
                    if (rr >= 0 && rr < R && cc >= 0 && cc < C && (arr[rr][cc] == '.' || arr[rr][cc] == 'D')) {
                        if (arr[rr][cc] == 'D') {
                            isEscape = true;
                            break Q;
                        }
                        arr[rr][cc] = 'S';
                        hedgehogQ.add(new int[]{rr, cc});
                    }
                }
            }
        }

        if (isEscape) {
            System.out.println(count);
        } else {
            System.out.println("KAKTUS");
        }

    }
}
