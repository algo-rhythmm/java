import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
그래프 2개를 쓰면서 완탐을 돌리자
N,M 범위가 널널하니 가능
=====================
입력:
    N,M
    N줄동안 그래프 상태
=====================
먼저 빙산이 분리되어있는지 체크
    빙산 발견시 BFS플러드필로 돌기, 그 후 또다른 빙산을 만나면 정답리턴
    만약 빙산이 하나도 없다면 0리턴
분리안되었다면 각각 빙산을 돌면서 BFS로 상하좌우 0이 몇개인지 체크
graph와 q를 사용해서 얼마나 녹여야하는지 계산후 마이너스값이 안나오게끔하기
flag변수를 사용해서 어떤 그래프가 최신값인지 체크
 */
public class BOJ2573빙산 {
    static int N,M;
    static int[][] graph;
    static int ans=0;
    static Queue<int[]> meltingQ = new ArrayDeque<>();

    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while(!isCollapsed()){
            melting();
            ans++;
        }

        System.out.println(ans);
    }

    public static boolean isCollapsed(){
        boolean[][] visited = new boolean[N][M];
        int cnt = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(graph[i][j]!=0 && !visited[i][j]){
                    visited[i][j] = true;
                    cnt++;
                    floodFill(visited, i, j);
                }
                if(cnt>=2) return true;
            }
        }
        if(cnt==0){
            ans = 0;
            return true;
        }
        return false;
    }

    public static void floodFill(boolean[][] visited,int y, int x){
        Queue<int[]>q = new ArrayDeque<>();
        q.offer(new int[]{y,x});
        while(!q.isEmpty()){
            int[] node = q.poll();
            int cy = node[0];
            int cx = node[1];
            for(int j=0; j<4; j++){
                int ny = cy+dy[j];
                int nx = cx+dx[j];
                if(!isInRange(ny,nx)) continue;
                if(visited[ny][nx]) continue;
                if(graph[ny][nx]==0) continue;
                visited[ny][nx] = true;
                q.offer(new int[]{ny,nx});
            }
        }
    }

    public static void melting(){
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(graph[i][j]!=0){
                    int cnt = 0;
                    for(int k=0; k<4; k++){
                        int ny = i+dy[k];
                        int nx = j+dx[k];
                        if(!isInRange(ny, nx)) continue;
                        if(graph[ny][nx]==0){
                            cnt++;
                        }
                    }
                    if(cnt>=1) meltingQ.offer(new int[]{i, j, cnt});
                }
            }
        }

        while(!meltingQ.isEmpty()){
            int[] node = meltingQ.poll();
            int cy = node[0];
            int cx = node[1];
            int weight = node[2];
            graph[cy][cx] = Math.max(graph[cy][cx]-weight,0);
        }
    }

    public static boolean isInRange(int y, int x){
        return y>=0&&y<N&&x>=0&&x<M;
    }

}
