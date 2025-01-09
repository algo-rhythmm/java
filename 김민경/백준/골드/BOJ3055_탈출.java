import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ3055_탈출 {
    static int[][] dir = {
            {-1, 0, 1, 0},
            {0, -1, 0, 1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int startR = 0, startC = 0;
        int endR = 0, endC =0;
        Deque<Integer[]> waterQ = new ArrayDeque<>();
        Deque<Integer[]> bbQ = new ArrayDeque<>();
        char[][] board = new char[R][C];

        for(int i=0; i<R; i++) {
            board[i] = br.readLine().toCharArray();
            for(int j=0; j<C; j++) {
                if(board[i][j]=='S') {
                    startR = i;
                    startC = j;
                    bbQ.add(new Integer[]{startR, startC});
                }
                else if(board[i][j] == 'D') {
                    endR = i;
                    endC = j;
                } else if (board[i][j] == '*') {
                    waterQ.add(new Integer[] {i, j});
                }
            }
        }
        boolean[][] visit = new boolean[R][C];
        int answer = 0;
        boolean flag = false;
        visit[startR][startC] = true;
        A : while (!bbQ.isEmpty()) {
            int size = waterQ.size();
            for (int i=0; i<size; i++) {
                Integer[] temp = waterQ.poll();
                int r = temp[0];
                int c = temp[1];

                for (int d =0; d<4; d++) {
                    int nr = r + dir[0][d];
                    int nc = c + dir[1][d];

                    if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue ;
                    if(board[nr][nc]=='*' || board[nr][nc]=='X' || board[nr][nc]=='D') continue ;

                    board[nr][nc] = '*';
                    waterQ.add(new Integer[]{nr, nc});
                }

            }
            size = bbQ.size();
            for(int i=0; i<size; i++) {
                Integer[] temp = bbQ.poll();
                int r = temp[0];
                int c = temp[1];

                for (int d = 0; d<4; d++) {
                    int nr = r + dir[0][d];
                    int nc = c + dir[1][d];

                    if(nr < 0 || nr >= R  || nc< 0 || nc>=C) continue;
                    if(visit[nr][nc] || board[nr][nc]=='X' || board[nr][nc]=='*') continue;


                    if(nr==endR && nc==endC) {
                        flag = true;
                        answer++;
                        break A;
                    }
                    visit[nr][nc] = true;
                    bbQ.add(new Integer[]{nr, nc});
                }
            }



            answer++;
        }

        if(flag){ System.out.println(answer);}
        else {
            System.out.println("KAKTUS");
        }
    }
}
