package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 *   배_1092_골드5
 *
 *   모든 화물은 박스안에 넣어져있다.
 *   항구에는 크레인이 N대 있고 1분에 박스를 하나씩 배에 실을 수 있다. 모든 크레인은 동시에 움직인다.
 *   각 크레인은 무게제한이 있다.
 *   이 무게제한보다 무거운 박스는 크레인으로 움직일 수 없다.
 *   모든 박스를 배로 옮기는데 드는 시간의 최솟값을 구하는 프로그램
 *
 *   LinkedList에 박스들을 큰 순서대로 담아놓는다.
 *   크레인은 ArrayList로 담아놔서 큰 순서대로 정렬한다.
 *
 *   이렇게 했더니 메모리초과가 뜬다.
 *   그냥 ArrayList로 하고 visited 배열을 써서 쓴 값은 true 처리
 *
 * */

public class 배_1092 {
    static int N, M;
    static ArrayList<Integer> crane = new ArrayList<>();
    static ArrayList<Integer> box = new ArrayList<>();

    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());


        for (int i = 0; i < N; i++) {
            crane.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(crane, Collections.reverseOrder());

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        visited = new boolean[M];

        for (int i = 0; i < M; i++) {
            box.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(box, (o1, o2) -> { // 큰 순서대로 정렬!
            return o2 - o1;
        });

        if (box.get(0) > crane.get(0)) {
            System.out.println(-1);
            return;
        }

        int time = 0;
        int count = 0;

        while (count < M) {
            int idx = 0;
            for (int i = 0; i < M; i++) {
                if (box.get(i) == -1) continue;

                if (crane.get(idx) >= box.get(i)) {
                    box.set(i, -1);
                    count++;
                    idx++;
                }

                if (idx == N) break;

            }
            time++;
        }

        System.out.println(time);
    }
}
