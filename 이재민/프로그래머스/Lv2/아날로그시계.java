/*
    분침이 한바퀴 돌면서 초침과 겹치는 횟수 = 59회
    시침이 한바퀴 돌면서 초침과 겹치는 획수 = 719회

    12시 00분 00초에는 초침이 시침과 분침 동시에 겹침 -> 1회 겹침으로 카운트

    주어진 시간을 시작시간, 종료시간이라고 했을 때
    종료시간까지 겹치는 횟수 - 시작시간까지 겹치는 횟수
*/

class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = -1;
        
        int endTime = h2 * 3600 + m2 * 60 + s2;
        int startTime = h1 * 3600 + m1 * 60 + s1;
        
        int et = endTime * 59 / 3600
            + endTime * 719/43200;
        int st = startTime * 59 / 3600
            + startTime * 719/43200;
        
        if(endTime >= 43200) et -= 2;
        else et -= 1;
        if(startTime >= 43200) st -= 2;
        else st -= 1;
        
        answer = et - st;
        
        if(startTime * 59 % 3600 == 0
          || startTime * 719 % 43200 == 0) answer++;
        
        return answer;
    }
}