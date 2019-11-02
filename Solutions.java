package main.java.com.srccode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by AB on 9/26/2016.
 */
public class Solutions {

    //Reverse with new string
    public static String reverseString(String s) {
        if (s == null) {
            return s;
        }
        if (s.length() == 0) {
            return s;
        }

        String newString = new String();
        for (int i = s.length() - 1; i >= 0; i--) {
            newString += s.charAt(i);
            System.out.print(s.charAt(i));
        }
        System.out.println("");
        return newString;
    }

    //Reverse in-place
    public static String reverseString2(String s) {
        char[] cArray = s.toCharArray();
        for (int i = 0, j = s.length() - 1; i < s.length() / 2; i++, j--) {
            char c = cArray[i];
            cArray[i] = cArray[j];
            cArray[j] = c;
        }
        return cArray.toString();
    }

    //Add numbers without using operators
    public static int getSum(int a, int b) {
        if (b == 0) {
            return a;
        }

        while (b != 0) {
            int sum = a ^ b;
            int carry = (a & b);
            b = carry << 1;
        }
        System.out.println(a);
        return a;
    }

    //Add digits of a number together in-place
    public static int addDigits(int num) {
        int add = num - 9*((num - 1)/9);
        System.out.println(add);
        return add;
    }

    public static boolean isAnagram(String s, String t) {

        HashMap map = new HashMap<Character, Integer>();

        if (s.length() != t.length()) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (map.containsKey(c)) {
                int temp = (int) map.get(c);
                temp++;
                map.put(c, temp);
            }else {
                map.put(c, 1);
            }

        }

        for (int j = 0; j < t.length(); j++) {
            char c = t.charAt(j);

            if (map.containsKey(c)) {
                int temp = (int) map.get(c);
                if (temp == 1) {
                    map.remove(c);
                }else {
                    temp--;
                    map.put(c, temp);
                }
            }else {
                return false;
            }

        }

        if (map.isEmpty()) {
            return true;
        }

