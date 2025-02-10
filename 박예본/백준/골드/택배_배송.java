import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 택배_배송 {
    static ArrayList<ArrayList<Node>> list;

    public static class Node implements Comparable<Node>{
        int to;
        int dist;

        Node(int to, int dist){
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o){
            return this.dist - o.dist;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        int[] dis = new int[N+1];
        Arrays.fill(dis,Integer.MAX_VALUE);

        for(int i =0; i<=N; i++){
            list.add(new ArrayList<Node>());
        }

        for(int i =0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            list.get(from).add(new Node(to,dist));
            list.get(to).add(new Node(from,dist));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dis[1] = 0;
        pq.add(new Node(1,0));

        while(!pq.isEmpty()){ //
            Node now = pq.poll();
            if(now.dist>dis[now.to])continue;
            for(int k = 0; k<list.get(now.to).size(); k++){ //앞으로 갈 곳 고르기?
                Node next = list.get(now.to).get(k);

                if(dis[next.to] > now.dist + next.dist){
                    dis[next.to] = now.dist + next.dist;
                    pq.add(new Node(next.to ,dis[list.get(now.to).get(k).to]));
                    // pq.add(new 택배_배송.Node(next.to , dis[next.to]));
                }
            }

        }
        System.out.println(dis[N]);


    }
}
