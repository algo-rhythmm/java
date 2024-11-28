import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * �־��� ��� �����ϴ� ����
 * 11572KB | 60ms
 */

public class ����Ű���� {
	static int N;
	static boolean[] check;
	static String[] str;
	static StringBuffer res = new StringBuffer();
	
	static String makeShortcutKey(String s, int idx) {
		StringBuffer sb = new StringBuffer();
		sb.append(s);
		sb.insert(idx+1, ']');
		sb.insert(idx, '[');
		
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		check = new boolean[26];
		str = new String[N];
		
		A: for(int i=0; i<N; i++) {
			str[i] = br.readLine();
		
			
			String[] split = str[i].split(" ");
			// 1. �� ������ ù ���ڰ� ����Ű�� �� �� �ִ���
			for(int j=0; j<split.length; j++) {
				char ch = split[j].charAt(0);
				if(ch < 97) ch = Character.toLowerCase(ch);
				
				if(!check[ch - 'a']) {
					check[ch - 'a'] = true;
					split[j] = makeShortcutKey(split[j], 0);
					String s = String.join(" ", split);
					res.append(s).append('\n');
					continue A;
				}
			}
			
			// 2. 1���� ������Ű�� ������ ��
			
			for(int j=0; j<str[i].length(); j++) {
				char ch = str[i].charAt(j);
				if(ch == ' ') continue;
				if(ch < 97) ch = Character.toLowerCase(ch);
				
				if(!check[ch - 'a']) {
					check[ch - 'a'] = true;
					String s = makeShortcutKey(str[i], j);
					res.append(s).append('\n');
					continue A;
				}
			}
			
			res.append(str[i]).append('\n');
		}
		
		System.out.println(res);
		
		
		
		
		
	}

}
