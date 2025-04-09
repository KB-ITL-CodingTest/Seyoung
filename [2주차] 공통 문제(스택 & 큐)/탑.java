import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int N = Integer.parseInt(br.readLine());
        int[] top = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 각 탑들을 순회 -> 현재 탑 지정
        for (int i = 1; i <= N; i++) {
            int currentTop = Integer.parseInt(st.nextToken());
            top[i] = currentTop;

            // 스택에 있는 후보 탑들을 탐색 (가까운 순서부터)
            while (!stack.isEmpty()) {
                int candidateTop = stack.peek();
                // 후보 탑이 현재 탑보다 높은 경우
                if (top[candidateTop] > currentTop) {
                    sb.append(candidateTop).append(' ');
                    break;
                }
                // 후보 탑이 현재 탑보다 낮은 경우 -> 후보에서 제거
                stack.pop();
            }

            // 수신할 탑이 없는 경우
            if (stack.isEmpty()) {
                sb.append('0').append(' ');
            }
            // 현재 탑은 이후 탑들의 수신 후보가 될 수 있으므로 스택에 추가
            stack.push(i);
        }
        System.out.println(sb);
    }
}
