package algorithms;

import org.bouncycastle.jcajce.provider.digest.Whirlpool;
import org.bouncycastle.jcajce.provider.digest.MD5;
import org.bouncycastle.jcajce.provider.digest.SHA512;
import org.bouncycastle.jcajce.provider.digest.Blake2b.Blake2b512;

import com.amazonaws.util.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.bouncycastle.jcajce.provider.digest.BCMessageDigest;
import shared.Constants;

public class Evaluator {

	
	public static final BCMessageDigest whirlpoolDigest = new Whirlpool.Digest();
	public static final BCMessageDigest md5Digest = new MD5.Digest();
	public static final BCMessageDigest sha512Digest = new SHA512.Digest();
	public static final BCMessageDigest blake512Digest = new Blake2b512();
	
	public static void main(String[] args) {
		evaluateMd5();
		evaluateBlake512();
		evaluateSHA512();
		evaluateWhirlpool();
	}
	
	public static void evaluateWhirlpool() {
		long[] tenKbResults = runTests(whirlpoolDigest, Constants.TEN_KB_FILE);
		long[] fiveKbResults = runTests(whirlpoolDigest, Constants.FIVE_KB_FILE);
		long[] twoAndHalfKbResults = runTests(whirlpoolDigest, Constants.TWO_AND_HALF_KB_FILE);
		long[] oneKbResults = runTests(whirlpoolDigest, Constants.ONE_KB_FILE);
		
		System.out.println("WHIRLPOOL RESULTS");
		/*System.out.println("Ten KB: " + Arrays.toString(tenKbResults));
		System.out.println("Five KB: " + Arrays.toString(fiveKbResults));
		System.out.println("Two and half KB: " + Arrays.toString(twoAndHalfKbResults));
		System.out.println("One KB: " + Arrays.toString(oneKbResults));*/
		
		System.out.println("Ten KB: " + Arrays.stream(tenKbResults).average().toString());
		System.out.println("Five KB: " + Arrays.stream(fiveKbResults).average().toString());
		System.out.println("Two and half KB: " + Arrays.stream(twoAndHalfKbResults).average().toString());
		System.out.println("One KB: " + Arrays.stream(oneKbResults).average().toString());
		System.out.println("");

	}
	
	public static void evaluateMd5() {
		long[] tenKbResults = runTests(md5Digest, Constants.TEN_KB_FILE);
		long[] fiveKbResults = runTests(md5Digest, Constants.FIVE_KB_FILE);
		long[] twoAndHalfKbResults = runTests(md5Digest, Constants.TWO_AND_HALF_KB_FILE);
		long[] oneKbResults = runTests(md5Digest, Constants.ONE_KB_FILE);
		
		System.out.println("MD5 RESULTS");
		/*System.out.println("Ten KB: " + Arrays.toString(tenKbResults));
		System.out.println("Five KB: " + Arrays.toString(fiveKbResults));
		System.out.println("Two and half KB: " + Arrays.toString(twoAndHalfKbResults));
		System.out.println("One KB: " + Arrays.toString(oneKbResults));*/
		
		
		System.out.println("Ten KB: " + Arrays.stream(tenKbResults).average().toString());
		System.out.println("Five KB: " + Arrays.stream(fiveKbResults).average().toString());
		System.out.println("Two and half KB: " + Arrays.stream(twoAndHalfKbResults).average().toString());
		System.out.println("One KB: " + Arrays.stream(oneKbResults).average().toString());
		System.out.println("");

	}
	
	public static void evaluateSHA512() {
		long[] tenKbResults = runTests(sha512Digest, Constants.TEN_KB_FILE);
		long[] fiveKbResults = runTests(sha512Digest, Constants.FIVE_KB_FILE);
		long[] twoAndHalfKbResults = runTests(sha512Digest, Constants.TWO_AND_HALF_KB_FILE);
		long[] oneKbResults = runTests(sha512Digest, Constants.ONE_KB_FILE);
		
		System.out.println("SHA512 RESULTS");
		/*System.out.println("Ten KB: " + Arrays.toString(tenKbResults));
		System.out.println("Five KB: " + Arrays.toString(fiveKbResults));
		System.out.println("Two and half KB: " + Arrays.toString(twoAndHalfKbResults));
		System.out.println("One KB: " + Arrays.toString(oneKbResults));*/
		
		
		System.out.println("Ten KB: " + Arrays.stream(tenKbResults).average().toString());
		System.out.println("Five KB: " + Arrays.stream(fiveKbResults).average().toString());
		System.out.println("Two and half KB: " + Arrays.stream(twoAndHalfKbResults).average().toString());
		System.out.println("One KB: " + Arrays.stream(oneKbResults).average().toString());
		System.out.println("");

	}
	
	public static void evaluateBlake512() {
		long[] tenKbResults = runTests(blake512Digest, Constants.TEN_KB_FILE);
		long[] fiveKbResults = runTests(blake512Digest, Constants.FIVE_KB_FILE);
		long[] twoAndHalfKbResults = runTests(blake512Digest, Constants.TWO_AND_HALF_KB_FILE);
		long[] oneKbResults = runTests(blake512Digest, Constants.ONE_KB_FILE);
		
		System.out.println("BLAKE-512 RESULTS");
		/* System.out.println("Ten KB: " + Arrays.toString(tenKbResults));
		System.out.println("Five KB: " + Arrays.toString(fiveKbResults));
		System.out.println("Two and half KB: " + Arrays.toString(twoAndHalfKbResults));
		System.out.println("One KB: " + Arrays.toString(oneKbResults));		*/
		
		System.out.println("Ten KB: " + Arrays.stream(tenKbResults).average().toString());
		System.out.println("Five KB: " + Arrays.stream(fiveKbResults).average().toString());
		System.out.println("Two and half KB: " + Arrays.stream(twoAndHalfKbResults).average().toString());
		System.out.println("One KB: " + Arrays.stream(oneKbResults).average().toString());
		System.out.println("");
	}
	
	public static long[] runTests(BCMessageDigest digest, String filePath) {
		int iterations = 100000;
		long[] results = new long[iterations];
		
		for(int i = 0; i < iterations; i++) {
			try {
				results[i] = evaluate(digest, filePath);
			} catch(IOException exception) {}
		}
		return results;
	}
	
	public static long evaluate(BCMessageDigest digest, String filePath) throws IOException {
		InputStream stream = new FileInputStream(new File(filePath));
		
		byte[] fileBytes = IOUtils.toByteArray(stream);
		long startTime = System.nanoTime();
		digest.digest(fileBytes);
		long duration = System.nanoTime() - startTime;
		
		return duration/1000;
	}

}
