public class Hubspot2 {
	
	public static void quickSort(int[] numArray, int low, int high) {
		
		if ((high - low) <= 0) {
			return;
		}

		int splitPoint = split(numArray, low, high);
		quickSort(numArray, low, splitPoint - 1);
		quickSort(numArray, splitPoint + 1, high);

	}

	public static int split(int[] numArray, int low, int high) {
		int left = low + 1;
		int right = high;
		int pivot = numArray[low];

		while (true) {
			while (left <= right) {
				if (numArray[left] < pivot) {
					left ++;
				}else {
					break;
				}
			}

			while (right > left) {
				if (numArray[right] < pivot) {
					break;
				}else {
					right--;
				}
			}

			if (left >= right) {
				break;
			}

			//swap left and right 
			int temp = numArray[left];
			numArray[left] = numArray[right];
			numArray[right] = temp;
			//advance 1 step
			left++; right--;
		}

		//swap pivot with left - 1
		numArray[low] = numArray[left-1];
		numArray[left-1] = pivot;

		return left-1;
	}

	public static int search(int[] array, int value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String args[]) {
		int[] numArray = new int[5];
		numArray[0] = 2;
		numArray[1] = 4;
		numArray[2] = 5;
		numArray[3] = 10;
		numArray[4] = 1;

		int[] copyArray = new int[numArray.length];
		for (int j = 0; j < numArray.length; j++) {
			copyArray[j] = numArray[j];
		}

		if (numArray.length > 1) {
			quickSort(copyArray, 0, numArray.length - 1);
		}

		int max = copyArray[copyArray.length - 1];
		int maxIdx = search(numArray, max);
		int minVal = 0;

		while (true) {
			int min = copyArray[minVal];
			int minIdx = search(numArray, min);
			if (minIdx < maxIdx) {
				System.out.println(numArray[maxIdx] - numArray[minIdx]);
				break;
			}else {
				minVal++;
			}
		}

		for (int i = 0; i < copyArray.length; i++) {
			System.out.println(copyArray[i]);
		}

	}

}