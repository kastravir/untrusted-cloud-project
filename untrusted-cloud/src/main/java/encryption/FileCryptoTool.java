package encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Quick class to encapsulate the basic logic of encrypting and decrypting a
 * sample file for use in the Untrusted Cloud Hopkins project
 * 
 * @author Joseph Kampman
 *
 */
public class FileCryptoTool {

	private String sourceFileName;

	private String secretKey = "I'mALittleTeapot";
	private SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");

	/**
	 * Instantiate the class with the name of the file to be manipulated
	 * 
	 * @param sourceFileName The full path to the file to be encrypted/decrypted
	 */
	public FileCryptoTool(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	/**
	 * Encrypt the provided file name using AES-256 encryption and write it to the
	 * provided file name
	 * 
	 * @param fileOutputName The full path where the encrypted file should be
	 *                       written to
	 */
	public void encryptFile(String fileOutputName) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			ingestAndWriteFile(cipher, fileOutputName);
		} catch (Exception e) {
			System.out.println("Shit is fucked");
		}
	}

	/**
	 * Decrypt the provided file using the same AES-256 encryption and secret key.
	 * Write the decrypted file to the provided file path
	 * 
	 * @param decryptedFileName The full path and file name where the decrypted file
	 *                          should be written to.
	 */
	public void decryptFile(String decryptedFileName) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			ingestAndWriteFile(cipher, decryptedFileName);
		} catch (Exception e) {
			System.out.println("Shit is fucked");
		}
	}

	/**
	 * Using the provided Cipher, read the file and write it to the provided output
	 * file name. Cipher determines if this action is encryption or decryption
	 * 
	 * @param cipher         The cipher to use when producing the output
	 * @param outputFileName The full path and file name to where the file should be
	 *                       written
	 */
	private void ingestAndWriteFile(Cipher cipher, String outputFileName) {
		try {
			File fileInput = new File(sourceFileName);
			FileInputStream inputStream = new FileInputStream(fileInput);
			byte[] inputBytes = new byte[(int) fileInput.length()];
			inputStream.read(inputBytes);

			byte[] outputBytes = cipher.doFinal(inputBytes);

			File fileEncryptOut = new File(outputFileName);
			FileOutputStream outputStream = new FileOutputStream(fileEncryptOut);
			outputStream.write(outputBytes);

			inputStream.close();
			outputStream.close();
		} catch (Exception e) {

		}
	}
}
