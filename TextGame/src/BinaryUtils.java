import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BinaryUtils {
	
	final static String FILE_NAME = "C:\\Users\\DEV\\Desktop\\binary\\binary1.kkk";
	final static String OUTPUT_FILE_NAME = "C:\\Users\\DEV\\Desktop\\binary\\binary2.kkk";
	
	public static void startTest() throws IOException {
		byte[] bytes = readSmallBinaryFile(FILE_NAME);
		log("Small - size of file read in:" + bytes.length);
		writeSmallBinaryFile(bytes, OUTPUT_FILE_NAME);
	}

	public static byte[] readSmallBinaryFile(String fileName) throws IOException {
		Path path = Paths.get(fileName);
		return Files.readAllBytes(path);
	}

	public static void writeSmallBinaryFile(byte[] bytes, String fileName) throws IOException {
		Path path = Paths.get(fileName);
		Files.write(path, bytes);
	}

	public static void log(Object msg){
		System.out.println(String.valueOf(msg));
	}
}
