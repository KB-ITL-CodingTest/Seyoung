import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[][] grid;
    static StringTokenizer st;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int N;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        // 변수 초기화
        N = Integer.parseInt(br.readLine());
        grid = new char[N][N];


        // 적록색약이 아닌 경우에 대한 그리드
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < N; j++) {
                grid[i][j] = tmp.charAt(j);
            }
        }

        // 영역 개수 파악
        int count1 = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    dfs(i, j, grid[i][j]);
                    count1++;
                }
            }
        }
        System.out.print(count1 + " ");


        // 적록색약인 경우에 대한 그리드
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 'R') grid[i][j] = 'G';
            }
        }

        // 영역 개수 파악 
        int count2 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    dfs(i, j, grid[i][j]);
                    count2++;
                }
            }
        }
        System.out.println(count2);
    }
    
    
    static void dfs(int y, int x, char t) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[ny][nx] && grid[ny][nx] == t) {
                visited[ny][nx] = true;
                dfs(ny, nx, t);
            }
        }
    }
}
