package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ20055_컨베이어벨트위의로봇 {
    static int N, K;
    static int[] belts;
    static boolean[] robot_on;
    static int cnt=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        robot_on = new boolean[N+1];
        st = new StringTokenizer(br.readLine());

        belts = new int[2*N+1];
        for(int i=1; i<2*N+1; i++) {
            belts[i] = Integer.parseInt(st.nextToken());
        }

        boolean flag = false;
        while(!flag) {
            rotate();
            move_robot();

            put_robot();
            flag = check_flag() ;

            cnt++;
        }
        System.out.println(cnt);
    }

    static void put_robot() {
        if(belts[1]>0) {
            belts[1]--;
            robot_on[1] = true;
        }
    }

    static void rotate (){
        int temp = belts[2*N];

        for(int i=2*N; i>N; i--) {
            belts[i] = belts[i-1];

        }
        for(int i=N; i>1; i--) {
            belts[i] = belts[i-1];
            robot_on[i] = robot_on[i-1];
        }
        belts[1] = temp;
        robot_on[1] = false;


        get_off();

    }

    static void move_robot() {


        for(int i=N-1; i>0; i--) {
            if(robot_on[i]  && belts[i+1] >0 && !robot_on[i+1]) {
                robot_on[i] = false;
                belts[i+1]--;
                robot_on[i+1] = true;
            }
        }
        get_off();
    }

    static void get_off() {
        if(robot_on[N]) robot_on[N] = false;
    }

    static boolean check_flag() {
        int cnt=0;
        for(int i=1; i<=2*N; i++) {
            if(belts[i]==0) cnt++;
        }
        if(cnt>=K) return true;
        else return false;
    }

}
