import java.io.*;
import java.util.*;

public class AC {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        here :
        for(int t = 0; t<T; t++){

            char[] p = br.readLine().toCharArray();
            int n = Integer.parseInt(br.readLine());

            String input = br.readLine();
            input = input.substring(1, input.length() - 1); // 양 끝의 '['와 ']' 제거 -> GPT 도움
            String[] numbers = input.isEmpty() ? new String[0] : input.split(","); // 빈 배열 처리

            int[] card = new int[n];

            for (int i = 0; i < n; i++) {
                card[i] = Integer.parseInt(numbers[i].trim());
            }

            boolean start = true;
            int go = 0;
            int back = card.length-1;

            for(int k = 0; k<p.length; k++){

                if(p[k]=='R'){
                    start = !start;
                }else{
                    if(go > back){
                        sb.append("error\n");
                        continue here;
                    }

                    if(start){ //정방향일때
                      go++;
                    }else{ //역방향일때
                      back--;
                    }
                }
            }

            sb.append("[");

            if (go <= back) {
                if (start) {
                    for (int q = go; q < back; q++) {
                        sb.append(card[q] + ",");
                    }
                    sb.append(card[back]);
                } else {
                    for (int q = back; q > go; q--) {
                        sb.append(card[q] + ",");
                    }
                    sb.append(card[go]);
                }

            }
            sb.append("]"+"\n");

        }
        System.out.println(sb);
    }

}
