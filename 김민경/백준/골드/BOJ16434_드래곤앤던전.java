import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16434_드래곤앤던전 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long sp = Integer.parseInt(st.nextToken());

        int [][] cmd = new int[N][3];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            cmd[i][0] = Integer.parseInt(st.nextToken());
            cmd[i][1] = Integer.parseInt(st.nextToken());
            if (cmd[i][0] == 2) sp += cmd[i][1];
            cmd[i][2] = Integer.parseInt(st.nextToken());
        }

        long power = 0;
        long answer = 0;

        int i=N-1;
        while (i>=0) {

            while (i>=0 && cmd[i][0] ==1) {
                power += (long) cmd[i][1] *((int) Math.ceil( (double) cmd[i][2]/sp) -1) ;
                i--;
            }

            answer = Math.max(power, answer);

            if (i>=0 && cmd[i][0]==2) {
                sp -= cmd[i][1];
                power -= cmd[i][2];
                if (power<0) power =0;
                i--;
            }

        }


        System.out.println(answer+1);



    }
}
