package com.courses.service.impl;

import com.courses.dao.AbstractDao;
import com.courses.dao.ActivityLogDao;
import com.courses.model.LogActivity;
import com.courses.service.LogActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Stepan.Kachan
 */
@Service("activityLogService")
@Transactional
public class LogActivityServiceImpl extends AbstractDao<Integer, LogActivity> implements LogActivityService {

    @Autowired
    private ActivityLogDao dao;

    @Override
    public List<LogActivity> list() {
        return dao.list();
    }

    @Override
    public void save(LogActivity log) {
        dao.save(log);
    }

    @Override
    public LogActivity findById(Integer id) {
        return dao.findById(id);
    }
}
