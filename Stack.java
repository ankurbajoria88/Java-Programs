public class Stack {
	private int maxSize;
	private long[] stackArray;
	private int top;

	public Stack(int s) {
	maxSize = s;
	stackArray = new long[maxSize];
	top = -1;
	}

	public void push(long value) {
		stackArray[++top] = value;
	}

	public long pop() {
		return stackArray[top--];
	}

	public long peek() {
		return stackArray[top];
	}

	public boolean isEmpty() {
		return (top == -1);
	}

	public boolean isFull() {
		return (top == maxSize -1);
	}

	public static void main(String agrs[]) {
		Stack stack = new Stack(5);

		stack.push(98472);
		stack.push(10);
		stack.push(73);
		stack.push(763);
		stack.push(76532);

		while(!stack.isEmpty()) {
			System.out.println("Value on stack is:" + stack.pop());
		}
	}
}