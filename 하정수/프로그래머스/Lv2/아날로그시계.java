package 프로그래머스;

import java.io.IOException;

/*
 *   아날로그시계
 *
 * */

public class 아날로그시계 {
    class Solution {

        public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
            int answer = -1;

            int start = h1 * 3600 + m1 * 60 + s1;
            int end = h2 * 3600 + m2 * 60 + s2;

            int et = end * 59 / 3600 + end * 719 / 43200;
            int st = start * 59 / 3600 + start * 719 / 43200;

            if (end >= 43200) et -= 2;
            else et -= 1;
            if (start >= 43200) st -= 2;
            else st -= 1;

            answer = et - st;

            if (start * 59 % 3600 == 0 || start * 719 % 43200 == 0) answer++;


            return answer;
        }

    }
}
