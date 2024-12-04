import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *   암호만들기_1759_골드5
 *   암호는 최소 한개의 모음과 최소 두개의 자음의 알파벳으로 구성
 *   암호는 무조건 상승하는 구조로 구성
 *
 *   int형 L,C 선언
 *   int형 배열 visited 배열 선언 후 입력받은 값이 있고 자음이면 1, 모음이면 2로 초기화, 순열조합때는 방문했으면 3로 초기화
 *   int형 자음과 모음 count 선언
 *   재귀를 통해 순열조합하는 과정에서 count 증가
 *
 *
 *
 * */

public class 암호만들기_1759 {
    static int L, C;
    static int[] visited = new int[26];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            String str = st.nextToken();
            char c = str.charAt(0);
            int tmp = c - 'a';
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') { // 모음이면 2로 초기화
                visited[tmp] = 2;
            } else { // 자음이면 1로 초기화
                visited[tmp] = 1;
            }
        }

        dfs(0, 0, 0, 0); // 총 카운트, 자음 카운트, 모음 카운트, 현재 idx

        System.out.println(sb);

    }

    public static void dfs(int count, int con_count, int vow_count, int idx) {
        if (count == L && con_count >= 2 && vow_count >= 1) {
            for (int i = 0; i < 26; i++) { // 순서대로 돌면서 3이면 sb에 추가
                if (visited[i] == 3) {
                    char tmp = (char) ('a' + i);
                    sb.append(tmp);
                }
            }
            sb.append("\n");
            return;
        }

        for (int i = idx; i < 26; i++) {
            if (visited[i] == 0 || visited[i] == 3) continue;
            if (visited[i] == 1) {
                visited[i] = 3;
                dfs(count + 1, con_count + 1, vow_count, i);
                visited[i] = 1;
                continue;
            } else if (visited[i] == 2) {
                visited[i] = 3;
                dfs(count + 1, con_count, vow_count + 1, i);
                visited[i] = 2;
                continue;
            }
        }
    }
}
