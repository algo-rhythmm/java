package algo0204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    11908KB | 88ms
 */

public class 상자넣기 {

    static int N;
    static int[] boxes;
    static int[] count;
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        boxes = new int[N];
        count = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++){
            boxes[i] = Integer.parseInt(st.nextToken());
            count[i] = 1;
        }

        for(int i=1; i<N; i++){
            for(int j=0; j<i; j++){
                if(boxes[i] > boxes[j]){
                    if(count[j] + 1 > count[i]){
                        count[i] = count[j] + 1;
                    }
                }
                res = Math.max(res, count[i]);
            }
        }

        System.out.println(res);

    }

}
