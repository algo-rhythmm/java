import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이분탐색으로 풀 수 있지만 각 방에서 필요한 체력을 계산하는 방식이 더 빠르다
public class BOJ16434_드래곤앤던전 {

    static int N;
    static long atk, minHp = 0, currentHp = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        atk = Integer.parseInt(st.nextToken());

        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            long a = Integer.parseInt(st.nextToken());
            long h = Integer.parseInt(st.nextToken());
            
            if(type == 1) {
                long time = h / atk;
                if(h % atk == 0) --time;
                long dmg = a * time;
                
                if(currentHp - dmg < 0) {
                    minHp = Math.max(minHp, currentHp);
                    minHp -= currentHp - dmg;
                    currentHp = 0;
                } else {
                    if(currentHp > minHp && dmg > minHp) {
                        minHp += dmg - minHp;
                    }
                    currentHp -= dmg;
                }
            } else {
                atk += a;
                currentHp += h;
            }
        }

        System.out.println(minHp + 1);
    }
}
