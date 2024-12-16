import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 경쟁적전염 {

    static int N, K;

    static class Virus implements Comparable<Virus>{

        int num;
        int len;

        public Virus(int num, int len) {
            this.num = num;
            this.len = len;
        }

        @Override
        public int compareTo(Virus o) {
            if(this.len==o.len){
                return this.num-o.num;
            }else {
                return this.len-o.len;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N+1][N+1];
//        int[] viros = new int[K+1];
        List<int[]> viros = new LinkedList<>();

        for(int i = 1; i < N+1;i++){
            st = new StringTokenizer(br.readLine());
            for(int q = 1; q< N+1; q++){
                int tmp = Integer.parseInt(st.nextToken());
                map[i][q] = tmp;
                if(tmp != 0){
                    viros.add(new int[]{tmp,i,q});
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int time = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        PriorityQueue<Virus> V = new PriorityQueue<>();
        for(int t = 0; t<viros.size(); t++){
            int tmp[] = viros.get(t);
            int len = Math.abs(tmp[1]-r)+ Math.abs(tmp[2]-c);
            V.add(new Virus(tmp[0],len));
        }
        Virus v = V.poll();
        if(v.len > time){
            System.out.println(0);
        }else{
            System.out.println(v.num);
        }

    }

}
