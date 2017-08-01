package com.omniwyse.sms.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);
	
	private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
    	LOGGER.info("Authentication success");
    	SavedRequest savedRequest = requestCache.getRequest(request, response);
    	
      if (savedRequest == null) {
          clearAuthenticationAttributes(request);
          return;
      }
      String targetUrlParam = getTargetUrlParameter();
      if (isAlwaysUseDefaultTargetUrl()
        || (targetUrlParam != null
        && StringUtils.hasText(request.getParameter(targetUrlParam)))) {
          requestCache.removeRequest(request, response);
          clearAuthenticationAttributes(request);
          return;
      }

      clearAuthenticationAttributes(request);
  }	
    
    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
    
}
