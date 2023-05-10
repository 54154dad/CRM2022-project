package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.model.ClueRemark;

import java.util.List;

/**
 * @Classname clueRemarkService
 * @Date 2023/4/24
 * @Created by YQ
 */
public interface ClueRemarkService {
   List<ClueRemark> queryClueRemarkForDetailByClueId(String clueId);
}
