import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 1. 문자열 2개 입력 받고 dp 테이블 초기화
        String str1 = " " + br.readLine();
        String str2 = " " + br.readLine();
        int N = str1.length() - 1;
        int M = str2.length() - 1;

        int[][] dp = new int[N+1][M+1];


        // 2. 점화식을 바탕으로 dp 테이블 구성
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (str1.charAt(i) == str2.charAt(j)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        traceLCS(N, M, dp, str1);
    }


    // 3. DP 테이블의 마지막 위치에서부터 역추적
    static void traceLCS(int y, int x, int[][] dp, String str) {
        StringBuilder sb = new StringBuilder();
        
        // 역추적
        while (y > 0 && x > 0) {
            // 현재 좌표로부터 왼쪽 또는 위쪽에 자신과 같은 값이 있을 때
            // 해당 좌표로 이동 (왼쪽 우선)
            if (dp[y][x] == dp[y][x-1]) {
                x--;
            }
            else if (dp[y][x] == dp[y-1][x]) {
                y--;
            }
            
            // 현재 좌표에서 왼쪽과 위에 자신과 같은 값이 없는 경우
            // 현재 문자를 StringBuilder에 저장하고 좌대각으로 이동
            else {
                sb.append(str.charAt(y));
                x--;
                y--;
            }
        }
        System.out.println(sb.length());
        System.out.println(sb.reverse());
    }
}
