package com.courses.dao.impl;

import com.courses.dao.AbstractDao;
import com.courses.dao.RentRequestDao;
import com.courses.model.AppUser;
import com.courses.model.RentRequest;
import com.courses.model.UserRole;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Stepan.Kachan
 */

@Repository("rentRequestDao")
public class RentRequestDaoImpl extends AbstractDao<Integer, RentRequest> implements RentRequestDao {

    @Override
    public void addRequest(RentRequest rentRequest) {
        persist(rentRequest);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RentRequest> getUsersRequests(AppUser user) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("user", user));
        return (List<RentRequest>) crit.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RentRequest> getAllRequests() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        return (List<RentRequest>) criteria.list();
    }

    @Override
    public RentRequest findRequestById(String id) {
        return getByKey(Integer.valueOf(id));
    }

    @Override
    public void updateRentRequest(RentRequest rentRequest) {
        update(rentRequest);
    }
}
