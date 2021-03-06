package easy.part4_recursion_dynamic;

/**
 * 给定非负整数数组arr,arr[i]表示可以从位置i向右跳1~k个距离。比如，arr[2]==3,代表从位置2可以跳到位置3、位置4或位置5.
 * 如果从位置0出发，返回最少跳几次能跳到arr最后的位置上。
 */
public class Code01_JumpGame {

    /**
     * 假设当前(位置i)能够跳最远的范围为[curBegin, curEnd], curFarther是在[curBegin, curEnd]范围内能够跳的最远距离(位置)。
     * 一旦当前位置(i)到了curEnd, 说明当前阶段(第一个节点跳跃跨过的落脚点)的一次跳跃结束，最远能跳到curFarthest.
     * @param arr
     * @return
     */
    public static int jump(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int jumpCount = 0;
        int curEnd = 0;
        int curFarthest = 0;
        for (int i = 0; i < arr.length; ++i) {
            // 更新“当前能跳到的最远位置”。使用贪心策略。  如果现在跳到i位置,那么再下一跳最远能跳到多远？
            curFarthest = Math.max(curFarthest, i + arr[i]);
            // [curBegin, curEnd]这段中，要想向前，必须经过curBegin开始才能往前。第一次计算从curBegin最远跳跃到curEnd.
            //                   也就是说第一次跳跃绝对会落在[curBegin, curEnd]之间。
            // 一旦这段选拔完成，就触发一次跳跃。从具体哪个位置起跳的，我不关心。反正它最后会跳到curFarthest位置，而且跳数加一。
            // 也可以这样理解：从curBegin开始跳到curBegin+arr[arrBegin]，不急，在跳跃过程中(i移动中)不断调整跳跃的最优位置
            if (i == curEnd) { // 到达了前一个阶段定的目标后，这一跳就可以做出了(jumpCount++)。
                jumpCount ++;
                curEnd = curFarthest;  // 最后没有跳到curEnd 确定了最终跳跃到的位置 curFarthest  下一个阶段能跳到的最远位置定下来
                if (curFarthest >= arr.length - 1) return jumpCount; // 跳跃完成，检查是否可以跳到最后一个了。大于也覆盖了最后一个
            }
        }
        return jumpCount;
    }


    /**
     * 书中标准思考：owesome
     * 给定非负整数数组arr,arr[i]表示可以从位置i向右跳1~k个距离。比如，arr[2]==3,代表从位置2可以跳到位置3、位置4或位置5.
     * 如果从位置0出发，返回最少跳几次能跳到arr最后的位置上。
     */
    public static int jumpp(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int jump = 0;
        int cur = 0; // 如果只能跳jump步，能到达的最远的位置
        int next = 0; // 如果再多跳一步，最远能到达的位置，(其实是cur跳之前要先计算好 下一步跳到哪里，所以有了这个next)
        for (int i = 0; i < arr.length; i++) {
            if (i > cur) { // 一旦超过了jump能到的最远位置 就需要再跳一步了
                jump ++;
                cur = next;
            }
            next = Math.max(next, i + arr[i]);
        }
        return jump;
    }










    public static void main(String[] args) {
        System.out.println(jumpp(new int[]{3,2,3,1,1,4}));
    }
}
