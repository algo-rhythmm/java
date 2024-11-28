import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * int형 N 선언
 * N크기의 string 배열 선언
 * 단어들의 앞자리 판별(띄어쓰기 기준)
 *
 * 알파벳 저장할 visited 배열 선언
 *
 * StringTokenizer 에 너무 시간을 쏟았다..
 *
 * */

public class 단축키지정_1283 {
    static int N;
    static String[] arr;
    static boolean[] visited = new boolean[26]; // 알파벳 갯수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new String[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            boolean q = false;
            while (st.hasMoreTokens()) {
                String str = st.nextToken();
                if (q) {
                    arr[i] = arr[i] + " " + str;
                    continue;
                }
                char s = str.charAt(0);
                if (s >= 'a') {
                    if (!visited[s - 'a']) {
                        visited[s - 'a'] = true;
                        q = true;
                        if (arr[i] != null) {
                            arr[i] = arr[i] + " [" + s + "]" + str.substring(1);
                        } else {
                            arr[i] = "[" + s + "]" + str.substring(1);
                        }
                        continue;
                    }
                } else {
                    if (!visited[s - 'A']) {
                        visited[s - 'A'] = true;
                        q = true;
                        if (arr[i] != null) {
                            arr[i] = arr[i] + " [" + s + "]" + str.substring(1);
                        } else {
                            arr[i] = "[" + s + "]" + str.substring(1);
                        }
                        continue;
                    }
                }
                if (arr[i] != null) {
                    arr[i] = arr[i] + " " + str;
                } else {
                    arr[i] = str;
                }
            }
            if (!q) {
                for (int j = 0; j < arr[i].length(); j++) {
                    char s = arr[i].charAt(j);
                    String aa = String.valueOf(s);
                    if (s == ' ') {
                        continue;
                    }
                    if (s >= 'a') {
                        if (!visited[s - 'a']) {
                            visited[s-'a'] = true;
                            String ss = "[" + s + "]";
                            arr[i] = arr[i].replaceFirst(aa, ss);
                            break;
                        }
                    } else {
                        if (!visited[s - 'A']) {
                            visited[s-'A'] = true;
                            String ss = "[" + s + "]";
                            arr[i] = arr[i].replaceFirst(aa, ss);
                            break;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(arr[i]);
        }
    }
}
