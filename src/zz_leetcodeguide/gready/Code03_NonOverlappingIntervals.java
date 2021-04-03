package zz_leetcodeguide.gready;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 区间问题：
 * 给多个区间，算出让这些区间互不重叠所需要移除区间的最小个数。起止相连不算重叠
 * 样例:
 * Input:[[1,2], [2,4], [1,3]]
 * Output: 1
 */
public class Code03_NonOverlappingIntervals {
    public static void main(String[] args) {
        System.out.println(removeRangeCount(new ArrayList<Integer[]>() {{
            add(new Integer[]{1, 2});
            add(new Integer[]{2, 4});
            add(new Integer[]{1, 3});
//            add(new Integer[]{2, 3});
            add(new Integer[]{5, 7});
        }}));
    }

    public static int removeRangeCount(List<Integer[]> ranges) {
        if (ranges == null || ranges.size() == 0) {
            return 0;
        }
        ranges = ranges.stream()
                .sorted((a, b) -> a[1].intValue() - b[1].intValue())
                .collect(Collectors.toList());
        int removes = 0;
        int preEnd = ranges.get(0)[1];
        for (int i = 1; i < ranges.size(); i++) {
            if (ranges.get(i)[0] < preEnd) {
                removes ++;
            } else {
                preEnd = ranges.get(i)[1];
            }
        }
        return removes;
    }
}
