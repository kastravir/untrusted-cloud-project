package samples.bouncycastle;


import org.bouncycastle.jcajce.provider.digest.Whirlpool;
import org.bouncycastle.jcajce.provider.digest.MD5;
import org.bouncycastle.jcajce.provider.digest.SHA512;
import org.bouncycastle.jcajce.provider.digest.Blake2b.Blake2b512;
import org.bouncycastle.jcajce.provider.digest.SHA3;

import iaik.security.md.Groestl512;
import iaik.security.md.JH512;
import iaik.security.md.KECCAK512;
import iaik.security.md.Skein512;

public class BouncyCastleExample {

	public static void main(String[] args) {
		byte[] secret = "The quick brown fox jumps over the lazy dog".getBytes();

		Whirlpool.Digest whirlpoolDigest = new Whirlpool.Digest();
		MD5.Digest md5Digest = new MD5.Digest();
		SHA512.Digest sha512Digest = new SHA512.Digest();
		
		/**
		 * The following were the finalists in the SHA-3 competition
		 * 
		 *  KECCAK512 was eventually selected as the basis for SHA-3, hence 
		 *  SHA-3 being explicitly included.
		 */
		
		Blake2b512 blake512Digest = new Blake2b512();
		Groestl512 grostDigest = new Groestl512();
		JH512 jhDigest = new JH512();
		KECCAK512 keckDigest = new KECCAK512();
		Skein512 skeinDigest = new Skein512();
		SHA3.Digest512 sha3Digest = new SHA3.Digest512();
		

		byte[] sha512HashedString = sha512Digest.digest(secret);
		byte[] whirlpoolHashedString = whirlpoolDigest.digest(secret);
		byte[] md5HashedString = md5Digest.digest(secret);

		byte[] blake512HashedString = blake512Digest.digest(secret);
		byte[] grostHashedString = grostDigest.digest(secret);
		byte[] jhHashedString = jhDigest.digest(secret);
		byte[] keckHashedString = keckDigest.digest(secret);
		byte[] skeinHashedString = skeinDigest.digest(secret);
		byte[] sha3HashedString = sha3Digest.digest(secret);
		

		System.out.println("SHA512 hash algorithm: " + sha512HashedString.toString());
		System.out.println("Whirlpool hash algorithm: " + whirlpoolHashedString.toString());
		System.out.println("MD5 hash algorithm: " + md5HashedString.toString());
		
		System.out.println("BLAKE512 hash algorithm: " + blake512HashedString.toString());
		System.out.println("GROST hash algorithm: " + grostHashedString.toString());
		System.out.println("JH hash algorithm: " + jhHashedString.toString());
		System.out.println("KECK hash algorithm: " + keckHashedString.toString());
		System.out.println("Skein hash algorithm: " + skeinHashedString.toString());
		System.out.println("SHA3 hash algorithm: " + sha3HashedString.toString());

	}
}
