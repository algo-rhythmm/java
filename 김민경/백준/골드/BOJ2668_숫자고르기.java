import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ2668_숫자고르기 {
    static int[] adjList;
    static boolean[] visit, check;
    static int N, start;
    static boolean cycle;
    static List<Integer> numbers, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        adjList = new int[N + 1];
        check = new boolean[N + 1];
        answer = new ArrayList<>();
        for (int i=1; i<=N; i++) {
            adjList[i] = Integer.parseInt(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        int cnt =0;
        for (int i=1; i<=N; i++) {
            // initialization
            numbers = new ArrayList<>();
            visit = new boolean[N + 1];
            start = i;
            cycle = false;

            visit[i] = true;
            numbers.add(i);
            DFS(start);

            if (cycle && !check[i]) {

                for (int n=0; n<numbers.size(); n++) {
                    check[numbers.get(n)] = true;
                    cnt++;
                    answer.add(numbers.get(n));
                }
            }
        }
        Collections.sort(answer);

        for (int i=0; i<answer.size(); i++) {
            sb.append(answer.get(i)).append("\n");
        }

        System.out.println(cnt);
        System.out.println(sb);

    }

    static void DFS(int n) {

        if (adjList[n] == start) {
            cycle = true;
            return;
        }
        if (visit[adjList[n]]) return;

        numbers.add(adjList[n] );
        visit[adjList[n]] = true;
        DFS(adjList[n]);
    }

}
