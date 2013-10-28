package jp.ddo.chiroru.bookshelf.utils.security;

import java.io.IOException;
import java.util.Set;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;
import javax.servlet.http.HttpServletRequest;

import jp.ddo.chiroru.bookshelf.core.BeanManagerProvider;
import sun.misc.BASE64Decoder;

public class BasicAuthentication {

    private static final String AUTHORIZATION_HTTP_HEADER_KEY = "Authorization";

    private Requester requester;

    private BeanManager manager;

    public BasicAuthentication(HttpServletRequest request) {
        String authorizationHttpHeader = getAuthorizationHttpHeader(request);
        requester = decodeBase64Str(authorizationHttpHeader);
        manager = BeanManagerProvider.getInstance().getBeanManager();
    }

    @SuppressWarnings("serial")
    public void authenticatie() {
        Set<Bean<?>> beans =
                manager.getBeans(CoordinationClientRepository.class, new AnnotationLiteral<Default>() {});
        Bean<?> bean = manager.resolve(beans);
        CoordinationClientRepository repository = (CoordinationClientRepository)manager.getReference(  
                bean,
                CoordinationClientRepository.class,  
                manager.createCreationalContext(bean));
        if (!repository.existMatchedClient(requester)) {
            throw new UnauthorizedException();
        }
    }

    private String getAuthorizationHttpHeader(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION_HTTP_HEADER_KEY);
    }

    private Requester decodeBase64Str(String encodedStr) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] decodedBytes = decoder.decodeBuffer(encodedStr.substring(6));
            String decodedStr = new String(decodedBytes);
            String[] identity = decodedStr.split(":");
            return new Requester(identity[0], identity[1]);
        } catch (IOException e) {
            throw new RuntimeException("Authorization ヘッダーのデコードに失敗しました.", e);
        }
    }
}
