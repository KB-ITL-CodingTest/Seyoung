import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];

        Arrays.fill(dp, N);
        dp[N] = 0;

        for (int i = N; i >= 2; i--) {
            if (dp[i] != N) {
                if (i % 3 == 0) dp[i/3] = Math.min(dp[i/3], dp[i] + 1);
                if (i % 2 == 0) dp[i/2] = Math.min(dp[i/2], dp[i] + 1);
                dp[i-1] = Math.min(dp[i-1], dp[i] + 1);
            }
        }
        System.out.println(dp[1]);
    }
}
