package com.jmfl.blinktrade.dao;

import com.jmfl.blinktrade.model.User;

public interface CloudDao {

    public Long getUserType(User user , String kind);
    public String updateUserSessionInfo(String emp_id,String emp_type , String kind);
}
