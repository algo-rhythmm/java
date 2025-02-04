import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1117_색칠 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long W = Integer.parseInt(st.nextToken());
        long H = Integer.parseInt(st.nextToken());
        long f = Integer.parseInt(st.nextToken());
        long c = Integer.parseInt(st.nextToken());

        long x1 = Integer.parseInt(st.nextToken());
        long y1 = Integer.parseInt(st.nextToken());
        long x2 = Integer.parseInt(st.nextToken());
        long y2 = Integer.parseInt(st.nextToken());


        long cutX = Math.min(f, W-f);

        long sub ;
        if(cutX<x2 && cutX>x1) {
           sub = (cutX-x1)*(y2-y1)* 2* (c+1) + (x2-cutX)*(y2-y1)* (c+1) ;
        }
        else if(cutX <= x1) {
            sub = (x2 - x1)* (y2-y1) * (c+1);
        }
        else {
            sub = 2 * (x2 - x1) * (y2 - y1) * (c + 1);
        }

        System.out.println(W*H - sub);

    }
}
