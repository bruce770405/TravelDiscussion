package javaserver.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.commons.codec.binary.Base64;
//import org.junit.Test;

public class Util {

	public static String path = "/Users/BruceHsu/Desktop/作品快照"+File.separator;

	public static String bytesToBase64(byte[] bytes) {
		return Base64.encodeBase64String(bytes);
	}

	public static String imageToBase64(String username) throws IOException {
		// nio改寫
		String url = path + username +File.separator +"user.jpg";
//		System.out.println(url);
		FileInputStream fs = new FileInputStream(url);
		FileChannel channel = fs.getChannel();
		int size = (int) channel.size();
		ByteBuffer buffer = ByteBuffer.allocate(size);
		while (channel.read(buffer) > 0) {
		}
		channel.close();
		String base = Base64.encodeBase64String(buffer.array());
		System.out.println(base);
		return base;
	}

	public static boolean base64ToImageFile(String username, String base64) {
		try {

			byte[] bytes = Base64.decodeBase64(base64);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 異常調整
					bytes[i] += 256;
				}
			}
			Path pathTo = Paths.get(path + username  +File.separator+ "user.jpg");
			WritableByteChannel channelTo = Files.newByteChannel(pathTo,
					new OpenOption[] { StandardOpenOption.CREATE, StandardOpenOption.WRITE });

			ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
			buffer.put(bytes);
//			buffer.flip();
			channelTo.write(buffer);
			buffer.flip();
			channelTo.close();

			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	
}