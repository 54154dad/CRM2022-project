package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.settings.model.DicValue;

import java.util.List;

/**
 * @Classname DivValueService
 * @Date 2023/4/13
 * @Created by YQ
 */
public interface DicValueService {


    List<DicValue> queryDicValueByTypeCode(String typeCode);

//    int saveCreateClue()
}
