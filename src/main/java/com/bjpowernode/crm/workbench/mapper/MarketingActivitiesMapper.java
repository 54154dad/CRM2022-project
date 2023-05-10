package com.bjpowernode.crm.workbench.mapper;

import com.bjpowernode.crm.workbench.model.Activity;

import java.util.List;
import java.util.Map;

public interface MarketingActivitiesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Mon Jan 02 10:17:30 CST 2023
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Mon Jan 02 10:17:30 CST 2023
     */
    int insert(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Mon Jan 02 10:17:30 CST 2023
     */
    int insertSelective(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Mon Jan 02 10:17:30 CST 2023
     */
    Activity selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Mon Jan 02 10:17:30 CST 2023
     */
    int updateByPrimaryKeySelective(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Mon Jan 02 10:17:30 CST 2023
     */
    int updateByPrimaryKey(Activity record);

    /**
     * 查询所有市场活动
     * @return
     */
    List<Activity> selectActivityAll();

    /**
     * 根据条件分页查询市场活动的列表
     * @param map
     * @return
     */
    List<Activity> selectActivityByConditionForPage(Map<String,Object> map);

    /**
     * 根据条件来查询市场活动的总记录条数
     * @param map
     * @return
     */
    int selectCountOfActivityByCondition(Map<String,Object> map);

    /**
     * 根据全选框传来的ids来删除数据
     * @param ids
     * @return
     */
    int deleteByIds(String[] ids);

    /**
     * 根据id查询市场活动
     * @param id
     * @return
     */
    Activity selectActivityById(String id);

    /**
     * 保存修改的市场活动
     */
    int updateActivity(Activity activity);
    /**
     * 根据id查询市场活动
     * 返回List集合
     */
    List<Activity> selectActivityByIds(String[] ids);


    int insertActivityByList(List<Activity> activityList);

    Activity selectActivityForDetailById(String id);

    List<Activity> selectActivityForDetailByClueId(String clueId);
}