import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1062_가르침 {
    static int N, K;
    static int[] words;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new int[N];
        for(int n = 0; n < N; ++n) {
            words[n] = string2Bit(br.readLine());
        }

        int bit = string2Bit("antic");  //anta tica -> antic

        System.out.println(combination(1, 5, bit));
    }

    static int combination(int idx, int count, int bit) {
        if(count >= K) return countWord(bit);
        if(idx == 26) return 0;
        

        int max = 0;
        if((bit & (1 << idx)) == 0) {
            max = Math.max(combination(idx + 1, count + 1, bit | (1 << idx)), max);
        }

        return Math.max(combination(idx + 1, count, bit), max);
    }

    static int countWord(int bit) {
        if(K < 5) return 0;

        int count = 0;
        for(int word : words) {
            if((bit & word) == word) ++count;   
        }
        return count;
    }

    static int string2Bit(String a) {
        int bit = 0;

        for(int i = 0; i < a.length(); ++i) {
            bit |= (1 << a.charAt(i) - 'a');
        }
        
        return bit;
    }
}
