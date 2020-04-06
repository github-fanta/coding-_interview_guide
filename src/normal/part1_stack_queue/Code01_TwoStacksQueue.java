package normal.part1_stack_queue;

import java.util.Stack;

/**
 * 两个栈实现队列
 */
public class Code01_TwoStacksQueue {
    public static class TwoStacksQueue{
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;
        public TwoStacksQueue() {
            this.pushStack = new Stack<Integer>();
            this.popStack = new Stack<Integer>();
        }

        public void add(int num) {
            pushStack.push(num);
        }

        public Integer poll() {
            if (pushStack.isEmpty() && popStack.isEmpty()) {
                throw new RuntimeException("队列为空");
            }else if(popStack.isEmpty()) {	//popStack必须为空才能倒
                while(!pushStack.isEmpty()) {	//pushStack要倒就倒完
                    popStack.push(pushStack.pop());
                }
            }

            return popStack.pop();
        }

        public Integer peek() {
            if (pushStack.isEmpty() && popStack.isEmpty()) {
                throw new RuntimeException("队列为空");
            }else if (popStack.isEmpty()) {
                while(!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }
            return popStack.peek();
        }
    }
}
