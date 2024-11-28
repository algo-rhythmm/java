package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1283_단축키지정 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		boolean[] isUsed = new boolean[26];
		for(int i=0; i<N; i++) {
			String whole_str = br.readLine();
			String[] splited_strs = whole_str.split(" ");
//			System.out.print(splited_strs.toString());
			
			boolean flag = false;
			// key at word's first character
			for(int j=0; j<splited_strs.length; j++) {
				String temp = splited_strs[j].toLowerCase();
				
				char key = temp.charAt(0);
				if(!isUsed[key-97]) {
					String[] arr_str = splited_strs[j].split("");
					ArrayList<String> list_str = new ArrayList<>(Arrays.asList(arr_str));
					list_str.add(0, "[");
					if(list_str.size()>2) list_str.add(2, "]");
					else list_str.add("]");
					splited_strs[j] = String.join("", list_str);
					isUsed[key-97] = true;
					flag = true;
					break;
				}
			}
			if(flag) {
				StringBuilder sb = new StringBuilder();
				for(int j=0; j<splited_strs.length; j++) {
					sb.append(splited_strs[j]).append(" ");
				}
				System.out.println(sb);
				continue;
			}
			
			// key at inside word
			String temp = whole_str.toLowerCase();
			for(int j=1; j<whole_str.length(); j++) {
				
				char key = temp.charAt(j);
				if(key == ' ') continue;
				if(!isUsed[key-97]) {
					String[] whole_str_arr = whole_str.split("");
					ArrayList<String> list_str = new ArrayList<String>(Arrays.asList(whole_str_arr));
					list_str.add(j, "[");
					if(list_str.size()>j+1) list_str.add(j+2, "]");
					else list_str.add("]");
					whole_str = String.join("", list_str);
					isUsed[key-97] = true;
					flag = true;
					break;
				}
			}
			
			System.out.println(whole_str);
			
		}
	}

}
