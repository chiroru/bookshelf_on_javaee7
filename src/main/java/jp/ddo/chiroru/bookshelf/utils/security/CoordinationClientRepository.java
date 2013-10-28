package jp.ddo.chiroru.bookshelf.utils.security;

import javax.ejb.Singleton;

@Singleton
public class CoordinationClientRepository {

    boolean existMatchedClient(Requester requester) {
        // TODO : DBのクライアント情報との突合せ処理を要実装.
        return true;
    }
}
