import java.util.ArrayList;

public class Heap {
	private ArrayList array;

	public Heap() {
		array = new ArrayList<Integer>();
	}

	public void insert(int value) {
		array.add(value);
		siftUp();
	}

	public void remove() {
		if (array.size() == 0) {
			System.out.println("Nothing to remove!");
		}else if (array.size() == 1) {
			array.remove(0);
		}else {
			array.set(0, array.remove(array.size() - 1));
			siftDown();
		}
	}

	private void siftUp() {
		// Index of the element stored in 'k' -> New element
		int k = array.size() - 1;
		while(k > 0) {
			// Check for the parent value 'p' at (k-1)/2 -> Parent element
			int p = (k-1)/2;

			// Swap values in k and p is value in k is greater than value in p
			if ((int)array.get(k) > (int)array.get(p)) {
				swap(k, p);
				k = p;
			}else {
				break;
			}
		}
	}

	private void siftDown() {
		// Index of the root element
		int k = 0;
		int childIdx1 = (2*k) + 1;
		int max = childIdx1;
		while(childIdx1 < array.size()) {
			// Get index values for the children of the node
			int childIdx2 = (2*k) + 2;

			if (childIdx1 > 0 && childIdx2 > 0) {
				if (childIdx2 < array.size()) {
					if ((int)array.get(childIdx2) > (int)array.get(childIdx1)) {
						max = childIdx2;
					}	
				}
			}else if (childIdx1 > 0 && childIdx1 < array.size() - 1) {
				max = childIdx1;
			}

			if ((int)array.get(k) < (int)array.get(max)) {
				swap(k, max);
				k = max;
				childIdx1 = (2*k) + 1;
			}else {
				break;
			}
		}
	}

	public void swap(int idx1, int idx2) {
		int tmp = (int)array.get(idx1);
		array.set(idx1, (int)array.get(idx2));
		array.set(idx2, tmp);
	}

	public void print() {
		for (int i = 0; i < array.size(); i++) {
			System.out.print("[" + array.get(i) + "]" + ",");
		}
		System.out.println();
	}

	public static void main(String args[]) {
		Heap heap = new Heap();

		heap.insert(1);
		heap.print();
		heap.insert(2);
		heap.print();
		heap.insert(30);
		heap.print();
		heap.insert(5);
		heap.print();
		heap.insert(90);
		heap.print();
		heap.insert(4);
		heap.print();

		heap.remove();
		heap.print();
		heap.remove();
		heap.print();
		heap.remove();
		heap.print();
	}
}