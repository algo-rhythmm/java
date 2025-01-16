import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 다른 풀이 보고 푼 것
// 224ms

// K개 이상 나타난 문자에 대해서만 계산
// 각 인덱스를 리스트에 담아놓고 순열을 통해 최소 최대 값을 탐색
public class BOJ20437_문자열게임2_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] idxList = new ArrayList[26];
        for(int i = 0; i < 26; i++) idxList[i] = new ArrayList<>();

        for(int t = 0; t < T; t++) {
            char[] W = br.readLine().toCharArray();
            int K = Integer.parseInt(br.readLine());
            int shortest = Integer.MAX_VALUE;
            int longest = Integer.MIN_VALUE;

            for(int i = 0; i < 26; i++) idxList[i].clear();
            
            for(int i = 0; i < W.length; i++) {
                idxList[W[i] - 'a'].add(i);
            }

            for(int i = 0; i < 26; i++) {
                int size = idxList[i].size();
                if(size < K) continue;

                for(int j = 0; j <= size - K; j++) {
                    int len = idxList[i].get(j + K - 1) - idxList[i].get(j) + 1;
                    shortest = Math.min(shortest, len);
                    longest = Math.max(longest, len);
                }
            }

            if(shortest == Integer.MAX_VALUE) sb.append("-1\n");
            else sb.append(shortest + " " + longest + "\n");
        }

        System.out.println(sb);
    }
}
