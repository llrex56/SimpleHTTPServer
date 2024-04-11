package signature;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author luozhenhong
 * @version 1.0
 * 2023/5/4 10:30
 */
public class RSASignTest {

    private static final String CERTIFICATE_FILE = "C:\\Users\\llrex\\workspace\\SimpleHTTPServer\\src\\test\\java\\signature\\client_public_key.pem";
    private static final String PRIVATE_KEY_FILE = "C:\\Users\\llrex\\workspace\\SimpleHTTPServer\\src\\test\\java\\signature\\client_private_key.pem";

    public static void main(String[] args) throws Exception {
        String data = "hello world";
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);

        // 构造公钥和私钥对象
        PublicKey publicKey = readPublicKeyFromFile();
        PrivateKey privateKey = readPrivateKeyFromFile();

        // 创建SHA512摘要对象
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        byte[] hash = digest.digest(dataBytes);

        // 使用RSA私钥对摘要进行签名
        byte[] signatureBytes = sign(hash, privateKey);

        // 使用RSA公钥对签名进行验证
        boolean valid = verify(hash, signatureBytes, publicKey);

        System.out.println("data: " + data);
        System.out.println("SHA-512 hash: " + bytesToHex(hash));
        System.out.println("signature: " + bytesToHex(signatureBytes));
        System.out.println("verification result: " + valid);
    }

    private static PublicKey readPublicKeyFromFile() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bytes = readFromFile(RSASignTest.CERTIFICATE_FILE);
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(publicKeySpec);
    }

    private static PrivateKey readPrivateKeyFromFile() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bytes = readFromFile(RSASignTest.PRIVATE_KEY_FILE);
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(privateKeySpec);
    }

    private static byte[] sign(byte[] hash, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("SHA512withRSA");
        signature.initSign(privateKey);
        signature.update(hash);
        return signature.sign();
    }

    private static boolean verify(byte[] hash, byte[] signatureBytes, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("SHA512withRSA");
        signature.initVerify(publicKey);
        signature.update(hash);
        return signature.verify(signatureBytes);
    }

    private static byte[] readFromFile(String fileName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(fileName)) {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            return bytes;
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}