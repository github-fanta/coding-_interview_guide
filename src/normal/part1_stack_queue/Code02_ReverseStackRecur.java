package normal.part1_stack_queue;

import java.util.Stack;

/**
 * 用递归逆转一个栈
 */
public class Code02_ReverseStackRecur {
    /*
    弹出栈底元素
     */
    public static int popBottom(Stack<Integer> stack) {
        int lastVal = stack.pop();
        if (stack.isEmpty()) {
            return lastVal;
        } else {
            int res = popBottom(stack); // 好好保存最底下的值，准备返回
            stack.push(lastVal);
            return res;
        }
    }

    /*
    逆转一个栈，每次把栈底元素pop出来放到栈顶
     */
    public static void reverseStack(Stack<Integer> stack) {
        if (stack == null || stack.isEmpty()) {
            return;
        }
        int bottom = popBottom(stack); //拿出来先不急放，
        reverseStack(stack);  //等到下面都翻转完成后再放进栈中。
        stack.push(bottom);
    }
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        reverseStack(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
