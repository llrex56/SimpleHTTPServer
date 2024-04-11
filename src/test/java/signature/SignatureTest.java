package signature;

import cn.hutool.crypto.PemUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SignUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author luozhenhong
 * @version 1.0
 * 2023/5/4 9:55
 */
public class SignatureTest {

    private static final String CERTIFICATE_FILE = "C:\\Users\\llrex\\workspace\\SimpleHTTPServer\\src\\test\\java\\signature\\client_public_key.pem";
    private static final String PRIVATE_KEY_FILE = "C:\\Users\\llrex\\workspace\\SimpleHTTPServer\\src\\test\\java\\signature\\client_private_key.pem";

    public static void main(String[] args) throws Exception {

        final FileInputStream publicKey = new FileInputStream(CERTIFICATE_FILE);
        final FileInputStream privateKey = new FileInputStream(PRIVATE_KEY_FILE);

        final Sign signature = SignUtil.sign(SignAlgorithm.SHA512withRSA, PemUtil.readPem(publicKey), PemUtil.readPem(privateKey));
        final String sign = signature.signHex("Hello World".getBytes(StandardCharsets.UTF_8));
        System.out.println(sign);
    }

    public static String sign() throws Exception {
        // 读取私钥文件
        FileInputStream keyfis = new FileInputStream(PRIVATE_KEY_FILE);
        byte[] privateBytes = new byte[keyfis.available()];
        keyfis.read(privateBytes);
        keyfis.close();
        // 获取私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 读取公钥文件
        FileInputStream fis = new FileInputStream(CERTIFICATE_FILE);
        byte[] publicBytes = new byte[fis.available()];
        fis.read(publicBytes);
        fis.close();
        // 获取公钥
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        final byte[] pri = privateKey.getEncoded();
        final byte[] pub = publicKey.getEncoded();

        return SecureUtil.sign(SignAlgorithm.SHA512withRSA, pri, pub).signHex("", StandardCharsets.UTF_8);
    }

    private static PrivateKey loadPrivateKey(String filePath) throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(filePath));
        String key = new String(keyBytes, StandardCharsets.UTF_8);
        key = key.replaceAll("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");
        byte[] decoded = Base64.getDecoder().decode(key);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(new PKCS8EncodedKeySpec(decoded));
    }
}
