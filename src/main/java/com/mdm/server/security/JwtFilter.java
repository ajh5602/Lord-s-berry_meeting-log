package com.mdm.server.security;

import com.mdm.server.common.ResponseMessage;
import com.mdm.server.exception.CertFailedException;
import com.mdm.server.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
/**
 * name : JwtFilter
 * usage : JWT 토큰 필터
 * @author ash
 * @date 2021-06-17
**/
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        String requestURI = ((HttpServletRequest) request).getRequestURI();
        try {
            if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.info("save authInfo on Security context, uri: {}", requestURI);
            } else {
                logger.debug("not have JWT, uri: {}", requestURI);
            }
        } catch (Exception e){
            throw new CertFailedException(ResponseMessage.CERT_FAIL);
        }
        chain.doFilter(request, response);
    }
}
