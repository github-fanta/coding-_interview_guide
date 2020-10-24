package normal.part5_string;

public class Code02_findStrInNullableArray {

    public static void main(String[] args) {
        String[] strs1 = {null, "a", null, "a", null, "b", null, "c"};
        String str1 = "a";

        String[] strs2 = {null, "a", null, "a", null, "b", null, "c"};
        String str2 = null;

        String[] strs3 = {null, "a", null, "a", null, "b", null, "c"};
        String str3 = "d";


        int idx1 = getIndex(strs1, str1);
        int idx2 = getIndex(strs2, str2);
        int idx3 = getIndex(strs3, str3);
    }

    public static int getIndex(String[] strs, String aimStr) {
        if (strs == null || strs.length == 0 || aimStr == null) {
            return -1;
        }
        int res = -1;
        int left = 0;
        int right = strs.length - 1;

        int mid = 0;
        int i = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (strs[mid] == null) {
                // 中点为空
                // 找左边最近的不为空的字符串
                while (i >= left && strs[i] == null) {
                    i --;
                }
                // 找完之后 看是否越界
                if (i < left) {
                    left = mid + 1;
                    continue;
                }
                // 比较 决定走向
                if (strs[i].compareTo(aimStr) == 0) {
                    res = i;
                    right = mid - 1;
                } else if (strs[i].compareTo(aimStr) < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                // 中点不为空
                if (strs[mid].compareTo(aimStr) == 0) {
                   res = mid;
                   right = mid - 1;
                } else if (aimStr.compareTo(strs[mid]) < 0) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return res;
    }
}
