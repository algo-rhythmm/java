
import java.util.*;
import java.io.*;

    public class 기차가어둠을헤치고은하수를 {
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 기차의 수
            int M = Integer.parseInt(st.nextToken()); // 명령의 수

            long[] train = new long[N]; // 각 기차의 상태를 비트로 저장
            Set<Long> vis = new HashSet<>(); // 고유 상태를 저장할 Set

            for (int t = 0; t < M; t++) {
                st = new StringTokenizer(br.readLine());
                String order = st.nextToken(); // 명령어
                int tmp = Integer.parseInt(st.nextToken()) - 1; // 기차 인덱스 (1-based -> 0-based)

                switch (order) {
                    case "1": // x번째 좌석에 탑승
                        int x = Integer.parseInt(st.nextToken());
                        train[tmp] |= (1 << (x - 1));
                        break;

                    case "2": // x번째 좌석에서 하차
                        x = Integer.parseInt(st.nextToken());
                        train[tmp] &= ~(1 << (x - 1));
                        break;

                    case "3": // 뒤로 밀기
                        train[tmp] = (train[tmp] << 1) & ((1 << 20) - 1); // 상위 비트를 초과하지 않도록 마스크 적용
                        break;

                    case "4": // 앞으로 당기기
                        train[tmp] >>= 1;
                        break;
                }
            }

            for (int k = 0; k < N; k++) {
                vis.add(train[k]); // 고유 상태 저장
            }

            System.out.println(vis.size()); // 고유한 기차 상태의 개수 출력
        }
    }
