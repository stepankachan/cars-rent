package com.courses.service;

import com.courses.model.LogActivity;

import java.util.List;

/**
 * @author Stepan.Kachan
 */
public interface LogActivityService {

    List<LogActivity> list();

    void save(LogActivity log);

    LogActivity findById(Integer id);
}
