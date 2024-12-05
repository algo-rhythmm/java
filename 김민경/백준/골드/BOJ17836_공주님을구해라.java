package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Pos {
    int r, c;
    boolean flag;

    Pos(int r, int c){
        this.r = r;
        this.c = c;
        this.flag = false;
    }

    Pos(int r, int c, boolean flag){
        this.r = r;
        this.c = c;
        this.flag = flag;
    }
}
public class BOJ17836_공주님을구해라 {
    static int N, M, T;
    static int[][] board;
    static int[][] dir = {
            {-1, 0, 1, 0},
            {0, -1, 0, 1}
    };

    static int answer=-1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        explore();

        if(answer==-1 || answer>T) System.out.println("Fail");
        else System.out.println(answer);
    }

    static void explore() {
        Deque<Pos> q = new ArrayDeque<>();
        boolean visit[][] = new boolean[N][M];
        boolean new_visit[][] = new boolean[N][M];

        q.offerLast(new Pos(0, 0));
        visit[0][0] =true;
        int cnt=0;
        A : while (!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Pos pos = q.pollFirst();

                if(pos.r == N-1 && pos.c == M-1) {
                    answer = cnt;
                    break A;
                }
                for(int d=0; d<4; d++) {
                    int nr = pos.r + dir[0][d];
                    int nc = pos.c + dir[1][d];

                    if(nr<0 || nr>=N || nc<0 || nc>=M ) continue;
                    if(board[nr][nc]==1 && !pos.flag) continue;

                    if(board[nr][nc]==2 || pos.flag) {
                        if(new_visit[nr][nc]) continue ;
                        q.offerLast(new Pos(nr, nc, true));
                        new_visit[nr][nc] = true;
                    }
                    else {
                        if(visit[nr][nc] || new_visit[nr][nc]) continue ;
                        q.offerLast(new Pos(nr, nc));
                        visit[nr][nc] =true;
                    }

                }

            }
            cnt++;
        }
    }
}
