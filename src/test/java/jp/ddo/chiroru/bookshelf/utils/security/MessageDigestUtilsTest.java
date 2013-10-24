package jp.ddo.chiroru.bookshelf.utils.security;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import jp.ddo.chiroru.bookshelf.utils.security.MessageDigestUtils.ALGORITHM;

import org.junit.Test;

public class MessageDigestUtilsTest {

    @Test
    public void SHA256のメッセージダイジェストのバイナリ表現文字列を生成する() {
        MessageDigestUtils testee = MessageDigestUtils.getInstance(ALGORITHM.SHA_256);
        assertThat(testee.toBinaryExpression("test"), is("10011111100001101101000010000001100010000100110001111101011001011001101000101111111010101010000011000101010110101101000000010101101000111011111101001111000110111010110000101110000010001011001101000101011101011011000001010110110000111100000000101000001000"));
    }

    @Test
    public void SHA256のメッセージダイジェストの8進表現文字列を生成する() {
        MessageDigestUtils testee = MessageDigestUtils.getInstance(ALGORITHM.SHA_256);
        assertThat(testee.toOctalExpression("test"), is("02370206032002010210011401750145023200570352024003050132032000250243027701170033005300130202005403210135015400250260036000120010"));
    }

    @Test
    public void SHA256のメッセージダイジェストの16進表現文字列を生成する() {
        MessageDigestUtils testee = MessageDigestUtils.getInstance(ALGORITHM.SHA_256);
        assertThat(testee.toHexExpression("test"), is("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08"));
    }

    @Test
    public void SHA512のメッセージダイジェストの16進表現文字列を生成する() {
        MessageDigestUtils testee = MessageDigestUtils.getInstance(ALGORITHM.SHA_512);
        assertThat(testee.toHexExpression("test"), is("ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff"));
    }
}
