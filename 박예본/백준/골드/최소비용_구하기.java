import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최소비용_구하기 {

    static int start, goal, price, village, bus;
    static boolean[] vis;
    static ArrayList<int[]>[] list;
    static int length[];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        village = Integer.parseInt(br.readLine());
        bus = Integer.parseInt(br.readLine());
        list = new ArrayList[village+1]; //마을이 1부터 시작한다 생각

        for(int i = 1; i<=village; i++){ //초기화
           list[i] = new ArrayList();
        }



        for(int i = 1; i<=bus; i++){ //초기화
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            list[from].add(new int[] {to,val});
        }

        st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());

        vis = new boolean[village+1];
        length = new int[village+1];
        Arrays.fill(length,Integer.MAX_VALUE);
        Dijkstra(start);

        System.out.println(length[goal]);
    }

    public static void Dijkstra(int now){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[] {now, 0});
//        vis[now] = true;
        length[now] = 0;
        int lastvalue = 0;

        while(!pq.isEmpty()) {
            //먼저 까
            int[] tmp = pq.poll();

            if (vis[tmp[0]]) continue;
            vis[tmp[0]] = true; //방문처리

            for (int[] i : list[tmp[0]]) {
                if (length[i[0]] > length[tmp[0]] + i[1]) {
                    length[i[0]] = length[tmp[0]] + i[1];
                    pq.add(new int[]{i[0], length[i[0]]});
                }
            }
        }
    }
}
