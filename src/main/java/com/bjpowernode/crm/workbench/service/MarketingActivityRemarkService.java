package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.model.ActivityRemark;

import java.util.List;

/**
 * @Classname MarketingActivityRemarkService
 * @Date 2023/3/29
 * @Created by YQ
 */
public interface MarketingActivityRemarkService {
    List<ActivityRemark> queryActivityRemarkForDetailByActivityId(String activityId);

    int saveCreateActivityRemark(ActivityRemark activityRemark);

    int deleteActivityRemark(String id);

    int saveEditActivityRemark(ActivityRemark activityRemark);
}
