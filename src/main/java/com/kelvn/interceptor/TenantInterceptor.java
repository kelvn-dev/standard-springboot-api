package com.kelvn.interceptor;

import com.kelvn.utils.TenantContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TenantInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String tenantID = request.getHeader("X-Tenant-Id");
    if (tenantID == null) {
      response.getWriter().write("X-TenantID not present in the Request Header");
      response.setStatus(400);
      return false;
    }
    TenantContext.setCurrentTenant(tenantID);
    return true;
  }

  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView)
      throws Exception {
    TenantContext.clear();
  }
}
