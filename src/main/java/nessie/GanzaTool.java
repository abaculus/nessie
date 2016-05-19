package nessie;

public class GanzaTool {

	public boolean isBalanced(final String s) {

		int checksum = 0;

		for (int i = 0; i < s.length(); i++) {

			final char c = s.charAt(i);
			if (c == '(') {
				checksum++;
			} else if (c == ')') {
				checksum--;
			}
		}

		return checksum == 0;
	}
}
