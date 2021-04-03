package normal.part7_array_matrix;

/**
 * 给定一个double类型的数组arr, 其中的元素可正、可负、可0，返回子数组累乘积的最大乘积。例如，arr=[-2.5, 4, 0, 3, 0.5, 8, -1] 子数组[3, 0.5, 8]累乘积
 * 可以获得最大的乘积12，所以返回12.
 */
public class Code08_MaxMulti {
     public static double maxMulti(double[] arr) {
         if (arr == null || arr.length == 0) {
             return 0;
         }
         double max = arr[0];
         double min = arr[0];
         double resMax = arr[0];
         // 累乘
         for (int i = 1; i < arr.length; i++) {
             // 累乘
             double maxMulti = max * arr[i];
             // 为哈要保存最小值呢?  就是为了防止这种情况: [-2,3,-4] 就是说乘到i这个位置了，i前的乘积有可能是负数，但是它这个负数有可能在
             // 某次相乘的时候派上用场 突然转正变成最大乘积
             double minMulti = min * arr[i];
             // 为啥要和arr[i] 比较呢  因为有可能之前的累乘积是个小数，有可能还不如当前的值大。如[0.1, 0.1, 100] 这时候就果断抛弃以前的累乘积 从当下重新开始
             // 这就是为啥要对比3个值    min用来防止负数逆袭，就算逆袭不了，它也不会比max还大。所以一直计算着它。以防万一   而当前的arr[i]就是为了随时抛弃以前的累乘积准备的
             max = Math.max(Math.max(maxMulti, minMulti), arr[i]);
             min = Math.min(Math.min(maxMulti, minMulti), arr[i]);
             resMax = Math.max(resMax, max);
         }
         return resMax;
     }

    public static void main(String[] args) {
        System.out.println(maxMulti(new double[]{-2.5, 4, 0, 3, 0.5, 8, -1}));
    }
}
