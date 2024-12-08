import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    1. 벨트가 한칸 회전 -> 로봇도 옮겨짐
        - 이때 N번칸에 로봇이 가게되면 내려야함
    2. 현재칸에 로봇이 존재하고, 다음칸에 로봇이 존재하지 않고, 다음칸의 내구도가 1이상
        - 움직인다.
    3. 올리는 위치 칸의 내구도가 0이 아니면 올린다.
    4. 내구도가 0인 칸의 개수가 K개 이상이면 종료
        - 칸의 내구도가 0이면 count해준다.

    12952kb | 176ms
 */

public class 컨베이어벨트위의로봇 {

    static int N, K, zero, res;
    static int[] belt;
    static boolean[] isRobot;

    static void moveConveyor() {
        // 2N -> 1번칸
        int last = belt[2 * N - 1];

        for(int i = 2*N-1; i>0; i--){
            belt[i] = belt[i-1];
            // 로봇은 1 ~ N칸에만 존재
            if(i < N){
                isRobot[i] = isRobot[i-1];
            }
        }
        // 2N -> 1번칸
        belt[0] = last;
        isRobot[0] = false;
        if(isRobot[N-1]) isRobot[N-1] = false;

    }

    static void moveRobot() {
        for(int i=N-2; i>=0; i--){
            if(!isRobot[i]) continue;
            if(isRobot[i+1]) continue;;
            if(belt[i+1] < 1) continue;

            belt[i+1]--;
            if(belt[i+1] == 0) zero++;
            isRobot[i+1] = true;
            isRobot[i] = false;
        }

        if(isRobot[N-1]) isRobot[N-1] = false;

        if(belt[0] > 0) {
            isRobot[0] = true;
            belt[0]--;
            if(belt[0] == 0) zero++;
        }
    }

    static void procedure() {
        while(zero < K) {
            moveConveyor();
            moveRobot();
            res++;
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt = new int[2 * N];
        isRobot = new boolean[2 * N];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<2*N; i++){
            belt[i] = Integer.parseInt(st.nextToken());
        }

        procedure();

        System.out.println(res);


    }
}
