package algorithms;

import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.jcajce.provider.digest.SHA512;
import org.bouncycastle.jcajce.provider.digest.Blake2b.Blake2b512;


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
		
		
		HashMap<String, MessageDigest> originalDigest = new HashMap<String, MessageDigest>();
		originalDigest.put("Whirlpool", whirlpoolDigest);
		originalDigest.put("MD5", md5Digest);
		originalDigest.put("SHA-512", sha512Digest);
		originalDigest.put("SHA3-512", sha3Digest);
		
		
		HashMap<String, MessageDigest> finalistsMap = new HashMap<String, MessageDigest>();
		finalistsMap.put("Groestl", groestDigest);
		finalistsMap.put("KECCAK", keccakDigest);
		finalistsMap.put("Skein-512", skeinDigest);
		finalistsMap.put("JH-512", jh512Digest);
		finalistsMap.put("BLAKE-512", blake512Digest);
		finalistsMap.put("Whirlpool", whirlpoolDigest);
		
		FileWriter fileWriter = new FileWriter("E:\\average-results.csv");
		fileWriter.write("Algorithm,File-Size,Duration\n");
		for (Map.Entry<String,MessageDigest> entry : originalDigest.entrySet()) {
			String name = entry.getKey();
			MessageDigest loopDigest = entry.getValue();
			System.out.println(name);
			for(int i = 1000000; i < 100000000; i+= 100000) {
				File file = new File("E:\\tmp.txt");
				RandomAccessFile f = new RandomAccessFile(file, "rw");
				byte[] tempArray = new byte[i];
				f.setLength(i);
				f.read(tempArray, 0, i);
				long startTime = System.nanoTime();
				loopDigest.digest(tempArray);
				long duration = (System.nanoTime() - startTime)/1000000; // Convert to milliseconds 
				
				fileWriter.write(name + "," + String.valueOf(i/1000) + ", " + String.valueOf(duration)+"\n");
				f.close();
				file.delete();
			}
		}
		fileWriter.close();
		
		FileWriter fileWriter2 = new FileWriter("E:\\finalist-results.csv");
		fileWriter2.write("Algorithm,File-Size,Duration\n");

		for (Map.Entry<String,MessageDigest> entry : finalistsMap.entrySet()) {
			String name = entry.getKey();
			MessageDigest loopDigest = entry.getValue();
			System.out.println(name);
			for(int i = 1000000; i < 100000000; i+= 100000) {
				File file = new File("E:\\tmp.txt");
				RandomAccessFile f = new RandomAccessFile(file, "rw");
				byte[] tempArray = new byte[i];
				f.setLength(i);
				f.read(tempArray, 0, i);
				long startTime = System.nanoTime();
				loopDigest.digest(tempArray);
				long duration = (System.nanoTime() - startTime)/1000000;
				
				fileWriter2.write(name + "," + String.valueOf(i/1000) + ", " + String.valueOf(duration)+"\n");
				f.close();
				file.delete();
			}
		}
		fileWriter2.close();
	}
}
