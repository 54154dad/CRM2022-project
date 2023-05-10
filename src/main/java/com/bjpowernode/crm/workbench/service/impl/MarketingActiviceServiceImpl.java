package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.workbench.mapper.MarketingActivitiesMapper;
import com.bjpowernode.crm.workbench.model.Activity;
import com.bjpowernode.crm.workbench.service.MarketingActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Classname MarketingActiviceServiceImpl
 * @Date 2023/1/2
 * @Created by YQ
 */
@Service
public class MarketingActiviceServiceImpl implements MarketingActivityService {
    @Autowired
    private MarketingActivitiesMapper marketingActivitiesMapper;

    @Override
    public int insert(Activity record) {
        return marketingActivitiesMapper.insert(record);
    }

    /**
     * 查询所有市场活动
     * @return
     */
    @Override
    public List<Activity> selectActivityAll() {
        return marketingActivitiesMapper.selectActivityAll();
        
    }

    /**
     * 调用mapper层的分页查询
     * @param map
     * @return
     */
    @Override
    public List<Activity> queryActivityByConditionForPage(Map<String, Object> map) {
        return marketingActivitiesMapper.selectActivityByConditionForPage(map);
    }

    /**
     *
     * @param map
     * @return
     */
    @Override
    public int selectCountOfActivityByCondition(Map<String, Object> map) {
        return marketingActivitiesMapper.selectCountOfActivityByCondition(map);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return marketingActivitiesMapper.deleteByIds(ids);
    }

    @Override
    public Activity selectActivityById(String id) {
        return marketingActivitiesMapper.selectActivityById(id);
    }

    @Override
    public int updateActivity(Activity activity) {
        return marketingActivitiesMapper.updateActivity(activity);
    }

    @Override
    public List<Activity> selectActivityByIds(String[] ids) {
        return marketingActivitiesMapper.selectActivityByIds(ids);
    }

    @Override
    public int saveCreateActivityByList(List<Activity> activityList) {
        return marketingActivitiesMapper.insertActivityByList(activityList);
    }

    @Override
    public Activity queryActivityForDetail(String id) {
        return marketingActivitiesMapper.selectActivityForDetailById(id);
    }


    @Override
    public List<Activity> queryActivityForDetailByNameClueId(Map<String, Object> map) {
        return marketingActivitiesMapper.selectActivityForDetailByNameClueId(map);
    }

    @Override
    public List<Activity> queryActivityForDetailByIds(String[] ids) {
        return marketingActivitiesMapper.selectActivityForDetailByIds(ids);
    }

    @Override
    public List<Activity> queryActivityForDetailByClueId(String clueId) {
        return marketingActivitiesMapper.selectActivityForDetailByClueId(clueId);
    }


}
