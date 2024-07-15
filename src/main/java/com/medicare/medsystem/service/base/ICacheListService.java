package com.medicare.medsystem.service.base;

import java.util.List;

public interface ICacheListService
{
    public void leftPush(String key, Object value);
    public void rightPush(String key, Object value);
    public List<Object> getList(String key);
    public void clearList(String key);
}
