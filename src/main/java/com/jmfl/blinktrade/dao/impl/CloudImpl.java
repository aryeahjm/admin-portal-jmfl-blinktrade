package com.jmfl.blinktrade.dao.impl;

import com.google.cloud.datastore.*;
import com.jmfl.blinktrade.dao.CloudDao;
import com.jmfl.blinktrade.model.User;
import com.jmfl.blinktrade.utils.Utils;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.jmfl.blinktrade.constants.Values.*;

@Component
public class CloudImpl implements CloudDao {

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

    private String getTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        return formatter.format(date);
    }

    @Override
    public Long getUserType(User user , String kind) {
        /*
            User.pass is not yet encrypted
         */
        try {
            user.setPassword(Utils.encrypt(user.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            // we will just keep the password as it is
        }
        KeyFactory factory = datastore.newKeyFactory().setKind(kind);
        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind(kind)
                .setFilter(StructuredQuery.CompositeFilter.and(
                        StructuredQuery.PropertyFilter.eq(EMP_ID_PROP_NAME, user.getName()),
                        StructuredQuery.PropertyFilter.eq(EMP_PASS_PROP_NAME, user.getPassword())))
                .build();

        QueryResults<Entity> users = datastore.run(query);
        Long l = null;
        while (users.hasNext()) {
            Entity en = users.next();
            l = en.getLong(EMP_TYPE_PROP_NAME);
            System.out.println("user type is "+l);
        }
        return l;
    }


    @Override
    public String updateUserSessionInfo(String emp_id,String emp_type , String kind) {
        KeyFactory factory = datastore.newKeyFactory().setKind(kind);
        System.out.println("emp_id "+emp_id);
        String time = getTime();
        FullEntity session = FullEntity.newBuilder(factory.newKey(emp_id))
                .set(EMP_ID_PROP_NAME,emp_id)
                .set(SESSION_TIME_DATE_PROP_NAME,time)
                .set(EMP_TYPE_PROP_NAME,emp_type)
                .build();
        datastore.put(session);
        return time;
    }
}
