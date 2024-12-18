import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 상범빌딩 {

    static int dr[] = {1,-1,0,0,0,0}; //가로
    static int dc[] = {0,0,1,-1,0,0}; //세로
    static int ds[] = {0,0,0,0,1,-1}; //상하

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while(true) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            if(L==0&&R==0&&C==0){return;}

            int[][][] map = new int[L][R][C];
            int[] start = null;
            int[] end = null;

            for (int s = 0; s < L; s++) {
                //빈칸 비워야 하나?

                for (int r = 0; r < R; r++) {
//                st = new StringTokenizer(br.readLine());
                    char[] tmp = br.readLine().toCharArray();
                    for (int c = 0; c < C; c++) {
                        switch (tmp[c]) {
                            case '.':
                                break;
                            case '#':
                                map[s][r][c] = 1;
                                break;
                            case 'S':
                                map[s][r][c] = 1;
                                start = new int[]{s, r, c, 0};
                                break;
                            case 'E':
                                end = new int[]{s, r, c};
                                break;
                        }
                    }
                }
            br.readLine(); //수 버리기
            }

            if (start == null) {
                System.out.println("Trapped!");
                return;
            }
            Queue<int[]> q = new LinkedList<>();
            q.add(start);

            int result = -1;

            while (!q.isEmpty()) {
                int[] tmp = q.poll();
//            System.out.println(tmp[0]+" "+tmp[1]+" "+tmp[2]+" "+tmp[3]+" ");
                //정답 검정식
                if (tmp[0] == end[0] && tmp[1] == end[1] && tmp[2] == end[2]) {
                    result = tmp[3];
                    break;
                }
                tmp[3]++;
                for (int i = 0; i < 6; i++) {
                    int tmpS = tmp[0] + ds[i];
                    int tmpR = tmp[1] + dr[i];
                    int tmpC = tmp[2] + dc[i];
//                System.out.println(i);
                    if (tmpS >= 0 && tmpS < L && tmpR >= 0 && tmpR < R &&
                            tmpC >= 0 && tmpC < C && map[tmpS][tmpR][tmpC] == 0) { //조건식
                        map[tmpS][tmpR][tmpC] = 1;
                        q.add(new int[]{tmpS, tmpR, tmpC, tmp[3]});
                    }

                }

            }

            if (result == -1) {
                System.out.println("Trapped!");
            } else {
                System.out.println("Escaped in " + result + " minute(s).");
            }
        }
    }
}
