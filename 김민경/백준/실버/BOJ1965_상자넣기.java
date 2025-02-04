import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1965_상자넣기 {



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] box = new int[N];
        int[] wall = new int[N];
        int[] cnt = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i=0; i<N; i++) {
            box[i] = Integer.parseInt(st.nextToken());
        }

        wall[0] = box[0];
        cnt[0] = 1;

        for (int i=1; i<N; i++) {
            for (int j=0; j<=i; j++) {
                if(wall[j] < box[i] && cnt[j]+1 > cnt[i]) {
                    wall[i] = box[i];
                    cnt[i] = cnt[j]+1;
                }
            }
        }

        int answer =0;
        for(int i=0; i<N; i++) {
            answer = Math.max(answer, cnt[i]);
        }

//        System.out.println(Arrays.toString(wall));
//        System.out.println(Arrays.toString(cnt));
        System.out.println(answer);
    }


}
