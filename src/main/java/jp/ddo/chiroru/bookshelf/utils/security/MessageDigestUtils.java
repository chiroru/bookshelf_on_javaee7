package jp.ddo.chiroru.bookshelf.utils.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * メッセージダイジェストを扱うためのユーティリティです.
 * @author chiroru_0130@yahoo.co.jp
 * @see {@link ALGORITHM}
 * @since 1.0.0
 */
public class MessageDigestUtils {

    private final static ALGORITHM DEFAULT_ALGORITHM = ALGORITHM.SHA_256;

    private MessageDigest messageDigest;

    private ALGORITHM algorithm;

    /*
     * コンストラクタによるインスタンスの生成を抑止.
     */
    private MessageDigestUtils(ALGORITHM algorithm) {
        this.algorithm = algorithm;
        try {
            this.messageDigest = MessageDigest.getInstance(algorithm.get());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("サポートしていないアルゴリズムが指定されました.", e);
        }
    }

    public static MessageDigestUtils getInstance() {
        return getInstance(DEFAULT_ALGORITHM);
    }

    public static MessageDigestUtils getInstance(ALGORITHM algorithm) {
        return new MessageDigestUtils(algorithm);
    }

    public String toBinaryExpression(String source) {
        byte[] digest = computeDigest(source);
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < digest.length; i += 4) {
            byte[] part = Arrays.copyOfRange(digest, i, i + 4);
            buffer.append(Integer.toBinaryString(ByteUtils.toInt(part)));
        }
        return buffer.toString();
    }

    public String toOctalExpression(String source) {
        byte[] digest = computeDigest(source);
        StringBuilder buffer = new StringBuilder();
        for (byte b : digest) {
            buffer.append(String.format("%04o", b));
        }
        return buffer.toString();
    }

    public String toHexExpression(String source) {
        byte[] digest = computeDigest(source);
        StringBuilder buffer = new StringBuilder();
        for (byte b : digest) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }

    public ALGORITHM getAlgorithm() {
        return algorithm;
    }

    private byte[] computeDigest(String str) {
        return messageDigest.digest(str.getBytes());
    }

    /**
     * メッセージダイジェストのアルゴリズムを表現する列挙方です.
     *　@see {@link MessageDigestUtils}
     * @since 1.0.0
     */
    public enum ALGORITHM {
        MD2("MD2"),
        MD5("MD5"),
        SHA("SHA"),
        SHA_256("SHA-256"),
        SHA_384("SHA-384"),
        SHA_512("SHA-512");

        private String algorithm;

        ALGORITHM(String algorithm) {
            this.algorithm = algorithm;
        }

        public String get() {
            return algorithm;
        }
    }
}
