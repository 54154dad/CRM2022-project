package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.model.ClueActivityRelation;

import java.util.List;

/**
 * @Classname clueActivityRelationService
 * @Date 2023/5/10
 * @Created by YQ
 */

public interface clueActivityRelationService {
    int saveCreateClueActivityRelationByList(List<ClueActivityRelation> relationList);

    int deleteClueActivityRelationByClueIdActivityId(ClueActivityRelation relation);
}
