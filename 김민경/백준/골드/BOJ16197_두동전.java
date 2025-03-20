package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ16197_두동전 {
	
	static int N, M;
	static int[][] dir = {
			{-1, 0, 1, 0},
			{0, -1, 0, 1}
	};
	
	static char[][] board;
	static int[] coin_pos;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		coin_pos = new int[4];
		
		boolean ord = false;
		for(int i=0; i<N; i++) {
			board[i] = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				if(board[i][j]=='o') {
					if(!ord) {
						coin_pos[0] = i;
						coin_pos[1] = j;
						ord = true;
					}
					else {
						coin_pos[2] = i;
						coin_pos[3] = j;
					}
				}
			}
		}
		
		Deque<Integer[]> q = new ArrayDeque<>();
		q.offer(new Integer[] {coin_pos[0], coin_pos[1], coin_pos[2], coin_pos[3]} );
		int trial =1;
		boolean[][][][] visit = new boolean[N][M][N][M];
		boolean flag = false;
		A: while(!q.isEmpty()) {
			int size = q.size();
			if(trial>10) {
				trial = -1;
				break;
			}
			for(int i=0; i<size; i++) {
				Integer[] temp = q.poll();
				
				int r1 = temp[0];
				int c1 = temp[1];
				
				int r2 = temp[2];
				int c2 = temp[3];
				
				for(int d=0; d<4; d++) {
					int nr1 = r1+dir[0][d];
					int nc1 = c1+dir[1][d];
					
					int nr2 = r2+dir[0][d];
					int nc2 = c2+dir[1][d];
					
					if( (nr1<0 || nr1>=N || nc1<0 || nc1>=M) && (nr2<0 || nr2>=N || nc2<0 || nc2>=M) ) continue;
					if( (nr1<0 || nr1>=N || nc1<0 || nc1>=M) || (nr2<0 || nr2>=N || nc2<0 || nc2>=M) ) {
						flag = true;
						break A;
					}
					
					if(visit[nr1][nc1][nr2][nc2]) continue;
					if(board[nr1][nc1]=='#' && board[nr2][nc2]=='#') continue;
					
					if(board[nr1][nc1]=='#') {
						nr1 = r1;
						nc1 = c1;
					}
					
					if(board[nr2][nc2]=='#') {
						nr2 = r2;
						nc2 = c2;
					}
					
					visit[nr1][nc1][nr2][nc2] = true;
					
					q.offer(new Integer[] {nr1, nc1, nr2, nc2} );
				}
			}
			
			trial++;
		}
		if(!flag) trial =-1;
		System.out.println(trial);
		
	}
}
