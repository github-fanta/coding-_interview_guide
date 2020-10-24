package normal.part7_array_matrix;

/**
 * 给定一个无序整形数组arr,  找到数组中未出现的最小正整数。
 * 举例：arr=[-1,2,3,4]。返回1
 * arr=[1,2,3,4] 返回5
 * 不管数组的元素值有多大，最小正整数肯定是在1 ~ arr.length + 1 之间 因为题目要求要最小正整数
 *
 * 方法1：从1开始找，数组中有了，再在数组中找2。。。
 * 方法2：排序。从头找
 *
 * 我们的理想状态：
 * 将每个元素放到指定位置上（从头到尾 逐渐变大）
 * idx: 0 1 2 3 4
 *     [1,2,3,4,5]
 *
 * 我们就看1 ~ arr.length + 1 的坑被现有数组arr元素的哪些填了 没有填得上的
 * left从左到右遍历 left 有两种情况 如果符合标准情况(arr[left] = left+1) left往右判断下一个 另一个情况 不符合 将arr[left]放到它该在的位置 arr[left] - 1;
 * 在将它放到该在的位置之前，先判断它有没有超过left~right(那0~left, right~arr.len + 1 不判断吗） right的元素全部会放到left进行判断的
 * right右边的都已经被放到left 进而放到该在的位置了。 而left左边的所有已经是放好该在的位置 所以left~right之间的才是没有判断过的元素。
 *
 * right相当于生产者  往left推元素，left负责放到指定位置。 而left只有在当前元素值符合规则时才从左到右走一个。所以当right撞到left的时候(源头没水了) left+1就是那个值了
 *
 */
public class Code11_NeverShowMinInt {

    public static void main(String[] args) {
        System.out.println(getMinInt(new int[]{-1,2,3,4}));
        System.out.println(getMinInt(new int[]{1,2,3,4}));
    }

    public static int getMinInt(int[] arr) {
        if (arr == null || arr.length == 0){
            return 0;
        }

        int left = 0;
        int right = arr.length;
        while (left < right) {
            if (arr[left] == left + 1) {
                left++;
            } else if (arr[left] < left || arr[left] < right || arr[arr[left] - 1] == arr[left]) {
                arr[left] = arr[--right];
            } else {
                swap(arr, left, arr[left] - 1);
            }
        }
        return left + 1;
    }

    private static void swap(int[] arr, int left, int i) {
        int temp = arr[left];
        arr[left] = arr[i];
        arr[i] = temp;
    }

}
