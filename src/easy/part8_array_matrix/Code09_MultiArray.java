package easy.part8_array_matrix;

/**
 * 给定一个整形数组arr, 返回不包含本位置的累乘数组
 * 例如 arr={2,3,1,4},返回{12,8,24,6} 即除自己以外，其他位置上的累乘
 * 时间复杂度O(N)  除需要返回的结果数组外，额外空间复杂度为O（1）
 * 【进阶】
 * 时空复杂度不变的情况下，不可以使用除法
 *
 */
public class Code09_MultiArray {
    // 方法一： 总乘积除以当前值。 0值三种情况：没有0， 一个0， 多个0
    public static int[] multi1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        int[] res = new int[arr.length];
        int allMulti = 1;
        int zeroCount = 0;
        for (int e : arr) {
            if (e != 0) {
                allMulti *= e;
            } else {
                zeroCount ++;
            }
        }
        // 1. 数组中没有0 用除法
        if (zeroCount == 0) {
            for (int i = 0; i < res.length; i++) {
                res[i] = allMulti / arr[i];
            }
        }
        // 2. 仅有一个0， 则此位置上是其他元素的乘机，剩余其他位置都是0
        if (zeroCount == 1) {
            for (int i = 0; i < res.length; i++) {
                if (arr[i] == 0) {
                    res[i] = allMulti;
                    break;
                }
            }
        }
        // 3. 多个0 则数组所有元素都是0
        return res;
    }

    // 方法二：进阶。 不使用除法
    // res[i] = lr[i-1] * rl[i+1]
    public static int[] multi2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        int[] res = new int[arr.length];
        res[0] = arr[0];
        // 并不是为了获取当前i位置的乘积 而是为的是 获取每个元素左边所有的乘积, 为下一步获得右边乘积后直接左右相乘做准备
        for (int i = 1; i < res.length; i++) {
            res[i] = res[i - 1] * arr[i];
        }
        // 由于左边乘积有数组记录，右边乘积就只好用一个变量记录了
        int rightPartMulti = 1;
        for (int i = res.length - 1; i > 0; i--) {
            res[i] = res[i - 1] * rightPartMulti;
            rightPartMulti *= arr[i];
        }
        res[0] = rightPartMulti;
        return res;

    }

    public static void main(String[] args) {
        int[] res = multi2(new int[]{2, 3, 1, 4});
        for (int e : res) {
            System.out.println(e);
        }
    }
}
