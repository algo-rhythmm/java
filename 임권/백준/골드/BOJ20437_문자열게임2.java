import java.io.BufferedReader;
import java.io.InputStreamReader;


// 2584ms
public class BOJ20437_문자열게임2 {

    static int shortest, longest, K;
    static char[] W;
    static int[] count;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            W = br.readLine().toCharArray();
            K = Integer.parseInt(br.readLine());
            
            count = new int[26];
            shortest = Integer.MAX_VALUE;
            longest = Integer.MIN_VALUE;

            for(char c : W) count[c - 'a']++;

            // 시작 알파벳과 끝 알파벳이 같아야 가장 짧다.
            // 가장 긴 문장도 조건이 같다.
            // 짧은 문장은 앞에서 부터 시작 알파벳과 같은 경우 카운트를 통해 가장 빠르게 조건을 만족하는 경우 기록
            // 긴 문장은 뒤에서 부터 시작하여 가장 먼저 조건을 만족하는 경우 기록
            for(int i = 0; i <= W.length - K; i++) {
                int counter = 1;
                int idx = i + 1;
                while(idx < W.length && counter != K) {
                    if(W[i] == W[idx++]) counter++;
                }

                if(counter == K) shortest = Math.min(shortest, idx - i);

                counter = 0;
                idx = W.length - 1;
                while(count[W[i] - 'a'] - counter != K - 1 && idx >= i) {
                    if(W[idx--] == W[i]) counter++;
                }

                if(count[W[i] - 'a'] - counter == K - 1 && idx + 1 < W.length && W[i] == W[idx + 1]) longest = Math.max(longest, idx - i + 2);  // K - 1인 경우는 정답보다 인덱스가 1만큼 앞이므로 보충충

                count[W[i] - 'a']--;
            }

            if(shortest == Integer.MAX_VALUE) sb.append("-1\n");
            else sb.append(shortest + " " + longest + "\n");
        }

        System.out.println(sb);
    }

}
