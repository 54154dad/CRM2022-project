package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.model.Clue;

/**
 * @Classname ClueService
 * @Date 2023/4/17
 * @Created by YQ
 */
public interface ClueService {

    int saveCreateClue(Clue clue);

    Clue  queryClueForDetailById(String id);


}
