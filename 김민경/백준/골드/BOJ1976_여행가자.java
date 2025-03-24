import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] parent;
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N+1];
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				String isConnect = st.nextToken();
				if(j<=i) continue;
				
				if(isConnect.equals("1")) {
					union(i, j);
				}
				
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		String answer = "YES";
		int a = Integer.parseInt(st.nextToken());
		for(int i=1; i<M; i++) {
			int b = Integer.parseInt(st.nextToken());
			
			if(find(a) != find(b)) answer = "NO";
			a = b;
		
		}
		
		System.out.println(answer);
		
	}
	
	static int find(int a) {
		if(parent[a]==a) return a;
		
		return parent[a] = find(parent[a]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a==b) return;
		
		if(a<b) parent[b] = a;
		else parent[a] = b;
	}
}
