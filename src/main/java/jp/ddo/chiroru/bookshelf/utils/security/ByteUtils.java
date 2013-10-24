package jp.ddo.chiroru.bookshelf.utils.security;

public class ByteUtils {

    public static int toInt(byte[] source) {
        if (source.length > 4)
            throw new IllegalArgumentException("要素数[4]を超えるのbyte列をint型に変換することはできません.要素数[4]以内のbyte列を指定してください.");

        int dist = 0;
        for (int counter = 0; counter < 4; counter++) {
            dist = dist << 8;
            dist = dist | (source[counter] & 0xFF);
        }
        return dist;
    }
}
