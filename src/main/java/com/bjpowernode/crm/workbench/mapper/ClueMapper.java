package com.bjpowernode.crm.workbench.mapper;

import com.bjpowernode.crm.workbench.model.Clue;

public interface ClueMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Mon Apr 17 15:57:33 CST 2023
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Mon Apr 17 15:57:33 CST 2023
     */
    int insert(Clue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Mon Apr 17 15:57:33 CST 2023
     */
    int insertSelective(Clue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Mon Apr 17 15:57:33 CST 2023
     */
    Clue selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Mon Apr 17 15:57:33 CST 2023
     */
    int updateByPrimaryKeySelective(Clue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Mon Apr 17 15:57:33 CST 2023
     */
    int updateByPrimaryKey(Clue record);

    /**
     * 创建线索
     * @param clue
     * @return
     */
    int insertClue(Clue clue);

    /**
     * 查看线索明细
     * @param id
     * @return
     */
    Clue selectClueForDetailById(String id);
}