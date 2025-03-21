import java.io.*;
import java.util.*;

public class 두_동전 {

    public static class Space{
        int f_r;
        int f_c;
        int s_r;
        int s_c;
        int cnt;
        public Space(int fr, int fc,int sr,int sc, int cnt ){
            this.f_r = fr;
            this.f_c = fc;
            this.s_r = sr;
            this.s_c = sc;
            this.cnt = cnt;
        }
    }

    static int dr[] = {0,0,1,-1};
    static int dc[] = {1,-1,0,0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int result = 0;

        int[][] map = new int[R][C];
        Queue<Space> q = new LinkedList<>();

        List<int[]> list = new ArrayList<>();

        for(int r = 0; r<R; r++){
            char[] tmp = br.readLine().toCharArray();
            for(int c = 0; c<C; c++){
                if(tmp[c]=='o'){
                    list.add(new int[] {r,c});
                }else if(tmp[c]=='.'){

                }else{
                    map[r][c]=1;
                }
            }
        }
        q.add(new Space(list.get(0)[0],list.get(0)[1],list.get(1)[0],list.get(1)[1],0));

        all :while(!q.isEmpty()){
            Space tmp = q.poll();
            if(tmp.cnt>10){ //생각 잘 하기
                result = -1;
                break;
            }

//            if(tmp.r == 0 || tmp.c ==R-1 || tmp.c ==0||tmp.c ==C-1){ //끝에 있다면
//                result = tmp.cnt+1;
//                break;
//            }
//            System.out.println(tmp.f_r+" "+tmp.f_c+" "+tmp.s_r+" "+tmp.s_c);
            for(int i = 0; i<4; i++){

                int Fr = tmp.f_r+dr[i];
                int Fc = tmp.f_c+dc[i];
                int Sr = tmp.s_r+dr[i];
                int Sc = tmp.s_c+dc[i];
//                System.out.println(Fr+" "+Fc+" "+Sr+" "+ Sc +" 일반요 ");
                if( Fr < 0 || Fr >= R || Fc < 0||Fc >= C ||Sr < 0 || Sr >= R || Sc < 0 || Sc >= C ){ //끝나는 조건
                    //근데 안끝나는 조건
                    if(Fr < 0 && Sr < 0 || Fr >= R && Sr >= R || Fc < 0 && Sc < 0 || Fc >= C && Sc >= C ){
                        continue;
                    }
                    result = tmp.cnt+1;
                    break all;
                }
                if(map[Fr][Fc] == 1){ Fr = tmp.f_r; Fc = tmp.f_c; }
                if(map[Sr][Sc] == 1){ Sr = tmp.s_r; Sc = tmp.s_c; }
                q.add(new Space (Fr,Fc,Sr,Sc,tmp.cnt+1));
            }

        }

        System.out.println(result);
    }

}
