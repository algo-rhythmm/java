import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 우선순위 큐를 사용해서 해결하고자 하였는데 왜 메모리 초과가 나오는지 모르겠다...
public class BOJ1092_배 {

    static int N, M;
    static int[] crane;
    static ArrayList<Integer> box = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        crane = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++) {
            crane[n] = Integer.parseInt(st.nextToken());
        }
        // 오름차순 정렬
        Arrays.sort(crane);

        M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int m = 0; m < M; m++) {
            box.add(Integer.parseInt(st.nextToken()));
        }

        // 내림차순 정렬
        box.sort((a, b) -> b - a);

        // 만약 크래인의 최대 운송 무게를 넘어가는 상자가 있다면 -1 출력
        if(box.get(0) > crane[N - 1]) {
            System.out.println(-1);
            return;
        }

        // 각 크레인이 옮길 수 있는 가장 무거운 상자를 옮긴다.
        int time = 0;
        while(!box.isEmpty()) {
            // 다른 크레인들이 이전 크레인의 j 값을 사용할 수 있도록 하면 시간절약이 가능하다 (2000ms -> 300ms)
            // 정렬되어 있기 때문에 이전 크레인의  j 값 이후만 보면 된다. (j 값 이전은 불가능)
            int j = 0;
            for(int i = N - 1; i >= 0; i--) {
                while(j < box.size() && box.get(j) > crane[i]) {
                    j++;
                }
                if(j == box.size()) continue;
                box.remove(j);
            }
            time++;
        }

        System.out.println(time);
    }  


}