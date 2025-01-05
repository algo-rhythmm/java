package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 *   기차가어둠을헤치고은하수를_15787_실버2
 *   비트마스킹을 쓰면 되는 것 같다.
 *
 *   기차를 담을 배열 N
 *
 *   원소 추가 : A |= (1 << k)
 *   원소 삭제 : A &= ~(1 << k)
 *
 *   비트마스킹을 잘 몰라서
 *   https://loosie.tistory.com/238 참고하였다.
 *
 * */

public class 기차가어둠을헤치고은하수를_15787 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] trains = new int[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int trainNum = Integer.parseInt(st.nextToken()) - 1;

            int seatNum = 0;

            switch (m) {
                case 1:
                    seatNum = Integer.parseInt(st.nextToken()) - 1;
                    trains[trainNum] |= (1 << seatNum);
                    break;

                case 2:
                    seatNum = Integer.parseInt(st.nextToken()) - 1;
                    trains[trainNum] &= ~(1 << seatNum);
                    break;

                case 3:
                    trains[trainNum] = trains[trainNum] << 1;
                    if(trains[trainNum] >= 1048576){ // 21번째 칸까지 갔다면 (비트가 int 형은 32bit 이기 때문에 21번째 칸은 존재하지 않음)
                        trains[trainNum] = trains[trainNum] - 1048576;
                    }
                    break;

                case 4:
                    trains[trainNum] = trains[trainNum] >> 1;
                    break;
            }
        }

        ArrayList<Integer> record = new ArrayList();

        int count = 0;
        for (int i = 0; i < trains.length; i++) {
            if (record.contains(trains[i])) continue;

            count++;
            record.add(trains[i]);
        }

        System.out.println(count);
    }
}
