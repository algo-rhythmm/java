import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 내려가기_2096 {
    static int N;
    static int[] max, min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        min = new int[3];
        max = new int[3];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 3; i++) {
            min[i] = Integer.parseInt(st.nextToken());
            max[i] = min[i];
        }

        for (int n = 1; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int[] down = new int[3];
            for (int i = 0; i < 3; i++) {
                down[i] = Integer.parseInt(st.nextToken());
            }
            int[] tmp;

            tmp = min.clone();

            min[0] = Math.min(tmp[0], tmp[1]) + down[0];
            min[1] = Math.min(tmp[0], Math.min(tmp[1], tmp[2])) + down[1];
            min[2] = Math.min(tmp[1], tmp[2]) + down[2];

            tmp = max.clone();

            max[0] = Math.max(tmp[0], tmp[1]) + down[0];
            max[1] = Math.max(tmp[0], Math.max(tmp[1], tmp[2])) + down[1];
            max[2] = Math.max(tmp[1], tmp[2]) + down[2];
        }

        int max_result = Math.max(max[0], Math.max(max[1], max[2]));
        int min_result = Math.min(min[0], Math.min(min[1], min[2]));

        System.out.println(max_result + " " + min_result);
    }
}
