package algo0106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    시작 시간 순으로 체크 (정렬)
    if 현재 시작 시간이 가장 빨리 끝내는 강의 시간보다 느리다면?
        : 강의장 추가

    76852KB | 688ms
*/

public class 강의실배정 {

    static int N;
    static Pair[] time;
    static PriorityQueue<Integer> endTime;

    static class Pair {
        int s, e;

        public Pair(int s, int e) {
            this.s = s;
            this.e = e;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        time = new Pair[N];
        endTime = new PriorityQueue<>(Comparator.naturalOrder());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            time[i] = new Pair(s, e);
        }

        Arrays.sort(time, Comparator.comparingInt(o -> o.s));

        endTime.add(time[0].e);

        for(int i=1; i<N; i++) {
            if(!endTime.isEmpty() && endTime.peek() <= time[i].s){
                endTime.poll();
            }
            endTime.add(time[i].e);
        }


        System.out.println(endTime.size());
    }
}
