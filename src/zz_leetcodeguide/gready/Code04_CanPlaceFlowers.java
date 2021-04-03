package zz_leetcodeguide.gready;

import java.util.Arrays;

/**
 *假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 *
 * 给你一个整数数组  flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。另有一个数 n
 * 能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false。
 *
 * 给定的已经符合规则了
 * 链接：https://leetcode-cn.com/problems/can-place-flowers
 */
public class Code04_CanPlaceFlowers {
    public static void main(String[] args) {
        System.out.println(canPlace2(new int[]{0,1,0,1,0,0}, 1));
        System.out.println(canPlaceAppend(new int[]{0,1,0,1,0,0}, 1));
//        System.out.println(canPlaceStep2(new int[]{1,0,0,0,0,1}, 2));
//        System.out.println(canPlaceFindPattern(new int[]{1,0,0,0,0,1}, 2));
    }

    /**
     * 官方的解法：从贪心的角度考虑，应该在不打破种植规则的情况下种入尽可能多的花，然后判断可以种入的花的最多数量是否大于或等于 n
     * 从第一个1到下一个1之间 的所有坑 最多能放几个。  可以通过找规律 找到   (j - i - 2)/2
     * 特殊情况：第一个就是0  那么可以假设起始的0在-1处  找到的规律是 j/2就好
     */
    public static boolean canPlaceFindPattern(int[] flowerbed, int n) {
        int pre = -1;
        for (int i = 0; i < flowerbed.length; i++) {
            if (n <= 0) return true;
            if (flowerbed[i] == 1) {
                if (pre < 0) {
                    n -= (i >> 1);
                } else {
                    n -= ((i - pre - 2) >> 1);
                }
                pre = i;
            }
        }
        if (pre < 0) {
            n -= ((flowerbed.length + 1) >> 1);
        } else {
            n -= ((flowerbed.length - pre - 1) >> 1);
        }
        return n <= 0;


    }

    /**
     * 大神的解法1：在flowerbed首尾都加上0  就不用考虑边界问题了
     * 原因：我们判断种花的条件是连续奇数个0，即3，5，7，但是在花坛边，连续两个0就可以在flowerbed[0]处种花，右边同理
     */
    public static boolean canPlaceAppend(int[] originFlowerbed, int n) {
        int[] flowerbed = new int[originFlowerbed.length + 2];
//        flowerbed[0] = 0;
//        flowerbed[flowerbed.length - 1] = 0;
        System.arraycopy(originFlowerbed, 0, flowerbed, 1, originFlowerbed.length);
//        for (int i = 1; i < flowerbed.length - 1; i++) {
//            flowerbed[i] = originFlowerbed[i - 1];
//        }
        for (int i = 1; i < flowerbed.length - 1; i ++) {
            if (flowerbed[i] == 0 && flowerbed[left(i)] == 0 && flowerbed[right(i)] == 0) {
                n --;
            }
        }
        return n <= 0;
    }
    private static int right(int i) {
        return i + 1;
    }
    private static int left(int i) {
        return i - 1;
    }

    /**
     * 大神的解法2：一次走两步，
     * 每次种花时，判断下一个坑是0，才能种。或者当前是最后一个坑位。也就不用判断下一个了
     * 如果下一个坑是1,那么i换步子，因为这下，i位置的下一个肯定种不了花了，要关注的是与当前位置差两步的位置能否种花。换个步子刚好。
     */
    public static boolean canPlaceStep2(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i += 2) {
            if (flowerbed[i] == 0) {
                if (i == flowerbed.length - 1 || flowerbed[i + 1] == 0) {
                    n --;
                } else {
                    i ++;
                }
            }
        }
        return n <= 0;
    }


    /**
     * 我的解法
     * @param arr
     * @param n
     * @return
     */
    public static boolean canPlace2(int[] arr, int n) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        if (arr.length == 1) {
            return (arr[0] == 0) || (arr[0] == 1 && n == 0);
        }
        for (int i = 0; i < arr.length; i++) {
            if (n <= 0) {
                return true;
            }
            if (arr[i] == 0) {
                // 如果是第一个
                if (i == 0) {
                    if (i + 1 < arr.length && arr[i + 1]  == 0) {
                        arr[i] = 1;
                        n--;
                    }
                    continue;
                }
                // 前一个为0
                if (arr[i - 1] == 0) {
                    // 后一个为0  或当前就是最后一个
                    if ((i + 1 < arr.length && arr[i + 1] == 0) || (i == arr.length - 1)) {
                        arr[i] = 1;
                        n --;
                    }

                }
            }
        }
        return n <= 0;
    }
}
