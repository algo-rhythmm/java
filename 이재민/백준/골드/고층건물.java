package algo0217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    1027 고층건물
    11580KB | 68ms
 */
public class 고층건물 {

    static int N;
    static int[] buildings;
    static int res = 0;

    static int getCnt(int x) {
        double ls = Double.POSITIVE_INFINITY;
        double rs = Double.NEGATIVE_INFINITY;

        int cnt = 0;

        for(int i = x - 1; i >= 0; i--) {
            double gradient = (double) (buildings[x] - buildings[i]) / (x - i);
            if(gradient < ls) {
                cnt++;
                ls = gradient;
            }

        }

        for(int i = x + 1; i < N; i++) {
            double gradient = (double) (buildings[i] - buildings[x]) / (i - x);
            if(gradient > rs) {
                cnt++;
                rs = gradient;
            }
        }


        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        buildings = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++) {
            int cnt = getCnt(i);
            res = Math.max(res, cnt);
        }

        System.out.println(res);
    }


}
