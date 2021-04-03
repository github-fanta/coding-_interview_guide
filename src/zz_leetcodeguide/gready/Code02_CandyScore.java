package zz_leetcodeguide.gready;

import java.util.Arrays;
import java.util.concurrent.CancellationException;

/**
 * 一群孩子站成一排，每一个孩子有自己的评分。现在需要给这些孩子发糖果，
 * 规则：如果一个孩子的评分比自己身旁的一个孩子高，那么必须得到比身旁孩子更多的糖果；
 * 所有孩子至少要有一个糖果。求解最少需要多少个糖果。
 * 样例:
 * Input:[1,0,2]
 * Output:5
 */
public class Code02_CandyScore {

    public static void main(String[] args) {
        System.out.println(candy(new int[]{1,0,2}));
    }

    public static int candy(int[] scores) {
        if (scores == null || scores.length == 0) {
            return 0;
        }
        int[] candy = new int[scores.length];
        for (int i = 0; i < candy.length; i++) {
            candy[i] = 1;
        }
        // left to right
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > scores[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            }
        }
        // right to left
        for (int i = scores.length - 2; i >= 0; i--) {
            if (scores[i] > scores[i + 1]) {
                candy[i] = candy[i + 1] + 1;
            }
        }
        return Arrays.stream(candy).sum();
    }

}
