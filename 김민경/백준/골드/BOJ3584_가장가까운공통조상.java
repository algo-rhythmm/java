import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;



public class BOJ3584_가장가까운공통조상 {

    static int[] parent ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int t=1; t<=T; t++) {
            int N = Integer.parseInt(br.readLine());
            parent = new int[N + 1];



            for (int i=0; i<N-1; i++) {

                st = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                parent[c] = p;
            }

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            boolean[] parents = new boolean[N + 1];
            parents[a] = true;
            while (parent[a]!=0) {
                int p = parent[a];
                parents[p] = true;
                a = p;
            }

            while (!parents[b]) {
                b = parent[b];
            }
            System.out.println(b);

        }

    }
}
