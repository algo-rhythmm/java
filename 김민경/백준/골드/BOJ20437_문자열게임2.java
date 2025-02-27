import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ20437_문자열게임2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        char[] arr;
        List<Integer>[] posList = new List[26];
        for (int t=0; t<T; t++) {

            for (int i=0; i<26; i++) {
                posList[i] = new ArrayList<>();
            }

            arr = br.readLine().toCharArray();
            int n = Integer.parseInt(br.readLine());
            int min = Integer.MAX_VALUE;
            int max = 0;
            for (int i=0; i<arr.length; i++) {
                int j = arr[i] - 'a';
                posList[j].add(i);
                if (posList[j].size() == n) {
                    int cmp = posList[j].get(n - 1) - posList[j].get(0) + 1;
                    min = Math.min(cmp, min);
                    max = Math.max(cmp, max);

                    posList[j].remove(0);
                }
            }

            StringBuilder sb = new StringBuilder();
            if (min == Integer.MAX_VALUE) sb.append("-1");
            else sb.append(min).append(" ").append(max);
            System.out.println(sb);
        }



    }
}
