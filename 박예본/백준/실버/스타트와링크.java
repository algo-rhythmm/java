import java.io.*;
import java.util.StringTokenizer;

public class 스타트와링크 {

    static int size;
    static int result;
    static boolean[] vis;
    static boolean check;
    static int[][] power;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        check = false;
        result = Integer.MAX_VALUE;
        size = Integer.parseInt(br.readLine());
        vis = new boolean[size+1];

        //초기화 로직
        power = new int[size+1][size+1];
        for(int r = 1; r <= size; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 1; c<=size; c++){
                int tem = Integer.parseInt(st.nextToken());
                power[r][c]+= tem;
                power[c][r]+= tem;
            }
        }

        //조합
        combi(1,0);

        System.out.println(result);
    }

    private static void combi(int start, int cnt) {

        if(check) return;

        if( cnt == size / 2){ // 다 돌았다는 뜻
            result = Math.min(result,score());
            if(result == 0) {check = true;}
            return;
        }

        if( start == size){//끝까지 갔다는 뜻
            return;
        }

        for(int i = start; i<= size; i++){ // 과거는 묻어둬 이게 맞나?
            if(vis[i]) continue; // 만약 이미 방문 했다면
            //그냥 넘어가기
            vis[i] = true; // 방문 처리
            combi(i + 1, cnt + 1); // 다음 재귀 호출
            vis[i] = false; // 백트래킹


        }


    }

    private static int score() {
        int a = 0, b = 0;
        for(int i = 1; i<=size; i++){
            if(vis[i]){ //a팀으로 만약
                for(int k = i + 1; k <= size; k++){
                    if(vis[k]) a += power[i][k];
                }
            }else{
                for(int k = i + 1; k <= size; k++){
                    if(!vis[k]) b += power[i][k];
                }
            }
        }
        return Math.abs(a-b);
    }


}
