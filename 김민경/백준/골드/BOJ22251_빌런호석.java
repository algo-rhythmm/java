import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] diff = {
			{0, 4, 3, 3, 4, 3, 2, 3, 1, 2},
			{4, 0, 5, 3, 2, 5, 6, 1, 5, 4},
			{3, 5, 0, 2, 5, 4, 3, 4, 2, 3},
			{3, 3, 2, 0, 3, 2, 3, 2, 2, 1},
			{4, 2, 5, 3, 0, 3, 4, 3, 3, 2},
			{3, 5, 4, 2, 3, 0, 1, 4, 2, 1},
			{2, 6, 3, 3, 4, 1, 0, 5, 1, 2},
			{3, 1, 4, 2, 3, 4, 5, 0, 4, 3},
			{1, 5, 2, 2, 3, 2, 1, 4, 0, 1},
			{2, 4, 3, 1, 2, 1, 2, 3, 1, 0}
	};
	static int N, K, P, X;
	static int[] floor, n_floor, x_floor;
	static int answer=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		floor = new int[K];
		n_floor = new int[K];
		x_floor = new int[K];
		
		int temp_x = X;
		int temp_n = N;
		
		for(int i=K-1; i>=0; i--) {
			n_floor[i] = temp_n%10;
			x_floor[i] = temp_x%10;
			
			temp_n /=10;
			temp_x /=10;
		}
		
		DFS(0, false, 0);
		System.out.println(answer);
	}
	
	static void DFS(int n, boolean flag, int p) {
		if(n==K) {
			String number_str="";
			for(int i=0; i<K; i++) {
				number_str+=floor[i]+"";
			}

			int number =Integer.parseInt(number_str);
			
			if(number<=N && number !=X && number !=0) answer++;
			
			return;
		}
		
		for(int i=0; i<10; i++) {
			if(p+diff[x_floor[n]][i]>P) continue;
			
			floor[n]=i;
			DFS(n+1, true, p+diff[x_floor[n]][i]);
		}
	}
}
