package normal.part5_string;

public class Code03_TwoStrsMinDistanceInArr {

    public static int minDistance(String[] strs, String str1, String str2) {
        if (str1 == null || str2 == null) {
            return -1;
        }
        if (str1.equals(str2)) {
            return 0;
        }
        int last1 = -1;
        int last2 = -1;
        int resMin = Integer.MAX_VALUE;

        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals(str1)) {
                if (last2 != -1) {
                    resMin = i - last2;
                }
                last1 = i;
            }
            if (strs[i].equals(str2)) {
                if (last1 != -1) {
                    resMin = i - last1;
                }
                last2 = i;
            }
        }
        return resMin == Integer.MAX_VALUE ? -1 : resMin;
    }

    public static void main(String[] args) {
        int minDistance = minDistance(new String[]{"1", "3", "3", "3", "2", "3", "1"}, "1", "2");
        System.out.println(minDistance);
    }
}
