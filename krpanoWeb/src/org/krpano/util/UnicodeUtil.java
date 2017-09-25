package org.krpano.util;

public class UnicodeUtil {
	/**
	 * 字符转Unicode编码,返回4个字符，如汉字“华”返回534e
	 * */
	public static String gbEncoding(final String gbString) {
		char[] utfBytes = gbString.toCharArray();
		String unicodeBytes = "";
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
			// unicodeBytes = unicodeBytes + "\\u" + hexB;
			unicodeBytes = unicodeBytes + hexB;
		}
		// System.out.println("unicodeBytes is: " + unicodeBytes);
		return unicodeBytes;
	}

	/**
	 * Unicode编码到字符
	 * */
	public static String decodeUnicode(final String dataStr) {
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			end = dataStr.indexOf("\\u", start + 2);
			String charStr = "";
			if (end == -1) {
				charStr = dataStr.substring(start + 2, dataStr.length());
			} else {
				charStr = dataStr.substring(start + 2, end);
			}
			char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
			buffer.append(new Character(letter).toString());
			start = end;
		}
		return buffer.toString();
	}
}
