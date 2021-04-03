package easy.part1_stack_queue;

import java.util.Stack;

public class Code03_SortStackByStack {

	
	public static void sortStackByStack(Stack<Integer> stack) {
		Stack<Integer> help = new Stack<>();
		// 将stack里面的原始值过一遍
		while(!stack.isEmpty()) {
			Integer popV = stack.pop();
			// 如果比help栈顶的值大，就把help栈顶“逼回去”stack
			while(!help.isEmpty() && popV > help.peek()) {
				stack.push(help.pop());
			}
			// 自己稳坐help栈中
			help.push(popV);
			
		}
		// stack过完后 help从上到下 有小到大 于是倒出来就是答案
		while(!help.isEmpty()) {
			stack.push(help.pop());
		}
		
	}

	public static void test(Stack<Integer> stack) {
		if (stack == null) {
			return;
		}
		new Stack<>();
	}
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		// 测试1000000次数
		for(int test=0; test < 1000000; test++) {
			// 随机推入10个0~9的整数
			for(int i=0; i<10; i++) {
				stack.push((int) (Math.random()*10));
				
			}
			// 调用排序过程
			sortStackByStack(stack);
			// pre用来判断排序后顺序从上到下是递减的
			int pre = Integer.MAX_VALUE;
			while(!stack.isEmpty()) {
				if(pre < stack.peek()) {
					System.out.println("wrong answer");
					return;
				}
				pre = stack.pop();
				//System.out.println(stack.pop());
			}
		}
		System.out.println("good answer");
		
	}
}
