package com.courses.dao.impl;

import com.courses.dao.AbstractDao;
import com.courses.dao.UserDao;
import com.courses.model.AppUser;

import com.courses.model.UserRole;
import com.courses.model.UserRoleType;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Stepan.Kachan
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, AppUser> implements UserDao {

    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public AppUser findById(int id) {
        AppUser user = getByKey(id);
        if(user!=null){
            Hibernate.initialize(user.getUserRoles());
        }
        return user;
    }

    public AppUser findBySSO(String sso) {
        logger.info("SSO : {}", sso);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        AppUser user = (AppUser)crit.uniqueResult();
        if(user!=null){
            Hibernate.initialize(user.getUserRoles());
        }
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<AppUser> findAllUsers() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<AppUser> users = (List<AppUser>) criteria.list();
        users.forEach(user ->{
            user.setAdmin(user.getUserRoles().stream().anyMatch(userRole ->
                    userRole.getType().equalsIgnoreCase(UserRoleType.ADMIN.getUserRoleType())));
        });

        // No need to fetch userProfiles since we are not showing them on list page. Let them lazy load.
        // Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserRoles());
		}*/
        return users;
    }

    public void save(AppUser user) {
        logger.error("!!!!!!!!!!!!!!!!!!! save !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        persist(user);
    }

    public void deleteBySSO(String sso) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        AppUser user = (AppUser)crit.uniqueResult();
        delete(user);
    }
}
