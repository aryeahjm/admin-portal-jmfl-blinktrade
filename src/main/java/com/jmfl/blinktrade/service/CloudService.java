package com.jmfl.blinktrade.service;

import com.jmfl.blinktrade.dao.CloudDao;
import com.jmfl.blinktrade.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.jmfl.blinktrade.constants.Values.DB_KIND_SESSION_STR;

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
    public void updateSession(User user , String kind){
        cloudDao.updateUserSessionInfo(user.getName().trim(),user.getPassword().trim(),DB_KIND_SESSION_STR);
    }


}
