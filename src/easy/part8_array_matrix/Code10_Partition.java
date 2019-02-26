package easy.part8_array_matrix;

/**
 * 给定一个有序数组arr, 调整arr使得这个数组的左半部分没有重复元素且升序，而不用保证右部分是否有序。
 * 例如，arr=[1,2,2,2,3,3,4,5,6,6,7,7,8,8,8,9],调整之后 arr=[1,2,3,4,5,6,7,8,9,...]
 *
 * 【补充题目】
 * 给定一个数组arr，其中只可能含有0、1、2三个值，请实现arr的排序。
 * 另一种问法：有一个数组，其中只有红球、篮球和黄球，请实现红球全放在数组的左边，篮球放在中间，黄球放在右边。
 * 另一种问法：有一个数组，再给定一个值k，请实现比k小的数都放在数组的左边，等于k的数都放在数组的中间，比k大的数
 * 都放在数组的右边。
 */
public class Code10_Partition {
    // 题目一
    public static void leftUnique(int[] arr) {
        if (arr ==null || arr.length < 2) {
            return;
        }
        int L = 0;
        int R = 0;
        while (R < arr.length) {
            if (arr[L] != arr[R]) {
                swap(arr, ++L, R);
            }
            R ++;
        }
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int small = -1;
        int larger = arr.length;
        int cur = 0;
        while (cur < larger) {
            if (arr[cur] == 0) {
                swap(arr, cur, ++small);
                cur ++; // 探索指针不能插在人家小于的范围吧
            } else if (arr[cur] == 2) {
                swap(arr, cur, --larger); // 这时候cur不用前进，因为左边的范围没变。探索指针在它应该在的位置
            } else {
                cur ++;
            }
        }
    }
    private static void swap(int[] arr, int i, int r) {
        int temp = arr[i];
        arr[i] = arr[r];
        arr[r] = temp;
    }



    public static void main(String[] args) {
        //int[] arr = new int[]{1,2,2,2,3,3,4,5,5};
        //leftUnique(arr);
        int[] arr = new int[]{1,2,2,2,0,0,0,0};
        sort(arr);
        for (int e : arr) {
            System.out.println(e);
        }
    }
}
