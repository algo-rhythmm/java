import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 기울기를 사용하여 풀었지만 CCW 알고리즘으로 풀 수 있다고 한다.
public class BOJ1027_고층건물 {

    static int N, MAX = 0;
    static int[] arr, result;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        result = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                if(isVisible(i, j)) {
                    result[i] += 1;
                    result[j] += 1;

                    if(result[i] > result[MAX]) MAX = i;
                    if(result[j] > result[MAX]) MAX = j; 
                }
            }
        }
        System.out.println(result[MAX]);
    }

    // 기울기를 구하여 빌딩이 가려져 있는지 구별
    static boolean isVisible(int from, int to) {
        int x1 = from, y1 = arr[from], x2 = to, y2 = arr[to];

        double a = ((double)(y2 - y1)) / ((double)(x2 - x1));

        for(int i = from + 1; i < to; i++) {
            double k = ((double)(arr[i] - y1)) / ((double)(i - x1));
            if(a <= k) return false;
        }

        return true;
    }
}