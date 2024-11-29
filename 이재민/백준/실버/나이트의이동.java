import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
	bfs 39112KB | 200ms
*/

public class Main {
    static int T, N;
    static int fx, fy, lx, ly;
    static boolean[][] visited;

    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    static class Loc {
        int x, y, dist;

        public Loc(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    static boolean rangeCheck(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static int bfs() {
        Queue<Loc> q = new ArrayDeque<>();

        q.add(new Loc(fx, fy, 0));
        visited[fx][fy] = true;

        while(!q.isEmpty()){
            Loc loc = q.poll();
            if(loc.x == lx && loc.y == ly){
                return loc.dist;
            }

            for(int d=0; d<8; d++){
                int nx = loc.x + dx[d];
                int ny = loc.y + dy[d];
                if(!rangeCheck(nx, ny)) continue;
                if(visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.add(new Loc(nx, ny, loc.dist + 1));
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuffer sb = new StringBuffer();

        T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            fx = Integer.parseInt(st.nextToken());
            fy = Integer.parseInt(st.nextToken());

            st  = new StringTokenizer(br.readLine());
            lx = Integer.parseInt(st.nextToken());
            ly = Integer.parseInt(st.nextToken());

            visited = new boolean[N][N];

            sb.append(bfs()).append('\n');
        }

        System.out.println(sb);
    }

}