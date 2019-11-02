public class Queue {
	private int maxSize;
	private long[] queueArray;
	private int idx;
	private int front;

	public Queue(int s) {
	maxSize = s;
	queueArray = new long[maxSize];
	front = 0;
	idx = -1;
	}

	public void enQueue(long value) {
		queueArray[++idx] = value;
	}

	public long deQueue() {
		return queueArray[front++];
	}

	public long front() {
		return queueArray[0];
	}

	public boolean isEmpty() {
		return (front == maxSize);
	}

	public boolean isFull() {
		return (idx == maxSize -1);
	}

	public static void main(String agrs[]) {
		Queue queue = new Queue(5);

		queue.enQueue(873);
		queue.enQueue(3242);
		queue.enQueue(85462);
		queue.enQueue(1234);
		queue.enQueue(53457);

		while(!queue.isEmpty()) {
			System.out.println("Value in queue is:" + queue.deQueue());
		}
	}
}