package com.medicare.medsystem.service.base;

import java.util.List;

public interface ICacheListService<T>
{
    public void setList(String key, List<T> value);
    public void leftPush(String key, T value);
    public void rightPush(String key, T value);
    public List<T> getList(String key);
    public void clearList(String key);
}
