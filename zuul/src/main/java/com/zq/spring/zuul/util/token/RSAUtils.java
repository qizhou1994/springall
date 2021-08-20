package com.zq.spring.zuul.util.token;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


/**
 * RSA公钥/私钥/签名工具包
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 */
public class RSAUtils {

    public static final String RAS_PUB_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCA28eLNejumAlqvXlf4Q32bS76VBWhLqk07X/NgGdKV2NkIXBeIJozhiZkOJWGO57gNRdu5DqQD30jc4glhOAIjwPXFej7T/yMadGLFpiCrG9Og0tKMuHSuG7zEpqh2Qihztro9xdXP/yWk2JUj/fWUoCgIWuCwGWNx487T/sSBQIDAQAB";
    public static final String RAS_PRI_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIDbx4s16O6YCWq9eV/hDfZtLvpUFaEuqTTtf82AZ0pXY2QhcF4gmjOGJmQ4lYY7nuA1F27kOpAPfSNziCWE4AiPA9cV6PtP/Ixp0YsWmIKsb06DS0oy4dK4bvMSmqHZCKHO2uj3F1c//JaTYlSP99ZSgKAha4LAZY3HjztP+xIFAgMBAAECgYBx6FZblBtFlrZ0WevKOrrKBLhLZzr7kbDX7b5VHdWw3NEqeXjIbE+DjmmvnGlpEJkgHy6Iw02VfWukhANtDymufSxw8AIubF5MSP8FthQ787QLyU1Q600X4BJRHURB9JfOBgiM97/qsr2ynbx8Yo/L+qKnYNcv5ENIPInm7XE4XQJBAOWcyB1kEyQMZyamSex7kwqazSwmFyWhVbfS5hpAroomGZ38lv7LyRCWSD8pKNNW9lHvldbf+7Fr1mld/mCI/68CQQCPqs77r961Vgm8WxqJ2UPSPllTZKmjlS885nEl+9KPc1GhqIQEWtV/s9fBOm4AneTvq5olbsC9pRZ3w430TSKLAkEAxbEJMxT4ze7H2SUPPMbgwR6rTDm3cDTKQq0YZL4QCO3o3Hef4dy/TsK1jXv4pI2ZIs6vKgRLBmUchDfjTmZmDQJAcgVBIrWQzmBLd8biSBc74WeEY1AX5nEnPXEyyc+TbDA80E07AW3J1gE4se2akji+Eo0h2KWOqLSWIVT9m6+AQwJBAJRZJL2rcE4Lvyhe69fITlwQzODxJKMXtTcAC3R+nDs56DKumVTzqa422j/QMilC3OaInlMDBS/mhnE4xXU94jU=";

    /**
     * 加密算法RSA
     */
    private static final String KEY_ALGORITHM = "RSA";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;


    /**
     * 私钥解密
     * 微信
     * @param encryptedStr 已加密数据
     * @param privateKey   私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKeyForLongStr(String encryptedStr, String privateKey) throws Exception {
        byte[] encryptedData = ByteFormat.hexToBytes(encryptedStr);
        byte[] keyBytes = Base64.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData);
    }
    private static Map<Integer, String> keyMap = new HashMap<Integer, String>();  //用于封装随机产生的公钥与私钥
    public static void main(String[] args) throws Exception {
//        System.out.println("a= "+ decrypt("746AA3CD1F1D8C36B8E11353A1857C9F57AE7A21449F9C4CE6B826D2F5122A9D",RAS_PRI_KEY));
        //生成公钥和私钥
//        genKeyPair();
//        //加密字符串
        String message = "123456";
        System.out.println("随机生成的公钥为:" + RAS_PUB_KEY);
        System.out.println("随机生成的私钥为:" + RAS_PRI_KEY );
        String messageEn = encrypt(message, RAS_PUB_KEY);
        System.out.println(message + "\t加密后的字符串为:" + messageEn);
        String messageDe = decrypt(messageEn,RAS_PRI_KEY);
         System.out.println("还原后的字符串为:" + messageDe);
    }

    /**
     * 随机生成密钥对
     * @throws NoSuchAlgorithmException
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encode(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encode(privateKey.getEncoded()));
        // 将公钥和私钥保存到Map
        keyMap.put(0,publicKeyString);  //0表示公钥
        keyMap.put(1,privateKeyString);  //1表示私钥
    }
    /**
     * RSA公钥加密
     *
     * @param str
     *            加密字符串
     * @param publicKey
     *            公钥
     * @return 密文
     * @throws Exception
     *             加密过程中的异常信息
     */
    public static String encrypt( String str, String publicKey ) throws Exception{
        //base64编码的公钥
        byte[] decoded = Base64.decode(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encode(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str
     *            加密字符串
     * @param privateKey
     *            私钥
     * @return 铭文
     * @throws Exception
     *             解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decode(str);
        //base64编码的私钥
        byte[] decoded = Base64.decode(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

}

