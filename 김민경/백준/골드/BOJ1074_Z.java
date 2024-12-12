package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1074_Z {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int div = 1;
        for(int i=1; i<N; i++) {
            div *=2;
        }
        int answer =0;
        while(div >0) {
            int quot_r = r / div;
            int quot_c = c / div;

            if(quot_r==0 && quot_c==1) {
                answer += (div*div);
            }
            else if(quot_r==1 && quot_c==0) {
                answer += (div*div*2);
            }
            else if(quot_r==1 && quot_c==1) {
                answer += (div*div*3);
            }
            r %= div;
            c %= div;

            div /=2;
        }

        System.out.println(answer);
    }
}
