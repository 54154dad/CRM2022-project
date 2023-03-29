package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.workbench.mapper.ActivityRemarkMapper;
import com.bjpowernode.crm.workbench.model.ActivityRemark;
import com.bjpowernode.crm.workbench.service.MarketingActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname MarketingActiviceRemarkServiceImpl
 * @Date 2023/3/29
 * @Created by YQ
 */
@Service
public class MarketingActiviceRemarkServiceImpl implements MarketingActivityRemarkService {
    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;

    @Override
    public List<ActivityRemark> queryActivityRemarkForDetailByActivityId(String activityId) {
        return activityRemarkMapper.selectActivityRemarkForDetailByActivityId(activityId);
    }
}
