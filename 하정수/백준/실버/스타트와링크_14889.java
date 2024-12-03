import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *   스타트와 링크 14889 실버1
 *   사람의 수 N
 *   N이 4면 한팀에 2명씩 즉, N/2 명이 한 팀이 된다.
 *   2차원 배열 arr 선언
 *   능력치 차이 min 선언
 *   min이 0이 나오면 탐색 바로 종료
 *
 * */

public class 스타트와링크_14889 {
    static int N, min;
    static int[][] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min = Integer.MAX_VALUE;
        visited = new boolean[N];

        dfs(0, 0); // 재귀로 수열 만들기

        System.out.println(min);

    }

    public static void dfs(int idx, int depth) {
        if (depth == N / 2) { // 만약 팀 구성이 이루어지면
            int team1 = 0;
            int team2 = 0;
            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (visited[i] && visited[j]) { // 방문배열이 true 면 한 팀이므로
                        team1 += arr[i][j];
                        team1 += arr[j][i];
                    } else if (!visited[i] && !visited[j]) { // 방문배열이 false면 한 팀이므로
                        team2 += arr[i][j];
                        team2 += arr[j][i];
                    }
                }
            }
            int sum = Math.abs(team1 - team2); // 음수값이 나오면 안되므로 절대값 치환

            min = Math.min(min, sum); // 최소 능력치 차이 저장

            return;
        }

        for (int i = idx; i < N; i++) { // 반복문으로 재귀 구현
            if (!visited[i]) {
                visited[i] = true;
                dfs(i + 1, depth + 1);
                visited[i] = false;
                if (min == 0) { // 만약 min이 0 이면 탐색할 필요 X
                    return;
                }
            }
        }
    }
}
