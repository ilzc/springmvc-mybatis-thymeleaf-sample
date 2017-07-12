package cn.com.senfun.common;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.codec.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by server1 on 2016/11/15.
 */
public class Utils {

    private static RandomString randomStr = new RandomString(10);

    public static String getSHA256Base64String(String sourceString) {
        try {
            return getBase64WithDigestString(sourceString, "SHA-256");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String getMD5HexString(String sourceString) {
        try {
            return getHexWithDigestString(sourceString, "MD5");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    private static String getHexWithDigestString(String sourceString, String algorithmn) throws UnsupportedEncodingException {
        MessageDigest md5Digest = getMessageDigest(algorithmn);
        byte[] result = md5Digest.digest(sourceString.getBytes("UTF-8"));
        return new String(Hex.encode(result));
    }
    private static String getBase64WithDigestString(String sourceString, String algorithmn) throws UnsupportedEncodingException {
        MessageDigest md5Digest = getMessageDigest(algorithmn);
        byte[] result = md5Digest.digest(sourceString.getBytes("UTF-8"));
        return new String(Base64.encode(result));
    }

    protected static final MessageDigest getMessageDigest(String algorithm) throws IllegalArgumentException {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException var2) {
            throw new IllegalArgumentException("No such algorithm [" + algorithm + "]");
        }
    }

    public static String generatePasswordSalt() {
        return randomStr.nextString();
    }




}
