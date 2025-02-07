import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18116_로봇조립 {
    static int[] parent, cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st ;
        StringBuilder sb = new StringBuilder();
        parent = new int[1_000_001];
        cnt = new int[1_000_001];

        makeSet();
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            String order = st.nextToken();

            int num1 = Integer.parseInt(st.nextToken());
            if (order.equals("I")) {
                int num2 = Integer.parseInt(st.nextToken());
                union(num1, num2);
            }
            else {
                sb.append(cnt[find(num1)]).append("\n");
            }
        }
        System.out.println(sb);
    }

    static int find(int x) {

        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);

    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if (x!=y) {
            parent[y] = x;
            cnt[x]+=cnt[y];
        }

    }

    static void makeSet() {
        for (int i=1; i<=1_000_000; i++) {
            cnt[i] = 1;
            parent[i] = i;
        }
    }
}


