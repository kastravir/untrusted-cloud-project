package algorithms;

import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;


import iaik.security.md.Groestl512;
import iaik.security.md.JH512;
import iaik.security.md.KECCAK512;
import iaik.security.md.Skein512;
import iaik.security.md.Md5;
import iaik.security.md.Whirlpool;
import iaik.security.md.BLAKE512;
import iaik.security.md.SHA3_512;
import iaik.security.md.SHA512_256;


public class LogisticRegressionCreator {
	
	public static int megaByteToByteConversion = 1000000;
	
	// Proposed
	public static final MessageDigest whirlpoolDigest = new Whirlpool();
	public static final MessageDigest sha512Digest = new SHA512_256();
	public static final MessageDigest md5Digest = new Md5();
	public static final MessageDigest sha3Digest = new SHA3_512();
	
	// Finalists
	public static final MessageDigest groestDigest = new Groestl512();
	public static final MessageDigest jh512Digest = new JH512();
	public static final MessageDigest keccakDigest = new KECCAK512();
	public static final MessageDigest skeinDigest = new Skein512();
	public static final MessageDigest blake512Digest = new BLAKE512();
	
	public static void main(String args[]) throws Exception {

		HashMap<String, MessageDigest> finalistsMap = new HashMap<String, MessageDigest>();
		finalistsMap.put("Groestl", groestDigest);
		finalistsMap.put("KECCAK", keccakDigest);
		finalistsMap.put("Skein-512", skeinDigest);
		finalistsMap.put("JH-512", jh512Digest);
		finalistsMap.put("BLAKE-512", blake512Digest);
		finalistsMap.put("Whirlpool", whirlpoolDigest);
		finalistsMap.put("SHA3-512", sha3Digest);
		finalistsMap.put("MD5", md5Digest);
		finalistsMap.put("SHA-512", sha512Digest);

		FileWriter fileWriter = new FileWriter("E:\\finalist-results.csv");
		fileWriter.write("Algorithm,File-Size,Duration\n");

		for (Map.Entry<String,MessageDigest> entry : finalistsMap.entrySet()) {
			String name = entry.getKey();
			MessageDigest loopDigest = entry.getValue();
			System.out.println(name);
			
			// Create files ranging from 1 MB to 100MB in 1MB increments
			for(int i = 1; i < 100; i+= 1) {
				File file = new File("E:\\tmp.txt");
				RandomAccessFile f = new RandomAccessFile(file, "rw");
				
				int sizeInBytes = i * megaByteToByteConversion;
				byte[] tempArray = new byte[sizeInBytes];
				f.setLength(sizeInBytes);
				f.read(tempArray, 0, sizeInBytes);
				long startTime = System.nanoTime();
				loopDigest.digest(tempArray);
				long duration = (System.nanoTime() - startTime)/1000000;
				
				fileWriter.write(name + "," + String.valueOf(i) + ", " + String.valueOf(duration)+"\n");
				f.close();
				file.delete();
			}
		}
		fileWriter.close();
	}
}
