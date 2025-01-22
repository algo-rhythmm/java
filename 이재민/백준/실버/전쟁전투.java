package algo0121;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    여기서 말하는 크기
    가로크기 ㅡ
    세로크기 ㅣ

    그래프탐색
    12172KB | 76ms
 */

public class 전쟁전투 {

    static int N, M;
    static char[][] soldier;
    static int friendly, enemy;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static class Pos {
       int x, y;
       public Pos(int x, int y) {
           this.x = x;
           this.y = y;
       }
    }

    static int bfs(int x, int y) {
        Queue<Pos> q = new ArrayDeque<>();
        q.add(new Pos(x, y));
        char ch = soldier[x][y];
        soldier[x][y] = 'X';

        int count = 1;

        while(!q.isEmpty()) {
            Pos cur = q.poll();

            for(int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if(!rangeCheck(nx, ny)) continue;
//                System.out.println(nx + " " + ny);
                if(soldier[nx][ny] == 'X' || soldier[nx][ny] != ch) continue;
                soldier[nx][ny] = 'X';
                q.add(new Pos(nx, ny));
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        soldier = new char[N][M];

        for(int i=0; i<N; i++){
            String line = br.readLine();
            soldier[i] = line.toCharArray();
        }


        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(soldier[i][j] != 'X'){
                    char ch = soldier[i][j];
                    int num = bfs(i, j);
                    if(ch == 'W'){
                        friendly += num * num;
                    }
                    else enemy += num * num;
                }
            }
        }

        System.out.println(friendly + " " + enemy);
    }
}
