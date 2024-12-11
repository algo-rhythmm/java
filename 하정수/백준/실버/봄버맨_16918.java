import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 *   봄버맨_16918_실버1
 *   복잡해보이지만 2,4,6,8,... 2의 배수는 항상 꽉찬 상태이고
 *   1,5,9,13,... 은 항상 초기상태와 같고
 *   3,7,11,15,... 은 항상 3초 후와 같다.
 *   3초까지만 상황을 돌리고 각 상황의 배열을 저장...
 *   라고 생각했지만 아니었다
 *
 * */

public class 봄버맨_16918 {
    static int R, C, N;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new char[R][C];

        // 초기 상태 입력
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        // N = 1일 때는 초기 상태 그대로
        if (N == 1) {
            printBoard();
            return;
        }

        // N이 짝수일 때는 모든 칸이 폭탄
        if (N % 2 == 0) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    System.out.print('O');
                }
                System.out.println();
            }
            return;
        }

        // N이 3 이상인 홀수일 때
        if (N % 4 == 3) {
            // 3초 후의 상태 계산
            char[][] temp = new char[R][C];
            for (int i = 0; i < R; i++) {
                Arrays.fill(temp[i], 'O');
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (board[i][j] == 'O') {
                        temp[i][j] = '.';
                        for (int k = 0; k < 4; k++) {
                            int nr = i + dr[k];
                            int nc = j + dc[k];
                            if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
                                temp[nr][nc] = '.';
                            }
                        }
                    }
                }
            }
            board = temp;
        } else if (N % 4 == 1) {
            // 5초, 9초, ... 후의 상태 계산
            char[][] temp = new char[R][C];
            for (int i = 0; i < R; i++) {
                Arrays.fill(temp[i], 'O');
            }

            char[][] prev = new char[R][C];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (board[i][j] == 'O') {
                        prev[i][j] = '.';
                        for (int k = 0; k < 4; k++) {
                            int nr = i + dr[k];
                            int nc = j + dc[k];
                            if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
                                prev[nr][nc] = '.';
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (prev[i][j] != '.') {
                        temp[i][j] = '.';
                        for (int k = 0; k < 4; k++) {
                            int nr = i + dr[k];
                            int nc = j + dc[k];
                            if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
                                temp[nr][nc] = '.';
                            }
                        }
                    }
                }
            }
            board = temp;
        }

        printBoard();
    }

    static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(board[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}