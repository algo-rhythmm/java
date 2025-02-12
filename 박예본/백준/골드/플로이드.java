import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[][] busLine = new int[N+1][N+1];
        int M = Integer.parseInt(br.readLine());
        int INF = 100000001;
        for(int a = 0; a<=N; a++){
        Arrays.fill(busLine[a],INF);
        busLine[a][a]= 0;
        }

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            busLine[from][to] = Math.min(cost,busLine[from][to]);
        }

        for(int m = 1; m<=N; m++){ //경유지
            for(int s = 1; s<=N; s++){
                for(int e = 1; e<=N; e++){
                    busLine[s][e] = Math.min(busLine[s][e],busLine[s][m]+busLine[m][e]);
                }
            }
        }

        for(int r =1; r<=N; r++){

            for(int c =1; c<=N; c++){
                sb.append(busLine[r][c] == INF ? 0 : busLine[r][c]).append(" ");

            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
}
