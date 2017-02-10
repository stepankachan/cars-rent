package com.courses.dao.impl;

import com.courses.dao.AbstractDao;
import com.courses.dao.ActivityLogDao;
import com.courses.model.LogActivity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Stepan.Kachan
 */
@Repository("activityLogDao")
public class ActivityLogImpl extends AbstractDao<Integer, LogActivity> implements ActivityLogDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<LogActivity> list() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        return (List<LogActivity>) criteria.list();
    }

    @Override
    public void save(LogActivity log) {
        persist(log);
    }

    @Override
    public LogActivity findById(Integer id) {
        return getByKey(Math.toIntExact(id));
    }
}
