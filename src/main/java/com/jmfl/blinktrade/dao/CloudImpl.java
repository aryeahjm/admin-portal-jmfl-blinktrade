package com.jmfl.blinktrade.dao;

import com.google.cloud.datastore.*;
import com.jmfl.blinktrade.model.User;
import org.springframework.stereotype.Component;

import static com.jmfl.blinktrade.constants.Values.*;

@Component
public class CloudImpl implements CloudDao {

    @Override
    public Long getUserType(User user , String kind) {
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        KeyFactory factory = datastore.newKeyFactory().setKind(kind);
        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind("Testing")
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
}
