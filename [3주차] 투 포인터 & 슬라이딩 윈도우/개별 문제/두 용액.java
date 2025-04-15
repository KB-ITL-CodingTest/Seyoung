import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 변수 선언
        int N = Integer.parseInt(br.readLine());
        long[] solutions = new long[N];
        long[] answer = new long[2];
        long min = Long.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }

        // 용액을 정렬
        Arrays.sort(solutions);

        // 투 포인터 시작, 종료 인덱스 선언
        int start = 0;
        int end = N-1;

        // 투 포인터 로직
        // start 가 end 보다 크거나 같을 때 종료
        while (start < end) {
            // 두 용액이 혼합됐을 때의 특성값, 그리고 특성값에 대한 절댓값
            long two_solutions = solutions[start] + solutions[end];
            long solutions_abs = Math.abs(two_solutions);

            // 0인 경우 정답을 변경하고 제거
            if (two_solutions == 0) {
                answer[0] = solutions[start];
                answer[1] = solutions[end];
                break;
            }

            else {
                // 최솟값 갱신
                if(solutions_abs < min) {
                    min = solutions_abs;
                    answer[0] = solutions[start];
                    answer[1] = solutions[end];
                }

                // two_solution 이 양수인지 음수인지 여부에 따라 시작, 종료 인덱스 증감
                // 음수일 경우 => 값이 더 커져야 하므로 start 증가
                // 양수일 경우 => 값이 작아져야 하므로 end 감소
                if (two_solutions < 0) start++;
                else end--;
            }
        }
        System.out.println(answer[0] + " " + answer[1]);
    }
}
