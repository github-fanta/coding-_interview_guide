package zz_leetcodeguide.gready;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 有一群孩子和一堆饼干，每个孩子有个饥饿度，每个饼干都有一个大小。每个孩子只能吃最多一个饼干，
 * 且只有饼干的大小大于孩子的饥饿度时，这个孩子才能吃饱，求解最多有多少孩子可以吃饱
 * Input: [1,2], [1,2,3]  两个数组分别代表 孩子的饥饿度  & 饼干的大小
 * Output: 2   最多有多少个孩子可以吃饱的数量
 *
 * 策略：给剩余孩子里最小饥饿度的孩子分配最小的能饱腹的饼干
 */
public class Code01_AssignCooies{

    public static int findContentChildren(List<Integer> children, List<Integer> cookies) {
        Collections.sort(children);
        Collections.sort(cookies);
        int childIdx = 0;
        int cookieIdx = 0;
        while (childIdx < children.size() && cookieIdx < cookies.size()) {
            if (cookies.get(cookieIdx) >= children.get(childIdx) ) {
                childIdx ++;
            }
            cookieIdx ++;
        }
        return childIdx;
    }

    public static void main(String[] args) {
        List<Integer> children = new ArrayList<>();
        children.add(1);
        children.add(2);
        List<Integer> cookies = new ArrayList<>();
        cookies.add(1);
        cookies.add(2);
        cookies.add(3);
        int count = findContentChildren(children, cookies);
        System.out.println("count: " + count);
    }
}
