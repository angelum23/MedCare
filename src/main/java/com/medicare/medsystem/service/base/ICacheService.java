package com.medicare.medsystem.service.base;

import java.util.List;

public interface ICacheService<T> {
    public T get(String key);
    public void set(String key, T value);
    public void delete(String key);
}