        return false;
    }

    public static boolean isPalindrome(int x) {
        if (x == 0 || x % 10 == 0) {
            return false;
        }
        int y = x;
        int reverse = 0;
        while (x > 0) {
            int digit = x % 10;
            reverse = reverse * 10 + digit;
            x = x / 10;
        }

        if (y == reverse) {
            System.out.println("true");
            return true;
        }

        System.out.println("false");
        return false;
    }

    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        return fibonacci(n-1) + fibonacci(n-2);
    }

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }

        return n * factorial(n-1);
    }

    //O(n^2) approach
    public static boolean isUnique(String s) {
        for (int i = 0; i < s.length(); i++) {
            CharSequence car = Character.toString(s.charAt(i));
            if (i == 0) {
                if (s.substring(1, s.length() - 1).contains(car)) {
                    return false;
                }
            }else if (i == s.length() -1) {
                if (s.substring(0, s.length() - 2).contains(car)) {
                    return false;
                }
            }else {
                if (s.substring(0, i).contains(car) || s.substring(i + 1, s.length() - 1).contains(car)) {
                    return false;
                }
            }
        }
        return true;
    }

    //O(n) approach with O(1) space
    public static boolean isUnique2(String s) {
        boolean[] char_set = new boolean[128];
        for (int i = 0; i < s.length(); i++) {
            int val = (int) s.charAt(i);
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    //O(n) approach with reduced space
    public static boolean isUnique3(String s) {
        int checker = 0;
        for (int i = 0; i < s.length(); i++) {
            int val = (int) s.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= (1 << val);
        }
        return true;
    }

    public static void replaceSpace(String s, int len) {
        char[] array = s.toCharArray();
        int spaceCount = 0;
        for (int i = 0; i < len; i++) {
            if (array[i] == ' ') {
                spaceCount++;
            }
        }

        int newLen = len + spaceCount * 2;

        for (int j = len - 1; j >= 0; j --) {
            if (array[j] == ' ') {
                array[newLen - 1] = '0';
                array[newLen - 2] = '2';
                array[newLen - 3] = '%';
                newLen = newLen - 3;
            }else {
                array[newLen - 1] = array[j];
                newLen = newLen - 1;
            }
        }
        System.out.println(String.valueOf(array));
    }

    public static boolean checkBrackets(String s) {
        int j = s.length() - 1;
        for (int i = 0; i < s.length()/2; i++) {
            if (s.charAt(i) == '(' && s.charAt(j) != ')') {
                return false;
            }else if (s.charAt(i) == '{' && s.charAt(j) != '}') {
                return false;
            }else if (s.charAt(i) == '[' && s.charAt(j) != ']') {
                return false;
            }
            j--;
        }
        return true;
    }

    //With StringBuffer
    public static String compressString1(String s) {
        int size = countCompression(s);
        if (size >= s.length()) return s;

        StringBuffer newStr = new StringBuffer(); //Can use char[] instead: char[] array = new char[size];
        char last = s.charAt(0);
        int count = 1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == last) {
                count ++;
            }else {
                newStr.append(last); //Replace next two lines with change for char[]
                newStr.append(count);
                last = s.charAt(i);
                count = 1;
            }
        }
        newStr.append(last);
        newStr.append(count);
        return newStr.toString();
    }

    public static int countCompression(String s) {
        if (s == null || s.isEmpty()) return 0;
        char last = s.charAt(0);
        int size = 0;
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == last) {
                count++;
            }else {
                last = s.charAt(i);
                size = size + String.valueOf(count).length() + 1;
                count = 1;
            }
        }
        size += 1 + String.valueOf(count).length();
        return size;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isRotation(String s1, String s2) {
        int len = s1.length();
        if (len == s2.length() && len > 0) {
            String s1s1 = s1 + s1;
            //return isSubString(s1s1, s2);
        }
        return false;
    }

    public static ArrayList<String> getPermutations(String text) {
        ArrayList<String> results = new ArrayList<String>();

        // the base case
        if (text.length() == 1) {
            results.add(text);
            return results;
        }

        for (int i = 0; i < text.length(); i++) {
            char first = text.charAt(i);
            String remains = text.substring(0, i) + text.substring(i + 1);

            ArrayList<String> innerPermutations = getPermutations(remains);

            for (int j = 0; j < innerPermutations.size(); j++)
                results.add(first + innerPermutations.get(j));
        }

        return results;
    }

    public static void toBinary(int number) {

        StringBuffer buffer = new StringBuffer();

        while (number > 0) {
            int remainder = number % 2;
            buffer.append(remainder);
            number = number / 2;
        }
        System.out.println(buffer.reverse().toString());
    }

    // Trial division method
    public static boolean isPrime(int number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Get all prime numbers upto n
    public static int[] sieveOfEratosthenes(int n) {
        int[] arr = new int[n + 1];

        for (int a = 2; a <= n; a++) {
            arr[a] = 1;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            for (int j = 1; i * j <= n; j++) {
                arr[i*j] = 0;
            }
        }

        for (int b = 2; b < arr.length; b++) {
            if (arr[b] == 1) {
                System.out.println(b);
            }
        }

        return arr;
    }

    public static void findFactors(int n) {
        ArrayList arr = new ArrayList<Integer>();

        for (int i = 1; i <= Math.sqrt(n); i ++) {
            if (n % i == 0) {
                arr.add(i);
                if (i != Math.sqrt(n)) {
                    arr.add(n/i);
                }
            }
        }

        for (int j = 0; j < arr.size(); j++) {
            System.out.println(arr.get(j));
        }
    }

    public static void primeFactorization(int num) {

        int count = 0;
        int n = num;

        for (int i = 2; i < Math.sqrt(num); i++) {

            if (n % i == 0) {
                count = 0;
                while (n % i == 0) {
                    n = n / i;
                    count++;
                }
            }
            System.out.println("The count for " + i + " is " + count);
        }

        if (n != 1) {
            System.out.println("The count for " + n + " is 1");
        }

    }

    public static int GCD(int n1, int n2) {

        int divisor = Math.min(n1, n2);
        int remainder = 0;
        int dividend = Math.max(n1, n2);

        while (divisor != 0) {
            remainder = dividend / divisor;
            dividend = divisor;
            divisor = remainder;
        }

        return dividend;
    }


    public static class TreeNode {
        public int data;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static class Node {
        public int value;
        public Node next;

        public Node() {}

        public Node(int value, Node next) {
            this.next = next;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }


    public static void rotateMatrix(int[][] matrix, int n) {
        for (int layer = 0; layer < n/2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                //save top
                int top = matrix[first][i];

                //left->top
                matrix[first][i] = matrix[last-offset][first];

                //bottom->left
                matrix[last-offset][first] = matrix[last][last-offset];

                //right->bottom
                matrix[last][last-offset] = matrix[i][last];

                //top->right
                matrix[i][last] = top;
            }
        }
    }

    public static Node partitionLL(Node node, int x) {
        Node before = null;
        Node after = null;
        Node equal = null;

        while(node != null) {
            if (node.getValue() < x) {
                if (before == null) {
                    before = new Node(node.getValue(), null);
                }else {
                    before.setNext(new Node(node.getValue(), null));
                }
            }else if (node.getValue() > x){
                if (after == null) {
                    after = new Node(node.getValue(), null);
                }else {
                    after.setNext(new Node(node.getValue(), null));
                }
            }else {
                if (equal == null) {
                    equal = new Node(node.getValue(), null);
                }
            }
            node = node.getNext();
        }

        equal.setNext(after);
        before.setNext(equal);

        return before;
    }

    public static String LCS(String s1, String s2) {
        char[] A = s1.toCharArray();
        char[] B = s2.toCharArray();
        int[][] LcsMatrix = new int[s1.length() + 1][s2.length() + 1];
        String[][] solution = new String[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                solution[i][j] = "0";
            }
        }

        for (int a = 1; a <= s1.length(); a++) {
            for (int b = 1; b <= s2.length(); b++) {
                if (A[a-1] == B[b-1]){
                    LcsMatrix[a][b] = LcsMatrix[a - 1][b - 1] + 1;
                    solution[a][b] = "d";
                }else {
                    LcsMatrix[a][b] = Math.max(LcsMatrix[a - 1][b], LcsMatrix[a][b - 1]);
                    if (LcsMatrix[a][b] == LcsMatrix[a - 1][b]){
                        solution[a][b] = "t";
                    }else {
                        solution[a][b] = "l";
                    }
                }
            }
        }

        String x = solution[A.length][B.length];
        String answer = "";
        int a = A.length;
        int b = B.length;

        while (x != "0") {
            if (solution[a][b] == "d") {
                answer = A[a-1] + answer;
                a--;
                b--;
            }else if (solution[a][b] == "l") {
                b--;
            }else if (solution[a][b] == "t") {
                a--;
            }
            x = solution[a][b];
        }

        return answer;
    }

    public static String shortestUniqueSubString(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String subS = s.substring(i,j);
                if (map.containsKey(subS)) {
                    map.put(subS, map.get(subS) + 1);
                }else {
                    map.put(subS, 1);
                }
            }
        }

        int count = 1;
        StringBuilder answer = new StringBuilder();
        String temp = null;
        int minLen = s.length();

        for (Map.Entry entry: map.entrySet()) {
            temp = entry.getKey().toString();
            if (temp.length() < minLen && Integer.valueOf(entry.getValue().toString()) == count) {
                minLen = temp.length();
            }
        }

        for (Map.Entry entry: map.entrySet()) {
            temp = entry.getKey().toString();
            if (temp.length() == minLen && Integer.valueOf(entry.getValue().toString()) == count) {
                answer.append(temp);
                answer.append(":");
            }
        }

        return answer.toString();
    }

    public static int sumCount(int k) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        int sum = 0;
        for (int i = 0; i <= k; i++) {
            int digit;
            int num = i;
            while (num != 0) {
                digit = num%10;
                num = num/10;
                sum += digit;
            }
            if (!map.containsKey(sum)) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(sum, list);
            }else {
                List<Integer> list = map.get(sum);
                list.add(i);
                map.put(sum, list);
            }
            sum = 0;
        }

        return 0;
    }

    public static int primeSum(int k) {
        ArrayList<Integer> list = new ArrayList<>();

        boolean prime;
        for (int i = 2; i <= k; i++) {
            prime = true;
            for (int a = 2; a < i; a ++) {
                if (i % a == 0) {
                    prime = false;
                }
            }
            if (prime) {
                list.add(i);
            }
        }

        int num1 = 0;
        int num2 = 0;
        int min1 = 0;
        int min2 = 0;
        for (int j = 0; j < list.size(); j++) {
            num1 = list.get(j);
            for (int l = 1; l < list.size(); l++) {
                num2 = list.get(l);

                if (num1 + num2 == k) {
                    if (min2 == 0 && min1 == 0) {
                        min1 = num1;
                        min2 = num2;
                    }else if (num2 < min2){
                        min2 = num2;
                    }else if (num1 < min1) {
                        min1 = num1;
                    }
                }
            }
        }
        return Math.min(min1, min2);
    }

    public static void printBSTInLevelOrder(TreeNode root) {

        LinkedList<TreeNode> llist = new LinkedList<>();

        llist.add(root);

        int nodesInCurrentLevel = 1;
        int nodesInNextLevel = 0;

        while(!llist.isEmpty()) {

            TreeNode temp = llist.removeFirst();
            nodesInCurrentLevel--;

            System.out.print(temp.data + " ");

            if (temp.left != null) {
                llist.add(temp.left);
                nodesInNextLevel++;
            }

            if (temp.right != null) {
                llist.add(temp.right);
                nodesInNextLevel++;
            }

            if (nodesInCurrentLevel == 0) {
                System.out.println();
                nodesInCurrentLevel = nodesInNextLevel;
                nodesInNextLevel = 0;
            }

        }

    }

    public static String compressString2(String str) {
        char[] arr = str.toCharArray();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        for (char c : arr) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            }else {
                int count = map.get(c);
                map.put(c, count + 1);
            }
        }

        StringBuffer strBuf = new StringBuffer();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                strBuf.append(entry.getKey());
            }
        }

        if (strBuf.toString().isEmpty()) {
            return "Empty String";
        }else {
            return strBuf.toString();
        }
    }

    public static String isPangram(String str) {
        char[] arr = str.toCharArray();
        HashSet<Character> set = new HashSet<Character>();

        for (char c : arr) {
            if (c != ' ') {
                set.add(Character.toLowerCase(c));
            }
        }

        if (set.size() == 26) {
            return "pangram";
        }else {
            return "not pangram";
        }
    }

    public static void rotateArray() {
        int n = 3;
        int k = 2;
        int[] a = new int[n];
        int[] m = new int[n];

        a[0] = 1;
        a[1] = 2;
        a[2] = 3;

        m[0] = 0;
        m[1] = 1;
        m[2] = 2;

        for (int i = 0; i < n; i++) {
            if (m[i] > k) {
                System.out.println(a[(m[i]-k) % n]);
            }else {
                System.out.println(a[(n - k + m[i]) % n]);
            }
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                int idx1 = i;
                int idx2 = j;
                if (nums[i] + nums[j] == target) {
                    result[0] = idx1;
                    result[1] = idx2;
                    break;
                }
            }
        }
        return result;
    }

    public static void diagnalDifference(int[][] a) {

        int n = a.length;

        int primaryDiagSum = 0;
        int secondaryDiagSum = 0;

        int j = -1;
        for (int i = 0; i < n; i ++) {
            j++;
            int number = a[i][j];
            primaryDiagSum += number;
        }

        int k = n;
        for (int l = 0; l < n; l ++) {
            k--;
            int number = a[l][k];
            secondaryDiagSum += number;
        }

        System.out.println(Math.abs(primaryDiagSum - secondaryDiagSum));
    }

    public static void printPyramid(int n) {
        for (int i = 1; i <= n; i++) {

            for (int j = 0; j < n - i; j++) {
                System.out.print(" ");
            }

            for (int k = 0; k < i; k++) {
                System.out.print("#");
            }

            System.out.println();
        }
    }

    public static void convertTime(String time) {
        String[] parts = time.split(":");
        Boolean pm = Boolean.valueOf(parts[2].substring(2, parts[2].length() - 1).startsWith("P"));

        if (pm) {
            if (Integer.valueOf(parts[0]) < 12) {
                parts[0] = String.valueOf(Integer.valueOf(parts[0]) + Integer.valueOf(12));
            }
            System.out.println(parts[0] + ":" + parts[1] + ":" + parts[2].substring(0, 2));
        }else {
            if (Integer.valueOf(parts[0]) == 12) {
                parts[0] = "00";
            }
            System.out.println(parts[0] + ":" + parts[1] + ":" + parts[2].substring(0, 2));
        }
    }

    public static Node MergeLists(Node headA, Node headB) {
        // This is a "method-only" submission.
        // You only need to complete this method

        Node head = null;

        if (headA == null && headB == null) {
            return null;
        }

        if (headA == null && headB != null) {
            return headB;
        }

        if (headB == null && headA != null) {
            return headA;
        }

        if (headA.value < headB.value) {
            head = headA;
            headA = headA.next;
        }else {
            head = headB;
            headB = headB.next;
        }

        Node current = head;
        while (headA != null || headB != null) {

            if (headA == null) {
                current.next = headB;
                return head;
            }else if (headB == null) {
                current.next = headA;
                return head;
            }

            if (headA.value < headB.value) {
                current.next = headA;
                current = current.next;

                headA = headA.next;
            }else {
                current.next = headB;
                current = current.next;

                headB= headB.next;
            }
        }

        current.next = null;

        return head;
    }

    public class Pair {

        private String left;
        private String right;

        public Pair(String left, String right) {
            this.left = left;
            this.right = right;
        }

        public String getLeft() {
            return left;
        }

        public String getRight() {
            return right;
        }

    }

    public void printCombinations(Vector<Vector<String>> lists) {

        HashSet<Pair> productResults = new HashSet<>();
        HashSet<Pair> intermediateProductResults = new HashSet<>();
        int size = lists.size();
        int counter = 0;
        int iteration = 0;
        while (counter < size) {
            if (iteration == 0) {
                productResults.addAll(cartesianProduct(lists.get(counter), lists.get(counter + 1)));
                counter += 2;
                iteration++;
            }else {
                if (!intermediateProductResults.isEmpty()) {
                    intermediateProductResults.clear();
                }
                intermediateProductResults.addAll(productResults);
                productResults.clear();
                productResults.addAll(cartesianProduct(convertPairsToList(intermediateProductResults), lists.get(counter)));
                counter++;
            }
        }

        for (Pair item : productResults) {
            System.out.println(item.getLeft() + " " + item.getRight());
        }
    }

    public HashSet<Pair> cartesianProduct(Vector<String> list1, Vector<String> list2) {

        HashSet<Pair> products = new HashSet<>();
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                products.add(new Pair(list1.get(i), list2.get(j)));
            }
        }

        return products;
    }

    public Vector<String> convertPairsToList(HashSet<Pair> set) {

        Vector<String> newVector = new Vector<>();

        for (Pair item : set) {
            newVector.add(item.getLeft() + " " + item.getRight());
        }

        return newVector;
    }

    public HashSet<HashSet<String>> cartesianProductRecursive1(int index, Vector<Vector<String>> lists) {

        HashSet<HashSet<String>> ret = new HashSet<>();

        if (index == lists.size()) {
            ret.add(new HashSet<String>());
        }else {
            for (String item : lists.get(index)) {
                for (HashSet set : cartesianProductRecursive1(index + 1, lists)) {
                    set.add(item);
                    ret.add(set);
                }
            }
        }
        return ret;
    }

    public Vector<Vector<String>> cartesianProductRecursive2(Vector<Vector<String>> lists) {
        Vector<Vector<String>> resultLists = new Vector<>();
        if (lists.size() == 0) {
            resultLists.add(new Vector<String>());
            return resultLists;
        } else {
            Vector<String> firstList = lists.get(0);
            Vector<Vector<String>> remainingLists = cartesianProductRecursive2(new Vector<>(lists.subList(1, lists.size())));
            for (String item : firstList) {
                for (Vector<String> remainingList : remainingLists) {
                    Vector<String> resultList = new Vector<>();
                    resultList.add(item);
                    resultList.addAll(remainingList);
                    resultLists.add(resultList);
                }
            }
        }
        return resultLists;
    }

    public int calculatePhoneBill(String s) {

        int charge = 0;
        // Treemap maintains the elements in sorted ascending order
        TreeMap<String, Long> map = new TreeMap<>();

        if (s != null) {
            for (String item : s.split("\n")) {
                String[] parts = item.split(",");
                if (!map.containsKey(parts[1])) {
                    Long temp = convertToSeconds(parts[0]);
                    map.put(parts[1], temp);
                } else {
                    Long temp = map.get(parts[1]);
                    temp += convertToSeconds(parts[0]);
                    map.put(parts[1], temp);
                }
            }
        }else {
            return -1;
        }

        // Sorting by values to get the highest call on the top to just skip over
        // since it is free
        LinkedHashMap<String, Long> sortedMap = sortByValues(map);

        int iteration = 0;
        for (Map.Entry<String, Long> entry : sortedMap.entrySet()) {
            if (iteration > 0) {
                Long fiveMinutes = convertToSeconds("00:05:00");
                if (Objects.equals(entry.getValue(), fiveMinutes)) {
                    Long timeInMinutes = 5L;
                    charge += (int) (timeInMinutes * 150);
                } else if (entry.getValue() > fiveMinutes) {
                    Long timeInMinutes = 0L;
                    if (checkForSeconds(entry.getValue())) {
                        timeInMinutes = TimeUnit.SECONDS.toMinutes(entry.getValue()) + 1;
                    } else {
                        timeInMinutes = TimeUnit.SECONDS.toMinutes(entry.getValue());
                    }
                    charge += (int) (timeInMinutes * 150);
                } else if (entry.getValue() < fiveMinutes) {
                    charge += (int) (entry.getValue() * 3);
                }
            }
            iteration++;
        }
        return charge;
    }

    private boolean checkForSeconds(Long time) {
        // This was probably wrong in the solution, should be 60 not 10
        if (time % 60 == 0) {
            return false;
        }
        return true;
    }

    public Long convertToSeconds(String time) {
        // sdf will take default values for unprovided info like date
        // year value starts at 1970
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        Long convertedTime = 0L;
        try {
            Date date = sdf.parse(time);
            convertedTime = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return TimeUnit.MILLISECONDS.toSeconds(convertedTime) - 18000;
    }

    private static LinkedHashMap sortByValues(TreeMap map) {
        List<Map.Entry<String, Long>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
            // o2 first to get descending order, o1 first to get ascending
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                return (o2.getValue().compareTo(o1.getValue()));
            }
        });

        // Linkedhashmap stores elements in the order that they are inserted maintaing the
        // relative ordering
        LinkedHashMap sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Long> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    // O(n^2) approach with O(1) space used
    public static int[] partitionArrayPosNeg(int[] array) {

        // Track the element being looked at since the array is constantly changing
        int key = 0;

        // Loop through the array
        for (int i = 0; i < array.length; i++) {

            // Store the value at the current idx
            key = array[i];

            // Store the value of prev idx
            int j = i-1;

            // If a -ve number is found then all positive elements before it
            // have to shift right by 1 place
            if (key < 0 && i > 0) {
                while (j >= 0 && array[j] >= 0) {
                    array[j + 1] = array[j];
                    j--;
                }
            }

            // Put the key back in the right position
            array[j + 1] = key;
        }
        return array;
    }

    // O(n) approach with partition logic from quick sort - relative ordering not maintained
    public static int[] partitionArray2(int[] array) {

       int pivot = 0;
       int pIdx = 0;

       for (int i = 0; i < array.length; i++) {
           if (array[i] < pivot && i > pivot) {
               int temp = array[i];
               array[i] = array[pIdx];
               array[pIdx] = temp;
               pIdx++;
           }
       }

       return array;
    }

    // O(n) approach for Dutch Flag problem - sorting 0,1,2
    public static int[] dutchFlagPartition(int[] a) {

        int lo = 0;
        int hi = a.length - 1;
        int mid = 0,temp=0;
        while (mid <= hi)
        {
            switch (a[mid])
            {
                case 0:
                {
                    temp   =  a[lo];
                    a[lo]  = a[mid];
                    a[mid] = temp;
                    lo++;
                    mid++;
                    break;
                }
                case 1:
                    mid++;
                    break;
                case 2:
                {
                    temp = a[mid];
                    a[mid] = a[hi];
                    a[hi] = temp;
                    hi--;
                    break;
                }
            }
        }
        return a;
    }

    public static int mehdi(int[] arr) {

        int max = Integer.MIN_VALUE;
        int startIdx = 0;
        int endIdx = 0;
        int prevSum = 0;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            prevSum = sum;
            sum = Math.max(sum + arr[i], arr[i]);
            if (sum == prevSum + arr[i]) {
                endIdx = i;
            }else {
                startIdx = i;
                endIdx = i;
            }
            max = Math.max(max, sum);
        }

        System.out.println("Start idx is: " + startIdx);
        System.out.println("End idx is: " + endIdx);

        return max;
    }

    public static double[] maxSubarray(double[] a) {
        double max_so_far = 0;
        double max_ending_here = 0;
        int max_start_index = 0;
        int startIndex = 0;
        int max_end_index = -1;

        for(int i = 0; i < a.length; i++) {
            if(max_ending_here +a[i] < 0) {
                startIndex = i+1;
                max_ending_here = 0;
            }
            else {
                max_ending_here += a[i];
            }

            if(max_ending_here > max_so_far) {
                max_so_far = max_ending_here;
                max_start_index = startIndex;
                max_end_index = i;
            }
        }

        if(max_start_index <= max_end_index) {
            return Arrays.copyOfRange(a, max_start_index, max_end_index+1);
        }

        return null;
    }

    public static void ceaserCipher(String s, int k) {

        char[] arr = s.toCharArray();
        String result = "";

        for (char c : arr) {
            result += encrypt(c, k);
        }

        System.out.println(result);
    }

    public static char encrypt(char c, int k) {
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] low = lowerCase.toCharArray();
        char[] up = upperCase.toCharArray();

        if (Character.isAlphabetic(c)) {
            if (Character.isLowerCase(c)) {
                return low[Math.abs(c - 'a' + k) % 26];
            }else if (Character.isUpperCase(c)) {
                return up[Math.abs(c - 'A' + k) % 26];
            }
        }
        return c;
    }

    public static int sos(String s) {
        int parts = s.length()/3;
        int startIdx = 0;
        int changes = 0;

        for (int i = 0; i < parts; i++) {
            String temp = s.substring(startIdx, startIdx + 3);
            char[] arr = temp.toCharArray();
            if (arr[0] != 'S') {
                changes++;
            }
            if (arr[1] != 'O') {
                changes++;
            }
            if (arr[2] != 'S') {
                changes++;
            }
            startIdx += 3;
        }
        return changes;
    }

    public static void fizzBuzz() {

        for (int i = 0; i < 101; i++) {
            if (i % 15 == 0) {
                System.out.println("FizzBuzz");
            }else if (i % 3 == 0) {
                System.out.println("Fizz");
            }else if (i % 5 == 0) {
                System.out.println("Buzz");
            }else {
                System.out.println(i);
            }
        }
    }

    public static void deleteAllGreater(Node head, int x) {

        Node current = head;
        Node prev = null;

        for(Node curr=head; curr!=null; curr=curr.next) {
            if (current.value > x) {
                if (prev == null) {
                    head = head.next;
                }else {
                    prev.next = current.next;
                }
            }else {
                prev = current;
            }
        }
    }

    public static int longestUniqueSubString(String s) {

        String longestSubS = "";
        int maxLen = 0;
        int currLen = 0;
        LinkedHashSet<Character> set = new LinkedHashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                if (currLen > maxLen) {
                    maxLen = currLen;
                    for (Character c1 : set) {
                        longestSubS += c1;
                    }
                    set.clear();
                    currLen = 0;
                }
            }else {
                set.add(c);
                currLen++;
            }
        }

        System.out.println("Longest unique substring: " + longestSubS);

        return maxLen;
    }

    public static int longestPalindromicSubString(String s) {

        String longestStr = s.substring(0, 1);

        for (int i = 0; i < s.length(); i++) {
            String p1 = expandAroundCenter(s, i, i);
            if(p1.length() > longestStr.length()) {
                longestStr = p1;
            }

            String p2 = expandAroundCenter(s, i, i + 1);
            if (p2.length() > longestStr.length()) {
                longestStr = p2;
            }
        }

        System.out.println("Longest palindromic substring is: " + longestStr);

        return longestStr.length();
    }

    public static String expandAroundCenter(String str, int start, int end) {

        int left = start, right = end;

        while(left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        return str.substring(left + 1, right - left -1);
    }

    public static int stringToInteger(String str) {
        int answer = 0, factor = 1;
        for (int i = str.length()-1; i >= 0; i--) {
            answer += (str.charAt(i) - '0') * factor;
            factor *= 10;
        }
        return answer;
    }

    public static void main(String args[]) {

        Solutions test = new Solutions();
        Solutions.reverseString("hello");
        Solutions.getSum(200, 100);
        Solutions.addDigits(38);
        System.out.println(Solutions.isAnagram("anagram", "nagaram"));
        System.out.println(Solutions.isAnagram("rat", "car"));
        Solutions.isPalindrome(121);
        Solutions.isPalindrome(12345);
        System.out.println(Solutions.fibonacci(10));
        System.out.println(Solutions.factorial(10));
        System.out.println(Solutions.isUnique("hero"));
        System.out.println(Solutions.isUnique("toto"));
        Solutions.replaceSpace("Mr John Smith    ", 13);
        System.out.println(Solutions.checkBrackets("[({)}]"));
        int[][] matrix = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        Solutions.printMatrix(matrix);
        Solutions.rotateMatrix(matrix, 3);
        Solutions.printMatrix(matrix);
        Solutions.toBinary(125);
        System.out.println(Solutions.isPrime(7));
        System.out.println(Solutions.isPrime(15));
        Solutions.sieveOfEratosthenes(10);
        Solutions.findFactors(72);
        Solutions.primeFactorization(12);
        System.out.println(Solutions.GCD(105, 350));

        int check = 3;
        //System.out.println(1 << check);

        char[] cnt = String.valueOf(check).toCharArray();
        //System.out.println(cnt);

        //Solutions.printMatrix(matrix);
        //Solutions.rotateMatrix(matrix, 3);
        //Solutions.printMatrix(matrix);

        Node node = partitionLL(new Node(10, new Node(1, new Node(5, new Node(20, new Node(15, null))))), 10);

        System.out.println();

        System.out.println(LCS("abcdf", "acdefb"));

        System.out.println(shortestUniqueSubString("aabbabbaab"));
        System.out.println(shortestUniqueSubString("aaaa"));

        System.out.println(sumCount(7777));

        System.out.println(primeSum(12));

        TreeNode node2 = new TreeNode(2, null, null);
        TreeNode node3 = new TreeNode(3, null, null);
        TreeNode node4 = new TreeNode(4, node2, node3);
        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node5 = new TreeNode(5, null, node6);
        TreeNode node1 = new TreeNode(1, node4, node5);

        printBSTInLevelOrder(node1);

        System.out.println(compressString1("baab"));

        System.out.println(isPangram("CSeBBZvYvDyayyBzdfdXvaBtxxwiXcD jQADzTCXzBxzwcyxbd cdyxcBbcxsVD wzXXzazCeqYyB CZzXyxAb WX zbtAdxRVyWEAB DbdAd ViYxzCSAuCVZVXyZY gfWzczBaAZWzXwy AXyXRvyrRZHxtedwcbAWeYiA ZwBea tQZaTXCoWbE cbtZvWZAziwA BWzBaVGbz yyECVAdAcSizBWgGTBz i BEJDELZ WBbaAEOGwbBcZZAvrYDmCWhT WycaXTatCwzavAwewZAZZWb ezBywzdYBhbZeVZBEZFuiPBafxyYzAdDxBb BcidxCV c oDZxgfbycygBdx y dCY ZEEZ txzasvxZADzcDzxhZzXBbDdaobzDYIwyAcXDdthWW U cjfzVWaCecAzaZzaAAUFXgcCYAZzD zABU CcaVZCZ Z xayFyXYAYyGavzVyZyzBe AxYzaACI hxXAaB UayBY y fCz ByXW AwxZzchzAwSwVBzuCW WaeddADAZycwa vZTjd czdWyzaybBeTCXYYZcBZtGy aSZrBAcZwcY BAcAeDFaA aXBhFwAyC IeW Y CaA AbWtSFbzVZIxTACZAaBYIBxbBAzCdAaWaWaAxAcvZaZZTzBBZZsug YtaYtmdxBzFDDzWWHhacAZDuzVyDBADAYaaxAqraXUzzJAAEAZSCWAbYZZyUfAyWYBKEZca bdbQazBEBXD XAdLwWHwwxFaFXxVZZyzcykCwcwaScqyDvB wAxdbDAZXFeEcwWbvbhFHxbYWAYab bZFzBIdax XvC ZcD DZxzabAcYavbdyFRBBbZWuu vzQAvZAVWAYSYbbCZfZWBXWeAZsXBcbaZfEbgEAIZvyCBwEydWa JByZedADvCZyftcUexYbYca BvZBCcybXbz zBUXdye zy YDaYEcAzdeaqEy ZDVSd dacbS szuzZSH ADycXCUDVb"));

        rotateArray();

        int[] array = new int[4];
        array[0] = 2;
        array[1] = 7;
        array[2] = 11;
        array[3] = 15;

        int[] result = twoSum(array,9);

        int[][] matrix1 = {
                { 11, 2, 4 },
                { 4, 5, 6 },
                { 10, 8, -12 }
        };

        diagnalDifference(matrix1);

        printPyramid(6);

        convertTime("12:00:00AM");

        Node ll1 = new Node(1, new Node(3, new Node(5, new Node(6, null))));
        Node ll2 = new Node(2, new Node(4, new Node(7, null)));

        MergeLists(ll1, ll2);

        Vector<String> list1 = new Vector<>();
        list1.add("quick");
        list1.add("lazy");

        Vector<String> list2 = new Vector<>();
        list2.add("brown");
        list2.add("black");
        list2.add("grey");

        Vector<String> list3 = new Vector<>();
        list3.add("fox");
        list3.add("dog");

        Vector<String> list4 = new Vector<>();
        list4.add("talks");
        list4.add("walks");

        Vector<String> list5 = new Vector<>();
        list5.add("yesterday");
        list5.add("today");
        list5.add("tomorrow");

        Vector<Vector<String>> bigList = new Vector<>();
        bigList.add(list1);
        bigList.add(list2);
        bigList.add(list3);
        //bigList.add(list4);
        //bigList.add(list5);


        Solutions solutions = new Solutions();

        solutions.printCombinations(bigList);

        HashSet<HashSet<String>> temp = solutions.cartesianProductRecursive1(0, bigList);
        Vector<Vector<String>> temp1 = solutions.cartesianProductRecursive2(bigList);

        int charge = solutions.calculatePhoneBill("00:05:00,400-234-090\n" +
                                   "00:07:20,701-080-080\n" +
                                    "00:00:00,400-234-091\n");

        System.out.println();

        int[] array1 = new int[7];
        array1[0] = -1;
        array1[1] = 2;
        array1[2] = -3;
        array1[3] = -2;
        array1[4] = 1;
        array1[5] = -9;
        array1[6] = 4;
        int[] result1 = partitionArrayPosNeg(array1);
        //int[] result2 = partitionArray2(array1);

        int[] array2 = new int[10];
        array2[0] = 1;
        array2[1] = 2;
        array2[2] = 2;
        array2[3] = 0;
        array2[4] = 1;
        array2[5] = 0;
        array2[6] = 2;
        array2[7] = 1;
        array2[8] = 0;
        array2[9] = 0;
        int[] result3 = dutchFlagPartition(array2);

        int max = mehdi(array1);

        ceaserCipher("middle-Outz", 2);
        ceaserCipher("159357lcfd", 98);

        sos("SSSSSSSSSSSSSSSOOOOOOOOOOOOOOOOOOOOOOOOOSSSSSSSO");

        deleteAllGreater(ll1, 3);

        System.out.println("Longest length of unique substring is: " + longestUniqueSubString("abcabcbb"));
        System.out.println("Longest length of unique substring is: " + longestUniqueSubString("bbbbb"));

        //System.out.println("Longest palindromic substring is: " + longestPalindromicSubString("abcddcba"));
        //System.out.println("Longest palindromic substring is:" + longestPalindromicSubString("abdcdeba"));

        System.out.println("String to int: " + stringToInteger("526787"));
    }
}
