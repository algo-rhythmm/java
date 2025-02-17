import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1027_고층건물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int i=0; i<N; i++) {
            int j=i-1;
            double sloop = Integer.MAX_VALUE, cmp = 0;
            int cnt =0;
            while (j>=0) {
                cmp = (double) (arr[i] - arr[j]) / (i - j);
                if (cmp< sloop) {
                    sloop = cmp;
                    cnt++;

                }
                j--;

            }
            j = i+1;
            sloop = Integer.MIN_VALUE;
            while (j<N) {
                cmp = (double) (arr[i] - arr[j]) / (i - j);
                if (cmp > sloop) {
                    sloop = cmp;
                    cnt++;

                }
                j++;

            }
            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }
}
