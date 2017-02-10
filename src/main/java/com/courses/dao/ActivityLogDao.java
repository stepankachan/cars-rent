package com.courses.dao;

import com.courses.model.LogActivity;

import java.util.List;

/**
 * @author Stepan.Kachan
 */
public interface ActivityLogDao {

    List<LogActivity> list();

    void save(LogActivity log);

    LogActivity findById(Integer id);
}
