package 프로그래머스;

import java.util.Arrays;

public class 구명보트 {
    class Solution {
        public int solution(int[] people, int limit) {
            Arrays.sort(people);
            int answer = 0;
            int left = 0;
            int right = people.length - 1;

            while(left<=right){
                if(limit >= people[left] + people[right]){
                    left++;
                }
                right--;
                answer++;
            }

            return answer;
        }
    }
}
