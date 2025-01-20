import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1389_케빈베이컨의6단계법칙 {
    static int answer = Integer.MAX_VALUE;
    static int N;
    static int k;
    static List<Integer>[] edgeList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList[N+1];
        for (int i=1; i<=N; i++) {
            edgeList[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            if (!edgeList[num1].contains(num2)) edgeList[num1].add(num2);
            if (!edgeList[num2].contains(num1)) edgeList[num2].add(num1);

        }

        for (int i=1; i<=N; i++) {
            set_connection(i);
        }

        System.out.println(k);

    }

    static void set_connection(int start) {
        boolean[] visit = new boolean[N+1];
        int[] connectivity = new int[N + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visit[start] = true;

        int dist = 1;


        while (!queue.isEmpty()) {
            int size = queue.size();

            for(int i=0; i<size; i++) {
                int temp = queue.poll();

                for (int j=0; j<edgeList[temp].size(); j++) {

                    int num = edgeList[temp].get(j);
                    if(visit[num]) continue;
                    queue.add(num);
                    visit[num] = true;
                    connectivity[num] = dist;
                }

            }
            dist++;

        }

        System.out.println(Arrays.toString(connectivity));

        int cmp =0;

        for(int i=1; i<=N; i++) {
            cmp += connectivity[i];
            if (cmp>=answer) break;
        }
        if(cmp<answer) {
            answer = cmp;
            k = start;
        }


    }
}
