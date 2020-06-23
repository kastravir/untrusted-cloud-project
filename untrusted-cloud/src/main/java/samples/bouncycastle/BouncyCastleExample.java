package samples.bouncycastle;

import org.bouncycastle.jcajce.provider.digest.Whirlpool;
import org.bouncycastle.jcajce.provider.digest.MD5;
import org.bouncycastle.jcajce.provider.digest.SHA512;
import org.bouncycastle.jcajce.provider.digest.Blake2b;
import org.bouncycastle.jcajce.provider.digest.Blake2b.Blake2b512;



public class BouncyCastleExample {

	public static void main(String[] args) {
		byte[] secret = "Im a little teapot".getBytes();
		
		Whirlpool.Digest whirlpoolDigest = new Whirlpool.Digest();
		MD5.Digest md5Digest = new MD5.Digest();
		SHA512.Digest sha512Digest = new SHA512.Digest();
		Blake2b512 blake512Digest = new Blake2b512();
		

		byte[] blake512HashedString = blake512Digest.digest(secret);
		byte[] sha512HashedString = sha512Digest.digest(secret);
		byte[] whirlpoolHashedString = whirlpoolDigest.digest(secret);
		byte[] md5HashedString = md5Digest.digest(secret);
		

		System.out.println("BLAKE512 hash algorithm: " + blake512HashedString.toString());
		System.out.println("SHA512 hash algorithm: " + sha512HashedString.toString());
		System.out.println("Whirlpool hash algorithm: " + whirlpoolHashedString.toString());
		System.out.println("MD5 hash algorithm: " + md5HashedString.toString());

	}
}
