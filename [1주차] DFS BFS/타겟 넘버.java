class Solution {
    int[] nums;
    int N;
    int T;


    public int dfs(int idx, int sum) {
        if (idx == N) {
            // 현재 값이 타겟 넘버라면 1 반환 아니면 0 반환
            if (sum == T && idx == N) return 1;
            return 0;
        }

        // 반환 값에 모든 경우의 수가 더해져서 반환
        return dfs(idx+1, sum) + dfs(idx+1, sum-(nums[idx]*2));
    }
    
    public int solution(int[] numbers, int target) {
        // 변수 초기화
        int total = 0;
        nums = numbers.clone();
        T = target;
        N = numbers.length;


        // 누적합(현재 존재하는 수들을 전부 더한다)
        for (int i = 0; i < N; i++) {
            total += numbers[i];
        }

        return dfs(0, total);
    }
}
