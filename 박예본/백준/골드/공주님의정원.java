import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 공주님의정원 {
    static int result;

    static class Flower {
        int startM;
        int stratD;
        int endM;
        int endD;

        public Flower() {
        }

        public Flower(int startM, int stratD, int endM, int endD) {
            this.startM = startM;
            this.stratD = stratD;
            this.endM = endM;
            this.endD = endD;
        }

        @Override
        public String toString() {
            return "Flower{" +
                    "startM=" + startM +
                    ", stratD=" + stratD +
                    ", endM=" + endM +
                    ", endD=" + endD +
                    '}';
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        List<Flower> flowers = new ArrayList<>();
        result = 0;

        int N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int startM = Integer.parseInt(st.nextToken());
            int stratD = Integer.parseInt(st.nextToken());
            int endM = Integer.parseInt(st.nextToken());
            int endD = Integer.parseInt(st.nextToken());
            flowers.add(new Flower(startM, stratD, endM, endD));
        }

        int startMonth = 3;
        int startDay = 1;

        int lastMonth = 3;
        int lastDay = 1;

        int time = N;

        while (lastMonth <= 11 && time > 0) { //끝 달이 11달을 넘어가면 나옴

            for(int i = 0; i<flowers.size(); i++) {

                if(flowers.get(i).startM < startMonth){//시작점 달 설정


                    if(flowers.get(i).endM > lastMonth){
                        lastMonth = flowers.get(i).endM;
                        lastDay = flowers.get(i).endD;
                    } else if (flowers.get(i).endM==lastMonth&&flowers.get(i).endD>lastDay) {
                        lastDay = flowers.get(i).endD;
                    }


                }

                if(flowers.get(i).startM == startMonth && flowers.get(i).stratD<=startDay){ //달은 같은데, 날이 더 적다면


                    if(flowers.get(i).endM > lastMonth){
                        lastMonth = flowers.get(i).endM;
                        lastDay = flowers.get(i).endD;
                    } else if (flowers.get(i).endM==lastMonth&&flowers.get(i).endD>lastDay) {
                        lastDay = flowers.get(i).endD;
                    }

                }


            }

            startMonth = lastMonth;
            startDay = lastDay;
            result++;
            time--;
            if(time == 0) {result=0; break;} //너무 많이 돌면 나가게 된다.

        }

        System.out.println(result);


    }
}