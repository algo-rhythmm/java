import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1759_암호만들기 {

    static int L, C;
    static char[] arr;
    static boolean[] check = new boolean[26];   //자음 모음 여부 체크
    static StringBuilder sb = new StringBuilder();
    // 사전순 -> 조합
    // 최소 한개의 모음 그리고 최소 두개의 자음이 포함되어야만한다.
    // 한 알파벳이 여러번 나오지 않는다.
    public static void main(String[] args) throws IOException {
        //자음 표시
        check['a' - 'a'] = true;
        check['e' - 'a'] = true;
        check['i' - 'a'] = true;
        check['o' - 'a'] = true;
        check['u' - 'a'] = true;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        //사전순으로 정렬하기
        Arrays.sort(arr);

        combination(0, -1, 0, new char[L]);

        System.out.println(sb);

    }

    // idx - 선택된 알파벳 수, prev - 이전에 선택한 알파벳, count - 자음 갯수, selected - 선택된 알파벳
    static void combination(int idx, int prev, int count, char[] selected) {
        if(idx == L) {
            // 최소 한개의 모음 그리고 최소 두개의 자음이 포함되는지 체크
            if(count == 0 || count > L - 2) return;

            for(int i = 0; i < L; i++) sb.append(selected[i]);
            sb.append("\n");
            return;
        }

        // 조합 생성
        for(int i = prev + 1; i < C; i++) {
            selected[idx] = arr[i];
            combination(idx + 1, 
                        i, 
                        check[arr[i] - 'a'] ? count + 1 : count, //자음인 경우 count를 증가시킨다.
                        selected
                    ); 
        }

    }
}
