package com.medicare.medsystem.service.base;

import java.util.List;

public interface ICacheService {
    public Object get(String key);
    public void set(String key, Object value);
    public void delete(String key);
}
