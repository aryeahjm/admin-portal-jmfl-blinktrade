package com.jmfl.blinktrade.service;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.*;
import com.jmfl.blinktrade.dao.CloudDao;
import com.jmfl.blinktrade.dao.CloudImpl;
import com.jmfl.blinktrade.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class CloudService {

    /*
        -1 : user type is not defined
         0 : user type is verifier
         1 : user type is admin
     */

    @Autowired
    CloudDao cloudDao;

    public Long getUserType(User user , String kind){
        return cloudDao.getUserType(user,kind);
    }


}
