package easy.part8_array_matrix;

/**
 * 【题目】
 * 给定一个长度为N的整形数组arr, 其中有N个互不相等的自然数1~N, 请实现arr的排序，但是不要把下标0~N-1位置上的数通过
 * 直接赋值的方式替换成1~N。
 * 时间复杂度O(N) 空间复杂度O(1)
 */
public class Code06_SortNaturalNum {
    public static void sort(int[] arr){
        int next = 0; // 锤子
        int swapTemp = 0;
        for (int i = 0; i < arr.length; i++) {
            // 记录本"坑"中的数
            next = arr[i];
            // 循环去"砸"别的坑，知道"砸"出来i位置的数
            while (arr[i] != i+1) {
                swapTemp = arr[next - 1];  // 用next找到对应位置，把它暂时取出来
                arr[next - 1] = next;  // 把next砸进去
                next = swapTemp;  // next变更
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,5,3,4};
        sort(arr);
        for (int e : arr) {
            System.out.println(e);
        }
    }
}
