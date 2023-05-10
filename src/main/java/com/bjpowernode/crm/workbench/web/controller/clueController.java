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
import com.bjpowernode.crm.workbench.model.ClueRemark;
import com.bjpowernode.crm.workbench.service.ClueService;
import com.bjpowernode.crm.workbench.service.ClueRemarkService;
import com.bjpowernode.crm.workbench.service.MarketingActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

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
    @RequestMapping("/workbench/activity/DetailClue.do")
    public String DetailClue(String id,HttpServletRequest request){
        Clue clue = clueService.queryClueForDetailById(id);
        List<ClueRemark> clueRemarkList = clueRemarkService.queryClueRemarkForDetailByClueId(id);
        List<Activity> activityList = marketingActivityService.queryActivityForDetailByClueId(id);

        request.setAttribute("clue",clue);
        request.setAttribute("clueRemarkList",clueRemarkList);
        request.setAttribute("activityList",activityList);

        return "workbench/clue/detail";
    }

}
