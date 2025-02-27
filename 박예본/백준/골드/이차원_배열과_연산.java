import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 이차원_배열과_연산 {

    public static class Pair implements Comparable<Pair>{
        int num;
        int cnt;

        Pair(int n, int c){
            this.num = n;
            this.cnt = c;
        }

        @Override
        public int compareTo(Pair o){
            if(this.cnt == o.cnt){
                return this.num-o.num;
            }
            return this.cnt-o.cnt;
        }
    }
    static int[][] map;
    static int r,c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int targetR = Integer.parseInt(st.nextToken());
        int targetC = Integer.parseInt(st.nextToken());
        int targetV = Integer.parseInt(st.nextToken());

        map = new int [101][101];
        r = 3;
        c = 3;
        int cnt = 0;

        for(int row = 1; row<=3; row++){
            st = new StringTokenizer(br.readLine());
            for(int col = 1; col<=3; col++){
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        while(map[targetR][targetC] != targetV){

            if(r >= c){
            R_sort();
            }else{
            C_sort();
            }

            cnt++;
            if(cnt > 100) { cnt = -1;  break; }
        }

        System.out.println(cnt);

    }

    static void R_sort(){
        int len = 0;
        for(int R = 1; R <= r; R++){
            Map<Integer,Integer> hm = new HashMap<>();

            for(int C = 1; C<=c; C++){
                int value = map[R][C];
            if(value==0) continue;

            hm.put(value,hm.getOrDefault(value,0)+1);
            map[R][C] = 0;
            }
            PriorityQueue<Pair> pq = new PriorityQueue<>();
            int cnt = 1;
            for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
                pq.add(new Pair(entry.getKey(), entry.getValue()));
                cnt += 2;
            }
            len = Math.max(len,cnt);
            int idx = 1;
            while(!pq.isEmpty()&& idx <= 100){
                Pair tmp = pq.poll();
                map[R][idx++] = tmp.num;
                map[R][idx++] = tmp.cnt;
            }

        }
        c = len;
    }

    static void C_sort(){
        int len = 0;
        for(int C = 1; C<=c; C++){
            Map<Integer, Integer> hm = new HashMap();
            for(int R = 1; R<=r; R++){
                int value = map[R][C];
                if(value == 0) continue;
                hm.put(value,hm.getOrDefault(value,0)+1);
                map[R][C] = 0;
            }
            PriorityQueue<Pair> pq = new PriorityQueue<>();

            int cnt = 1;
            for( Map.Entry<Integer,Integer> entry : hm.entrySet()){
                    pq.add(new Pair(entry.getKey(),entry.getValue()));
                    cnt+=2;
            }
            len = Math.max(cnt,len);
            int idx = 1;
            while(!pq.isEmpty()&&idx<=100){
                Pair tmp = pq.poll();
                map[idx++][C] = tmp.num;
                map[idx++][C] = tmp.cnt;
            }
        }
        r = len;
    }

}
