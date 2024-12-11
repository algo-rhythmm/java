import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 컨베이어벨트위의로봇 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] durability = new int[2*N];
        boolean[] robot = new boolean[2*N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<2*N; i++) {
            durability[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int step = 0;
        int count = 0;

        while(true){
            step++;

            // 벨트 회전
            start = (start + 2*N - 1) % (2*N);

            // 로봇도 벨트와 함께 회전
            for(int i=N-1; i>=0; i--){
                robot[i] = (i==0)? false : robot[i-1];
            }
            robot[N-1] = false; // 내리는 위치에는 로봇이 있을 수 없음

            // 로봇 이동
            for(int i=N-2; i>=0; i--){ // 내리는 위치 바로 앞 칸부터
                if(robot[i] && !robot[i+1] && durability[(start+i+1)% (2*N)]>0){
                    // 로봇 이동
                    robot[i] = false;
                    robot[i+1] = true;
                    durability[(start+i+1)%(2*N)]--;
                    if(durability[(start+i+1)%(2*N)]==0) count++;
                }
            }
            // 내리는 위치 도착 로봇 내리기
            robot[N-1] = false;

            // 로봇 올리기
            if(durability[start]>0){
                robot[0] = true;
                durability[start]--;
                if(durability[start] == 0) count++;
            }

            // 내구도 0 개수 확인
            if(count>=K) break;
        }
        System.out.println(step);
    }
}
