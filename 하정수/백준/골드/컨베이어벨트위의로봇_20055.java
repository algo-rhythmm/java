import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 *   컨베이어벨트위의로봇_20055_골드5
 *
 * */

public class 컨베이어벨트위의로봇_20055 {
    static int N, K, result;
    static LinkedList<Belt> belt;
    static boolean[] robot;

    public static class Belt {
        int durability;
        boolean robot;

        public Belt(int durability) {
            this.durability = durability;
            robot = false;
        }

        public void putRobot() {
            robot = true;
            durability--;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt = new LinkedList<>();
        robot = new boolean[N * 2];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 2 * N; i++) {
            belt.add(i, new Belt(Integer.parseInt(st.nextToken())));
        }

        while (K > 0) {
            MoveBelt();
            MoveRobot();
        }
        System.out.println(result);
    }

    public static void MoveBelt() {
        result++;
        belt.add(0, belt.removeLast());
        if (belt.get(N - 1).robot) {
            belt.get(N - 1).robot = false;
        }
    }

    public static void MoveRobot() {
        for (int i = N - 1; i > 0; i--) {
            if (!belt.get(i).robot) continue;
            if (belt.get(i + 1).robot || belt.get(i + 1).durability <= 0) continue;

            belt.get(i).robot = false;
            belt.get(i + 1).putRobot();
            if (belt.get(i + 1).durability <= 0) {
                K--;
            }

            if (i + 1 == N - 1) {
                belt.get(i + 1).robot = false;
            }
        }

        if (belt.get(0).durability > 0) {
            belt.get(0).putRobot();
            if (belt.get(0).durability <= 0) {
                K--;
            }
        }
    }
}
