package jp.ddo.chiroru.bookshelf.utils.security;

import java.io.Serializable;

/**
 * このクラスはリクエスト元のクライアントを表現します.
 * @author chiroru_0130@yahoo.co.jp
 * @since 1.0.0
 */
public class Requester
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private String clientId;

    private String password;

    public Requester(String clientId, String password) {
        this.clientId = clientId;
        this.password = password;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
