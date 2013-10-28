package jp.ddo.chiroru.bookshelf.core;

import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BeanManagerProvider {

    private static BeanManagerProvider INSTANCE = new BeanManagerProvider();

    private BeanManager manager;

    public static BeanManagerProvider getInstance() {
        return INSTANCE;
    }

    private BeanManagerProvider() {

        InitialContext ic;

        try {
            ic = new InitialContext();
            manager = (BeanManager)ic.lookup("java:comp/BeanManager");
        } catch (NamingException e) {
            throw new RuntimeException("BeanManagerのlookupに失敗しました.", e);
        }
    }

    public BeanManager getBeanManager() {
        return manager;
    }
}
