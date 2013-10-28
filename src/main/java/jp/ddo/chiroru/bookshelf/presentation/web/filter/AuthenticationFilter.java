package jp.ddo.chiroru.bookshelf.presentation.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ddo.chiroru.bookshelf.utils.security.BasicAuthentication;
import jp.ddo.chiroru.bookshelf.utils.security.UnauthorizedException;

public class AuthenticationFilter
        implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = null;
        if (request instanceof HttpServletRequest)
            httpServletRequest = (HttpServletRequest)request;

        try {
            BasicAuthentication basicAuthentication = new BasicAuthentication(httpServletRequest);
            basicAuthentication.authenticatie();
            chain.doFilter(request, response);
        } catch (UnauthorizedException e) {
            unauthorizaedResponse((HttpServletResponse)response);
            return;
        }
    }

    @Override
    public void destroy() {
        // do nothing.
    }

    private void unauthorizaedResponse(HttpServletResponse response) {
        try {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } catch (IOException e) {
            throw new RuntimeException("HTTP 401 応答の生成に失敗しました.", e);
        }
    }
}
