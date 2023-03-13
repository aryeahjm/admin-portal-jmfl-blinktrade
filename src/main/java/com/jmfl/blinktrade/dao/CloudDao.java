package com.jmfl.blinktrade.dao;

import com.jmfl.blinktrade.model.User;

public interface CloudDao {

    public Long getUserType(User user , String kind);
}
