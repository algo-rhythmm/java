package 프로그래머스;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
/*
 *   프로그래머스_충돌위험찾기
 *   물류센터 (r,c)와 같은 2차원 좌표로 나타낼 수 있는 n 개의 포인트 존재
 *   각 포인트는 1~n 까지의 서로 다른 번호
 *   로봇마다 정해진 운송경로 존재 (m 개의 포인트로 구성)
 *   운송 시슽메에 사용되는 로봇은 x대, 모든 로봇은 0초에 동시에 출발 (1초마다 r이나 c가 1씩 증가되거나 감소)
 *   다음 포인트로 이동할 때는 항상 최단 경로 (최단 경로가 여러가지일 경우, r좌표가 변하는 이동을 c좌표가 변하는 이동보다 우선)
 *   마지막 포인트에 도착한 로봇은 물류센터를 벗어남.
 *   이동 중 같은 좌표에 로봇이 2대 이상 모인다면 위험상황으로 판단.
 *   이 위험 상황이 총 몇번 일어나는지.
 *
 *   입력 이해가 안감,,
 *
 * */

public class 충돌위험찾기 {

    class Solution {
        static Queue<int[]>[] q;
        static int n;
        static int result;

        public int solution(int[][] points, int[][] routes) {
            n = routes.length;
            q = new LinkedList[n];

            for (int i = 0; i < n; i++) {
                q[i] = new LinkedList<>();
            }

            move(points, routes);

            accident();

            return result;
        }

        static void accident() {
            int count = 0;

            while (count < n) {
                int[][] arr = new int[101][101];
                count = 0;

                for (int i = 0; i < n; i++) {
                    if (q[i].isEmpty()) {
                        count++;
                        continue;
                    }

                    int[] tmp = q[i].poll();
                    arr[tmp[0]][tmp[1]]++;
                }

                for (int i = 0; i < 101; i++) {
                    for (int j = 0; j < 101; j++) {
                        if (arr[i][j] > 1) result++;
                    }
                }
            }
        }

        static void move(int[][] points, int[][] routes) {
            for (int i = 0; i < n; i++) {
                int[] route = routes[i];
                int x = points[route[0] - 1][1];
                int y = points[route[0] - 1][0];

                q[i].add(new int[]{x, y});

                for (int j = 1; j < route.length; j++) {
                    int px = points[route[j] - 1][1];
                    int py = points[route[j] - 1][0];

                    while (py != y) {
                        if (py > y) y++;
                        else y--;

                        q[i].add(new int[]{x, y});
                    }

                    while (px != x) {
                        if (px > x) x++;
                        else x--;

                        q[i].add(new int[]{x, y});
                    }
                }
            }
        }
    }


}
