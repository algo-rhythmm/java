package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ16918_봄버맨 {
    static int[][] dir = {
            {-1, 0, 1, 0},
            {0, -1, 0, 1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] board = new int[R][C];
        char[][] bomb = new char[R][C];

        for(int i=0; i<R; i++) {
            bomb[i] = br.readLine().toCharArray();
            for(int j=0; j<C; j++) {
                if(bomb[i][j]=='O') {
                    board[i][j] = 2;
                }
            }
        }


        for(int t=0; t<N-1; t++) {
            Deque<Integer[]> q = new ArrayDeque<>();


            // set bomb and time flow
            for(int i=0; i<R; i++) {
                for(int j=0; j<C; j++) {
                    if(board[i][j]==0) {
                        board[i][j]=3;
                    }
                    else{
                        board[i][j]--;
                        if(board[i][j]==0) {
                            q.offerLast(new Integer[]{i, j});
                        }
                    }
                }
            }

            while(!q.isEmpty()){
                Integer[] temp = q.pollFirst();
                int r = temp[0];
                int c = temp[1];
                for(int d =0; d<4; d++) {
                    int nr = r+dir[0][d];
                    int nc = c+dir[1][d];

                    if(nr<0 || nr>=R || nc<0 || nc>=C) continue;

                    board[nr][nc] = 0;
                }
            }

//            for(int i=0; i<R; i++) {
////                System.out.println(Arrays.toString(board[i]));
////            }
////            System.out.println();



        }
        StringBuilder sb;
        for(int i=0; i<R; i++) {
            sb = new StringBuilder();
            for(int j=0; j<C; j++) {
                if(board[i][j]>0) {
                    sb.append('O');
                }
                else {
                    sb.append('.');
                }
            }
            System.out.println(sb);
        }


    }
}
