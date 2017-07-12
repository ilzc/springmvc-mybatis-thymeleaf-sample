package cn.com.senfun.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;

import java.security.MessageDigest;

public class WebsitePasswordEncoder extends MessageDigestPasswordEncoder {
    private final Logger logger = LoggerFactory.getLogger(WebsitePasswordEncoder.class);

    public WebsitePasswordEncoder() {
        super("MD5");
    }
    public final String DEFAULT_SALT = "LSda2015";
    @Override
    public String encodePassword(String rawPass, Object salt) {
        String md5RawPass = Utils.getMD5HexString(rawPass);
        String saltedRawPass = Utils.getMD5HexString(md5RawPass + DEFAULT_SALT);
        String result = Utils.getSHA256Base64String(saltedRawPass + salt);
        return result;

    }

    @Override
    protected String mergePasswordAndSalt(String password, Object salt, boolean strict) {
        if (password == null) {
            password = "";
        }

        MessageDigest messageDigest = getMessageDigest();
        byte[] digest = messageDigest.digest(Utf8.encode(password));
        password = new String(Hex.encode(digest));

        if ((salt == null) || "".equals(salt)) {
            return password;
        }
        else {
            return password + salt.toString();
        }
    }
}
