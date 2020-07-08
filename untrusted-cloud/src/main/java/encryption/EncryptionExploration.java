package encryption;

public class EncryptionExploration {
	public static void main(String[] args) {
		FileCryptoTool encryptoTool = new FileCryptoTool("src/main/resources/stockFiles/SampleFile.txt");
		encryptoTool.encryptFile("src/main/resources/stockFiles/SampleFile.enc");
		
		FileCryptoTool decryptoTool = new FileCryptoTool("src/main/resources/stockFiles/SampleFile.enc");
		decryptoTool.decryptFile("src/main/resources/stockFiles/SampleFileDecrypted.txt");
	}
}
