import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11054_가장긴바이토닉부분수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] down = new int[N];
        int[] up = new int[N];

        Arrays.fill(up, 1);
        Arrays.fill(down, 1);

        for (int i=1; i<N; i++) {
            for (int j=0; j<i; j++) {

                if (nums[i] > nums[j])
                    up[i] = Math.max(up[i], up[j]+1);
                if (nums[N-1-i] > nums[N-1-j])
                    down[N-1-i] = Math.max(down[N-1-i], down[N-1-j]+1);

            }
        }

        int answer =0;

        for (int i=0; i<N; i++){
            answer = Math.max(up[i] + down[i], answer);
        }
        System.out.println(answer-1);
    }
}
