import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        int l = 0;
        int r = people.length-1;
        

        while(l<=r) {
            while( l<r && people[l] + people[r--]>limit ) answer++;
            
            l++;
            answer++;
        }
        
        return answer;
    }
}
