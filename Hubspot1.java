import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;

public class Hubspot1 {
	
	public static String checkForPalidrome(String s) {
		
		boolean isPossible = isPalindromePossible(s);

		if (isPossible) {
			//Does the string need to be re-arranged?
			if (!s.equals(new StringBuilder(s).reverse().toString())) {
				//Find anagram that is palindrome
				String result = permuteString(s, 0, s.length() - 1);
				return (result == null) ? new String("-1") : result;
			}else {
				return s;
			}
		}

		return new String("-1");
	}

	public static boolean isPalindromePossible(String s) {
		int[] count = new int[26];

		for (int i = 0; i < s.length(); i++) {
			count[s.charAt(i) - 'a']++;
		}

		int oddOccur = 0;

		for (int counter : count) {
			if (oddOccur > 1) {
				return false;
			}
			if (counter%2 == 1) {
				oddOccur++;
			}
		}
		return true;
	}

	public static String permuteString(String s, int start, int end) {
		if (start == end -1) {
			if (isPalindromePossible(s)) {
				return s;
			}
		}else {
			for (int j = start, i = end; j < end; j++) {
				s = swap(s, j, i);
				permuteString(s, start + 1, end);
				s = swap(s, j, i);
			}
		}
		return null;
	}

	public static String swap(String s, int idx1, int idx2) {
		StringBuilder newS = new StringBuilder(s);
		char l = s.charAt(idx1);
		char r = s.charAt(idx2);
		newS.setCharAt(idx1, r);
		newS.setCharAt(idx2, l);
		return newS.toString();
	}

	public static void main(String args[]) {
		List<String> strings = new ArrayList<String>(); 
		strings.add("racecar");
		strings.add("level");
		strings.add("aacbb");
		strings.add("hello");
		for (String s : strings) {
			System.out.println(checkForPalidrome(s));
		}
	}

}