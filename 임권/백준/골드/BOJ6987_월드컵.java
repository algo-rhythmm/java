import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ6987_월드컵 {
    static BufferedReader br;
    static StringTokenizer st;
    static int N,M,Ans = Integer.MAX_VALUE,sum;
    static int[] win, draw, lose;
    static boolean[][] v;
    static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
    static boolean flag;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            flag = false;
            sum = 0;
            win = new int[6];
            draw = new int[6];
            lose = new int[6];
            for (int j = 0; j < 6; j++) {
                win[j] = Integer.parseInt(st.nextToken());
                draw[j] = Integer.parseInt(st.nextToken());
                lose[j] = Integer.parseInt(st.nextToken());
                int num = win[j] + draw[j] + lose[j];
                if(num != 5){
                    break;
                }
                sum += num;
            }

            if(sum == 30) {
                dfs(0, 1, 0);
            }

            System.out.print((flag ? 1:0) + " ");
        }


    }

    private static void dfs(int team, int idx, int cnt) {
        if(flag) {
            return;
        }
        if(cnt == 15) {
            flag = true;
            return;
        }

        if(cnt == 5) {
            team++;
            idx = 1;
        }else if(cnt == 9) {
            team++;
            idx = 1;
        }else if(cnt == 12) {
            team++;
            idx = 1;
        }else if(cnt == 14) {
            team++;
            idx = 1;
        }

        if(win[team]>0 && lose[team+idx] > 0 ) {
            win[team]--;
            lose[team+idx]--;
            dfs(team,idx+1,cnt+1);
            win[team]++;
            lose[team+idx]++;
        }

        if(win[team+idx]>0 && lose[team] > 0 ) {
            win[team+idx]--;
            lose[team]--;
            dfs(team, idx + 1, cnt + 1);
            win[team+idx]++;
            lose[team]++;
        }

        if(draw[team+idx]>0 && draw[team] > 0 ) {
            draw[team]--;
            draw[team+idx]--;
            dfs(team, idx + 1, cnt + 1);
            draw[team]++;
            draw[team+idx]++;
        }
    }
}