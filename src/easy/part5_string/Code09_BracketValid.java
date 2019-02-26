package easy.part5_string;

public class Code09_BracketValid {
    public static boolean isBracketValid(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        char[] chs = str.toCharArray();
        int status = 0;
        for (char ch : chs) {
            if (ch == '(') {
                status ++;
            } else if (ch == ')') {
                status --;
                if (status < 0) return false;
            } else {
                return false;
            }
        }
        return status == 0;
    }

    public static void main(String[] args) {
        System.out.println(isBracketValid("(()()))"));
    }
}
