import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ16434_드래곤앤던전_이분탐색 {

    static class Room {
        int type;
        long a, h;
        public Room(int type, long a, long h) {
            this.type = type;
            this.a = a;
            this.h = h;
        }
    }

    static int N;
    static long startAtk, answer = -1;
    static ArrayList<Room> rooms = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        startAtk = Integer.parseInt(st.nextToken());

        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            long a = Integer.parseInt(st.nextToken());
            long h = Integer.parseInt(st.nextToken());
            
            rooms.add(new Room(type, a, h));
        }

        long left = 0, right = 123456000000000000L; // MAX_N * MAX_a * MAX_h OR Long.MAX_VALUE;
        
        while(left <= right) {
            long mid = (left + right) / 2;

            if(sim(mid)) {
                right = mid - 1;
                answer = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    static boolean sim(long maxHp) {
        long hp = maxHp;
        long atk = startAtk;

        for(int n = 0; n < N; n++) {
            Room room = rooms.get(n);

            if(room.type == 1) {
                long time = room.h / atk;
                if(room.h % atk == 0) --time;
                long dmg = room.a * time;

                hp -= dmg;
            } else {
                atk += room.a;
                hp = Math.min(maxHp, hp + room.h);
            }

            if(hp <= 0) return false;
        }

        return true;
    }
}
