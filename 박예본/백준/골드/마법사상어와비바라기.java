import java.util.*;
import java.io.*;
public class 마법사비바라기 {
    static int[] dr = {0,0,-1,-1,-1,0,1,1,1};
    static int[] dc = {0,-1,-1,0,1,1,1,0,-1};
    static int[] rr = {-1,-1,1,1};
    static int[] rc = {-1,1,-1,1};
    public static class Space{
        int r;
        int c;
        public Space(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 맵 크기
        int M = Integer.parseInt(st.nextToken()); // 명령 갯수

        int[][] map = new int[N][N]; // 맵

        for(int r = 0; r<N; r++){ // 맵 초기화
            st = new StringTokenizer(br.readLine());
            for(int c =0; c<N; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

//        Queue<Space> q = new LinkedList();
//        q.add(new Space(N-1,0));

        List<Space> list = new LinkedList<>();
        boolean[][] rained = new boolean[N][N];

        list.add(new Space(N-1,0));
        list.add(new Space(N-1,1));
        list.add(new Space(N-2,0));
        list.add(new Space(N-2,1));

        for(int m = 0; m<M; m++){ //

            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()); // 가는 방향
            int s = Integer.parseInt(st.nextToken()); // 가는 거리

            for(Space tmp : list){ //위치 이동 및 비 내리기
//                tmp.r = Math.abs(((tmp.r+(dr[d]*s))) % N);
//                tmp.c = Math.abs(((tmp.c+(dc[d]*s))) % N);
                tmp.r = (N*s+(tmp.r+(dr[d] * s))) % N;
                tmp.c = (N*s+(tmp.c+(dc[d] * s))) % N;
                map[tmp.r][tmp.c]++;
                rained[tmp.r][tmp.c] = true;
//                System.out.println(tmp.r+" "+tmp.c);
            }
            for(Space tmp : list){ //대각선 비 모으기
                for(int k = 0; k<4; k++){
                    int tmpR = tmp.r+rr[k];
                    int tmpC = tmp.c+rc[k];
                    if(tmpR >= 0 && tmpR < N && tmpC >=0 && tmpC < N && map[tmpR][tmpC] > 0 ){
                        map[tmp.r][tmp.c]++;
//                        System.out.println(tmpR+" "+tmpC);
                    }
                }

            }

            list = new LinkedList<>();

            for(int r = 0; r<N; r++){ // 맵 초기화
                for(int c =0; c<N; c++){
                    if(!rained[r][c] && map[r][c] > 1){
                        map[r][c] -= 2;
                        list.add(new Space(r,c));
                    }
                }
            }
            rained = new boolean[N][N];
//            for(int r = 0; r<N; r++){ // 맵 초기화
//                System.out.println();
//                for(int c =0; c<N; c++){
//                    System.out.print(map[r][c]+" ");
//                }
//            }
//            System.out.println();


        }
        int result = 0;

        for(int r = 0; r<N; r++){ // 맵 초기화
            for(int c =0; c<N; c++){
                result+= map[r][c];
            }
        }
        System.out.println(result);


    }
}
