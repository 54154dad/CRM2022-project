package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.commons.contans.Contants;
import com.bjpowernode.crm.commons.entity.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.commons.utils.UUIDUtils;
import com.bjpowernode.crm.settings.model.DicValue;
import com.bjpowernode.crm.settings.model.User;
import com.bjpowernode.crm.settings.service.DicValueService;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.workbench.model.Activity;
import com.bjpowernode.crm.workbench.model.Clue;
import com.bjpowernode.crm.workbench.model.ClueActivityRelation;
import com.bjpowernode.crm.workbench.model.ClueRemark;
import com.bjpowernode.crm.workbench.service.ClueService;
import com.bjpowernode.crm.workbench.service.ClueRemarkService;
import com.bjpowernode.crm.workbench.service.MarketingActivityService;
import com.bjpowernode.crm.workbench.service.clueActivityRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Classname clueController
 * @Date 2023/4/17
 * @Created by YQ
 */
@Controller
public class clueController {
    @Autowired
    private ClueService clueService;

    @Autowired
    private ClueRemarkService clueRemarkService;

    @Autowired
    private MarketingActivityService marketingActivityService;

    @Autowired
    private DicValueService dicValueService;

    @Autowired
    private UserService userService;

    @Autowired
    private clueActivityRelationService clueActivityRelationService;

    @RequestMapping("/workbench/clue/index.do")
    public String index(HttpServletRequest request){
        List<User> userList = userService.selectUserAll();
        List<DicValue> appellationList = dicValueService.queryDicValueByTypeCode("appellation");
        List<DicValue> clueStateList = dicValueService.queryDicValueByTypeCode("clueState");
        List<DicValue> sourceList = dicValueService.queryDicValueByTypeCode("source");

        request.setAttribute("appellationList",appellationList);
        request.setAttribute("clueStateList",clueStateList);
        request.setAttribute("sourceList",sourceList);
        request.setAttribute("userList",userList);
        return "/workbench/clue/index";
    }

    @RequestMapping("/workbench/activity/saveClue.do")
    @ResponseBody
    public Object saveClue(Clue clue, HttpSession session, HttpServletRequest request){
        User user=(User) session.getAttribute(Contants.SESSION_USER);
        clue.setId(UUIDUtils.getUUID());
        clue.setCreateTime(DateUtils.forMateDateTime(new Date()));
        clue.setCreateBy(user.getId());

        ReturnObject returnObject=new ReturnObject();
        try{
            //调用service层方法，保存创建的线索
            int ret = clueService.saveCreateClue(clue);
            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);

            }else{
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙,请稍后重试....");
            }
        }catch (Exception e){
            e.printStackTrace();

            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙,请稍后重试....");
        }

        return returnObject;
    }
    @RequestMapping("/workbench/clue/queryActivityForDetailByNameClueIdTwo.do")
    public @ResponseBody Object queryActivityForDetailByNameClueIdTwo(String activityName,String clueId){
        //封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("activityName",activityName);
        map.put("clueId",clueId);
        //调用service层方法，查询市场活动
        List<Activity> activityList=marketingActivityService.queryActivityForDetailByNameClueId(map);
        //根据查询结果，返回响应信息
        return activityList;
    }

    @RequestMapping("/workbench/activity/DetailClue.do")
    public String DetailClue(String id,HttpServletRequest request){
        Clue clue = clueService.queryClueForDetailById(id);
        List<ClueRemark> clueRemarkList = clueRemarkService.queryClueRemarkForDetailByClueId(id);
        List<Activity> activityList = marketingActivityService.queryActivityForDetailByClueId(id);

        request.setAttribute("clue",clue);
        request.setAttribute("remarkList",clueRemarkList);
        request.setAttribute("activityList",activityList);

        return "workbench/clue/detail";
    }
    @RequestMapping("/workbench/clue/queryActivityForDetailByNameClueId.do")
    public @ResponseBody Object queryActivityForDetailByNameClueId(String activityName,String clueId){
        //封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("activityName",activityName);
        map.put("clueId",clueId);
        //调用service层方法，查询市场活动
        List<Activity> activityList=marketingActivityService.queryActivityForDetailByNameClueId(map);
        //根据查询结果，返回响应信息
        return activityList;
    }

    @RequestMapping("/workbench/clue/saveBund.do")
    public @ResponseBody Object saveBund(String[] activityId,String clueId){
        //封装参数
        ClueActivityRelation car=null;
        List<ClueActivityRelation> relationList=new ArrayList<>();
        for(String ai:activityId){
            car=new ClueActivityRelation();
            car.setActivityId(ai);
            car.setClueId(clueId);
            car.setId(UUIDUtils.getUUID());
            relationList.add(car);
        }

        ReturnObject returnObject=new ReturnObject();
        try {
            //调用service方法，批量保存线索和市场活动的关联关系
            int ret = clueActivityRelationService.saveCreateClueActivityRelationByList(relationList);

            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);

                List<Activity> activityList=marketingActivityService.queryActivityForDetailByIds(activityId);
                returnObject.setRetData(activityList);
            }else{
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试....");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试....");
        }

        return returnObject;
    }

    @RequestMapping("/workbench/clue/saveUnbund.do")
    public @ResponseBody Object saveUnbund(ClueActivityRelation relation){
        ReturnObject returnObject=new ReturnObject();
        try {
            //调用service层方法，删除线索和市场活动的关联关系
            int ret = clueActivityRelationService.deleteClueActivityRelationByClueIdActivityId(relation);

            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试....");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试....");
        }

        return returnObject;
    }
    @RequestMapping("/workbench/clue/toConvert.do")
    public String toConvert(String id,HttpServletRequest request){
        //调用service层方法，查询线索的明细信息
        Clue clue=clueService.queryClueForDetailById(id);
        List<DicValue> stageList=dicValueService.queryDicValueByTypeCode("stage");
        //把数据保存到request中
        request.setAttribute("clue",clue);
        request.setAttribute("stageList",stageList);
        //请求转发
        return "workbench/clue/convert";
    }

}
