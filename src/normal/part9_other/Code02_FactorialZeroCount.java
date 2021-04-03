package normal.part9_other;

/**
 * 给定一个非负整数N, 返回N!结果的末尾为0的数量
 * 例如3！=6 结果的末尾没有0， 则返回0 5！ = 120，结果的末尾有1个0，返回1.
 * 1000000000！， 结果的末尾有249999998个0 返回249999998
 */
public class Code02_FactorialZeroCount {

    /**
     * 方法一： 代价logN(以5位底)
     * @param num
     * @return
     */
    public static int getCount(int num) {
        if (num < 0) {
            return 0;
        }
        int resCount = 0;
        for (int i = 5; i <= num; i += 5){
            int cur = i;
            while (cur % 5 == 0) {
                resCount ++;
                cur /= 5;
            }
        }
        return resCount;
    }

    /**
     * 方法2:
     * 首先，每5个数会提供一个5的因子（1,2,3,4,5） （6,7,8,9,10） （11,12,13,14,15） 。。。
     * 其次，第二层由第一层 每组最后一个数组成的组合（5,10,15,20,25） 中 25(5^2) 又能多提供一个5  一共有N/5^2个这样的数
     * 最后，第n层中 能提供N/5^2个这样的数
     * 所以每层提供的5加载一起  一共能提供 Z = N/5 + N/(5^2) + N/(5^3) + ... +N/(5^i)
     */
    public static int getCount2(int num) {
        if (num < 0) {
            return 0;
        }
        int resCount = 0;
        while (num != 0) {
            resCount += (num/5);
            num /= 5;
        }
        return resCount;
    }

    public static void main(String[] args) {
        System.out.println(getCount(1000000000));
        System.out.println(getCount2(1000000000));
    }
}
