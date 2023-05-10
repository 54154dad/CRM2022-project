package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.model.Activity;
import com.bjpowernode.crm.workbench.model.Clue;

import java.util.List;
import java.util.Map;

/**
 * @Classname MarketingActivityService
 * @Date 2023/1/2
 * @Created by YQ
 */
public interface MarketingActivityService {

     int insert(Activity record);

     List<Activity> selectActivityAll();

     List<Activity> queryActivityByConditionForPage(Map<String,Object> map);

     int selectCountOfActivityByCondition(Map<String,Object> map);

     int deleteByIds(String[] ids);

     Activity selectActivityById(String id);

     int updateActivity(Activity activity);

     List<Activity> selectActivityByIds(String[] ids);

     int saveCreateActivityByList(List<Activity> activityList);

     Activity queryActivityForDetail(String id);
     

     List<Activity> queryActivityForDetailByNameClueId(Map<String, Object> map);


     List<Activity> queryActivityForDetailByIds(String[] ids);

     List<Activity> queryActivityForDetailByClueId(String clueId);
}
