package app.services.helpers;

import java.util.Base64;
import java.util.Random;

public class Str {
	public Str(){
	}

	public String random(int length) {
		StringBuilder string = new StringBuilder();
		int len;
		while ((len = string.length()) < length) {
			int size = length - len;
			byte[] bytes = random_bytes(size);

			String encodedString = Base64.getEncoder().encodeToString(bytes);
			encodedString = encodedString.replaceAll("[\\/\\+\\=]", "");
			encodedString = encodedString.substring(0, size);

			string.append(encodedString);
		}

		return string.toString();
	}

	public byte[] random_bytes(int size) {
		byte[] bytes = new byte[size];
		new Random().nextBytes(bytes);
		return bytes;
	}
}
