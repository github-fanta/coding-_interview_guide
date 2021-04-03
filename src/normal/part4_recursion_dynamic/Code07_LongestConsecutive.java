package normal.part4_recursion_dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定无序数组arr,返回其中最长的连续序列的长度
 * 举例： arr[100, 4, 200, 1, 3, 2], 最长的连续序列为
 */
public class Code07_LongestConsecutive {

    public static int longestConsecutive(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i ++) {
            // 只处理之前没出现的数  处理过的就再也不会再计算了。
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
                if (map.containsKey(arr[i] - 1)) {
                    max = Math.max(max, merge(map, arr[i] - 1, arr[i]));
                }
                if (map.containsKey(arr[i] + 1)) {
                    max = Math.max(max, merge(map, arr[i], arr[i] + 1));
                }
            }
        }
        return max;
    }

    /**
     * 融合两段。
     * @param map
     * @param less
     * @param more
     * @return
     */
    private static int merge(Map<Integer, Integer> map, int less, int more) {
        // 新的左边界
        int left = less - map.get(less) + 1;
        // 新的右边界
        int right = more + map.get(more) - 1;
        int len = right - left + 1;
        // 这两个边界就在len的最极端，等到下次merge时候 都能和新的毗邻上
        map.put(left, len);
        map.put(right, len);
        return len;
    }

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }
}
