package com.kelvn.interceptor;

import java.lang.reflect.Method;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RedisKeyGenerator implements KeyGenerator {
  @Override
  public Object generate(Object target, Method method, Object... params) {
    return target.getClass().getSimpleName()
        + "_"
        + method.getName()
        + "_"
        + StringUtils.arrayToDelimitedString(params, "_");
  }
}
