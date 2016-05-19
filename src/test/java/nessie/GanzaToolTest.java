package nessie;

import java.util.Stack;

public class GanzaToolTest {

	public void testIsBalanced() {
		System.err.println(new GanzaTool().isBalanced(")("));
	}

	public static void main(String[] args) {
		// new GanzaToolTest().testIsBalanced();

		System.err.println(new GanzaToolTest().isBalanced3(")("));
		System.err.println(new GanzaToolTest().isBalanced3("(())"));
		System.err.println(new GanzaToolTest().isBalanced3("A(HJ)(dd)()"));
		System.err.println(new GanzaToolTest().isBalanced3("(()"));
		System.err.println(new GanzaToolTest().isBalanced3("())("));
	}

	public static boolean isBalanced3(final String in) {
		final Stack<Character> charStack = new Stack<Character>();
		for (final char chr : in.toCharArray()) {
			switch (chr) {
			case '(':
				charStack.push(chr);
				break;
			case ')':
				if (charStack.isEmpty() || charStack.pop() != '(')
					return false;
				break;
			}
		}
		return charStack.isEmpty();
	}

	private static boolean isBalanced2(String s) {
		int left = 0;
		int right = 0;
		for (int i = 0; i < s.length(); i++) {
			final char c = s.charAt(i);
			if (c == '(' && i == 0) {
				left++;
				System.out.println(c + " " + left);
			} else {
				return false;
			}
			if (c == '(' && i > 0) {
				left++;
				System.out.println(c + " " + left);
			}
			if (c == ')') {
				right++;
				System.out.println(c + " " + right);
			}
		}
		return 0 == (left - right);
	}

	private static boolean isBalanced(String s) {
		char[] chars = new char[s.length()];
		int size = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				chars[size++] = c;
			}
			if (c == ')' && (size == 0 || chars[--size] != '(')) {
				return false;
			}
			if (c == ']' && (size == 0 || chars[--size] != '[')) {
				return false;
			}
		}

		return true;
	}
}
