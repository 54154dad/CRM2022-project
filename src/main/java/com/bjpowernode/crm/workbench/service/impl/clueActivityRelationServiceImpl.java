package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.workbench.mapper.ClueActivityRelationMapper;
import com.bjpowernode.crm.workbench.model.ClueActivityRelation;
import com.bjpowernode.crm.workbench.service.clueActivityRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname clueActivityRelationServiceImpl
 * @Date 2023/5/10
 * @Created by YQ
 */
@Service
public class clueActivityRelationServiceImpl implements clueActivityRelationService {
    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;
    @Override
    public int saveCreateClueActivityRelationByList(List<ClueActivityRelation> relationList) {
        return clueActivityRelationMapper.insertClueActivityRelationByList(relationList);
    }

    @Override
    public int deleteClueActivityRelationByClueIdActivityId(ClueActivityRelation relation) {
        return clueActivityRelationMapper.deleteClueActivityRelationByClueIdActivityId(relation);
    }
}
