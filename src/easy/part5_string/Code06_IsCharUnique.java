package easy.part5_string;
// 给定一个字符类型数组chas[],判断chas中是否所有的字符都只出现过一次 简单要求：实现时间复杂度为O(N)的方法
public class Code06_IsCharUnique {
    public static boolean isUnique(char[] chas){
        if (chas == null || chas.length == 0) {
            return true;
        }
        boolean[] bucket = new boolean[256];
        for(char ch : chas) {
            if (bucket[ch] == true) return false;
            bucket[ch] = true;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isUnique(new char[]{'1', 'z', 'z'}));
    }
}
