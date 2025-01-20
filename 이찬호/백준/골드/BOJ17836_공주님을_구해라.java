import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
N,M크기 성에 0,0부터 시작, N-1, M-1로 도착해야함
T시간 이내로 구출
용사는 그람을 얻었을 경우 벽을 부수고 이동할 수 있다.(갯수 제한없이)
구할수 없다면 Fail, 있다면 최단시간으로 구해보자
===========================
입력:
    N, M, T (3<= N, M <= 100) (1<=T<=10,000)
===========================
범위 벗어나는지 체크
벽인지 체크
    벽이라면 그람이 있는지 체크
갔던 곳인지 체크
    int[][] 배열로 거기 갔을때까지 도달한 시간 체크
그람인지 체크
    그람먹으면 그람 변수에 저장해놓기.
위 행위를 T번동안 반복, 만약 실패시 fail 출력.
===========================
칼집었을때 돌아온 길을 다시 되돌아가야하는 경우도 생긴다 (그게 더 늦은 경우라도)
 */
public class BOJ17836공주님 {
    static int N,M,T;
    static int[][] map;
    static int[][] visited, gramrVisited;

    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};

    static class Warrior{
        int y, x, step;
        boolean gramr;
        public Warrior(int y, int x, int step, boolean gramr) {
            this.y = y;
            this.x = x;
            this.step = step;
            this.gramr = gramr;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new int[N][M];
        gramrVisited = new int[N][M];

        for(int i=0; i<N; i++){
            Arrays.fill(visited[i], 10001);
            Arrays.fill(gramrVisited[i], 10001);
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        BFS();



    }

    private static void BFS(){
        Queue<Warrior> q = new ArrayDeque<>();
        q.offer(new Warrior(0,0,0, false));
        visited[0][0] = 0;
        int cnt = 0;

        // ||가 아니라 &&
        while(!q.isEmpty()&&cnt<=T){
            int size = q.size();
            for(int i=0; i<size; i++){
                Warrior w = q.poll();
                int cy = w.y;
                int cx = w.x;

                for(int j=0; j<4; j++){
                    int ny = cy+dy[j];
                    int nx = cx+dx[j];
                    if(!isInRange(ny, nx)) continue;
                    if(ny==N-1&&nx==M-1){
                        System.out.println(w.step+1);
                        return;
                    }
                    if(w.gramr){
                        if(gramrVisited[ny][nx]<=w.step+1) continue;
                    }
                    else{
                        if(visited[ny][nx]<=w.step+1) continue;
                    }
                    if(map[ny][nx]==0){
                        q.offer(new Warrior(ny, nx, w.step+1, w.gramr));
                        if(w.gramr){
                            gramrVisited[ny][nx] = w.step+1;
                        } else{
                            visited[ny][nx] = w.step+1;
                        }
                    }
                    else if(map[ny][nx]==1){
                        if(w.gramr){
                            q.offer(new Warrior(ny, nx, w.step+1, w.gramr));
                            gramrVisited[ny][nx] = w.step+1;
                        }
                    }
                    else if(map[ny][nx]==2){
                        q.offer(new Warrior(ny, nx, w.step+1, true));
                        visited[ny][nx] = w.step+1;
                        gramrVisited[ny][nx] = w.step+1;
                    }
                }

            }
            cnt++;
        }
        System.out.println("Fail");
    }

    public static boolean isInRange(int y, int x){
        return y>=0&&y<N&&x>=0&&x<M;
    }
}
