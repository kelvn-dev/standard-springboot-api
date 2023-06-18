package com.kelvn.config.multitenancy;

import com.kelvn.utils.TenantContext;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {
  private String defaultTenant = "public";

  @Override
  public String resolveCurrentTenantIdentifier() {
    String t = TenantContext.getCurrentTenant();
    System.out.println(t);
    if (t != null) {
      return t;
    } else {
      return defaultTenant;
    }
    //    return Optional.ofNullable(TenantContext.getCurrentTenant())
    //      .orElse(TenantContext.DEFAULT_TENANT_ID);
  }

  @Override
  public boolean validateExistingCurrentSessions() {
    return true;
  }
}
