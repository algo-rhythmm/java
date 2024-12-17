import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class 용액 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] w = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int a =0; a<N;a++){
            w[a]= Integer.parseInt(st.nextToken());
        }

        int high = N-1;
        int low = 0;

        int result_high = w[high];
        int result_low = w[low];
        int sum = Math.abs(result_high + result_low);

        while( high > low+1 ){

            if( Math.abs(w[high]) < Math.abs(w[low]) ){
                low++;
            }else{ //-가 더 크다면
                high--;
            }

            if( Math.abs(w[high] + w[low]) < sum){
                result_high = w[high];
                result_low = w[low];
                sum = Math.abs(w[high] + w[low]);
            }

        }

        System.out.println(result_low+ " " +result_high);

    }
}
