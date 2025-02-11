package algo0207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    317308KB | 1152ms
 */
public class 로봇조립 {

    static int N;
    static int[] parent;
    static int[] res;

    static void init() {
        for(int i=1; i<=1000000; i++){
            parent[i] = i;
            res[i] = 1;
        }
    }

    static int find(int x) {
        if(parent[x] != x){
            return parent[x] = find(parent[x]);
        }

        return x;
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x != y) {
            if(x < y) {
                parent[y] = x;
                res[x] += res[y];
            }
            else {
                parent[x] = y;
                res[y] += res[x];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(br.readLine());
        parent = new int[1000001];
        res = new int[1000001];
        init();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            if(command.equals("I")) {
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }
            else if(command.equals("Q")) {
                int x = find(a);
                sb.append(res[x] + "\n");
            }


        }

        System.out.println(sb);
    }
}
