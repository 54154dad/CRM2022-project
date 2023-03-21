package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.commons.contans.Contants;
import com.bjpowernode.crm.commons.entity.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.commons.utils.UUIDUtils;
import com.bjpowernode.crm.settings.model.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.workbench.model.Activity;
import com.bjpowernode.crm.workbench.service.MarketingActivityService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

/**
 * @Classname activityController
 * @Date 2022/12/26
 * @Created by YQ
 */
@Controller
public class activityController {
    @Autowired
    private UserService userService;

    @Autowired
    private MarketingActivityService marketingActivityService;

    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request){
        List<User> userList = userService.selectUserAll();
        request.setAttribute("userList",userList);

        return "/workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    public @ResponseBody Object saveCreateActivity(Activity activity, HttpSession session,HttpServletRequest request){
        User user=(User) session.getAttribute(Contants.SESSION_USER);
        //封装参数
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateUtils.forMateDateTime(new Date()));
        activity.setCreateBy(user.getId());


        ReturnObject returnObject=new ReturnObject();
        try {
            //调用service层方法，保存创建的市场活动
            int ret = marketingActivityService.insert(activity);

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
    @RequestMapping("/workbench/activity/queryActivityByConditionForPage.do")
    @ResponseBody
    public Object queryActivityByConditionForPage(String name,String owner,String startDate
            ,String endDate,int pageNo,int pageSize){
        //封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("beginNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        //调用service层方法，查询数据
        List<Activity> activityList=marketingActivityService.queryActivityByConditionForPage(map);
        int totalRows=marketingActivityService.selectCountOfActivityByCondition(map);
        //根据查询结果结果，生成响应信息
        Map<String,Object> retMap=new HashMap<>();
        retMap.put("activityList",activityList);
        retMap.put("totalRows",totalRows);
        return retMap;

    }
    /**
     * 删除市场活动
     *
     */
    @RequestMapping("/workbench/activity/deleteByIds.do")
    @ResponseBody
    public Object deleteByIds(String[] id){
        ReturnObject returnObject = new ReturnObject();
        try{
            //调用service方法
            int ret = marketingActivityService.deleteByIds(id);
            if (ret > 0){
                //大于0删除成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统错误,请稍后再试！");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统错误,请稍后再试！");
        }

      return returnObject;
    }
    /**
     * 根据id查询市场活动
     */
    @RequestMapping("/workbench/activity/queryActivityById.do")
    @ResponseBody
    public Object queryActivityById(String id){
        Activity activity = marketingActivityService.selectActivityById(id);
        //根据查询结果，返回响应信息
        return activity;
    }

    /**
     * 更新修改的市场活动
     */
    @RequestMapping("/workbench/activity/updateActivity.do")
    @ResponseBody
    public Object updateActivity(Activity activity,HttpSession session){
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        //封装参数
        activity.setEditTime(DateUtils.forMateDateTime(new Date()));
        activity.setEditBy(user.getId());

        ReturnObject returnObject = new ReturnObject();

        try{
            //调用service层方法
            int ret = marketingActivityService.updateActivity(activity);
            if (ret > 0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后再试！");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后再试！");
        }
        return returnObject;
    }


    /**
     * 文件下载
     */
    @RequestMapping("/workbench/activity/fileDownload.do")
    public void fileDownload(HttpServletResponse response) throws IOException {
        //设置响应类型
        response.setContentType("application/octet-stream;charset=UTF-8");
        //获取输出流
        OutputStream out = response.getOutputStream();

        //浏览器接收到响应信息后，默认情况下，直接在显示窗口中打开响应信息，即使打不开，也会调用应用程序打开；只有是在打不开，才会激活文件下载窗口
        //可以设置响应头信息，是浏览器接收到响应信息后，直接激活文件下载窗口，即使能打开也不打开
        response.setHeader("Content-Disposition","attachment;filename=myStudentList.xls");
        //读取excel文件(InputStream),把输出到浏览器(OutputStream)
        InputStream is = new FileInputStream("E:\\JavaCreateExcelTest\\studentList.xls");
        byte[] buff = new byte[256];
        int len = 0;
        while((len = is.read(buff))!=-1){
            out.write(buff,0,len);
        }
        //关闭资源
        is.close();
        out.flush();
    }

    /**
     * 导出市场活动
     */
    @RequestMapping("/workbench/activity/queryAllActivities.do")
    public void queryAllActivities(HttpServletResponse response) throws IOException {
        List<Activity> activityList = marketingActivityService.selectActivityAll();

        //创建HSSFWorkbook对象，对应一个excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //使用wb创建HSSFSheet对象,对应wb文件中的一页
        HSSFSheet sheet = wb.createSheet("市场活动列表");
        //使用sheet创建HSSFRow对象，对应wb文件中的一行
        HSSFRow row = sheet.createRow(0);//行号从0开始，以此增加
        //使用row创建HSSFCell对象，对应row中的一列
        HSSFCell cell = row.createCell(0);//列的编号，从0开始以此增加
        cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("所有者");
        cell = row.createCell(2);
        cell.setCellValue("名称");
        cell = row.createCell(3);
        cell.setCellValue("开始日期");
        cell = row.createCell(4);
        cell.setCellValue("结束日期");
        cell = row.createCell(5);
        cell.setCellValue("成本");
        cell = row.createCell(6);
        cell.setCellValue("描述");
        cell = row.createCell(7);
        cell.setCellValue("创建时间");
        cell = row.createCell(8);
        cell.setCellValue("创建者");
        cell = row.createCell(9);
        cell.setCellValue("创建时间");
        cell = row.createCell(10);
        cell.setCellValue("修改者");
        //遍历activityList
        if (activityList != null && activityList.size() > 0) {
            Activity activity = null;
            for (int i = 0; i < activityList.size(); i++) {
                activity = activityList.get(i);
                //每遍历一个activity,生成一行
                row = sheet.createRow(i+1);
                cell = row.createCell(0);
                cell.setCellValue(activity.getId());
                cell = row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell = row.createCell(2);
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell = row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell = row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }
        //调用工具函数生成excel
        OutputStream os = new FileOutputStream("E:\\JavaCreateExcelTest\\ActivityList.xls");
        wb.write(os);

        //释放资源
        os.close();
        wb.close();
        //把生成的excel文件下载到客户端
        //设置响应类型
        response.setContentType("application/octet-stream;charset=UTF-8");
        //获取输出流
        OutputStream out = response.getOutputStream();

        //浏览器接收到响应信息后，默认情况下，直接在显示窗口中打开响应信息，即使打不开，也会调用应用程序打开；只有是在打不开，才会激活文件下载窗口
        //可以设置响应头信息，是浏览器接收到响应信息后，直接激活文件下载窗口，即使能打开也不打开
        response.setHeader("Content-Disposition","attachment;filename=myActivityList.xls");
        //读取excel文件(InputStream),把输出到浏览器(OutputStream)
        InputStream is = new FileInputStream("E:\\JavaCreateExcelTest\\ActivityList.xls");
        byte[] buff = new byte[256];
        int len = 0;
        while((len = is.read(buff))!=-1){
            out.write(buff,0,len);
        }
        //关闭资源
        is.close();
        out.flush();
    }

    /**
     * 选择导出市场活动
     *  *每次至少选择导出一条记录
     * 	*导出成功之后,页面不刷新
     * @param id
     * @return
     */
    @RequestMapping("/workbench/activity/queryAllActivitiesById.do")
    @ResponseBody
    public void queryAllActivitiesById(String[] id, HttpServletResponse response) throws IOException {
        List<Activity> activityList = marketingActivityService.selectActivityByIds(id);
        HSSFWorkbook workbook = new HSSFWorkbook();
        //sheet这一页的“页名称”
        HSSFSheet sheet = workbook.createSheet("市场活动列表");
        HSSFRow row = sheet.createRow(0);//第一行
        HSSFCell cell = row.createCell(0);//第一行第一列
        cell.setCellValue("ID");
        cell = row.createCell(1);//第一行第二例
        cell.setCellValue("所有者");
        cell = row.createCell(2);//第一行第三列
        cell.setCellValue("名称");
        cell = row.createCell(3);
        cell.setCellValue("开始日期");
        cell = row.createCell(4);
        cell.setCellValue("结束日期");
        cell = row.createCell(5);
        cell.setCellValue("成本");
        cell = row.createCell(6);
        cell.setCellValue("描述");
        cell = row.createCell(7);
        cell.setCellValue("创建时间");
        cell = row.createCell(8);
        cell.setCellValue("创建者");
        cell = row.createCell(9);
        cell.setCellValue("修改时间");
        cell = row.createCell(10);
        cell.setCellValue("修改者");
        //循环遍历市场活动集合列表
        if(activityList!=null && activityList.size()>0){
            Activity activity = null;
            //遍历activityList,创建HSSFRown对象，生成所有数据行
            for(int i=0;i<activityList.size();i++){
                activity = activityList.get(i);

                //每次遍历出一个activity对象，创建数据行（生成一行）
                //i+1是因为第一行已经创建好了，是表头行
                row = sheet.createRow(i + 1);//第1+i行
                //每一行创建11列，每一列的数据从activity对象中获取
                cell = row.createCell(0);//第1+i行第一列
                cell.setCellValue(activity.getId());
                cell = row.createCell(1);//第1+i行第二列
                cell.setCellValue(activity.getOwner());
                cell = row.createCell(2);//第1+i行第三列
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell = row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell = row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
            //根据workbook对象生成Excel文件
       /* OutputStream fileOutputStream = new FileOutputStream("F:\\360downloads\\filedownload\\activityList.xls");
        workbook.write(fileOutputStream);
        //释放资源
        fileOutputStream.close();*/
            workbook.close();
            /**
             * 把生成的Excel文件从服务器端下载到客户端
             */
            //1.设置响应类型
            response.setContentType("appplication/octet-stream;charset=UTF-8");
            //2.获取输出流
            OutputStream out = response.getOutputStream();
            //浏览器接收到响应请求信息后，默认情况下，直接在显示窗口打开响应信息，即使打不开，也会调用应用程序打开；
//            只有实在打不开，才会激活文件下载窗口
            //可以设置响应头信息，使浏览器接收到响应信息后，直接激活文件下载窗口，即使能打开也不打开
            response.addHeader("Content-Disposition","attachment;filename=activitySelectList.xls");
           /* //读取Excel文件（InputStream），把输出到到浏览器（OutPutStream）
            InputStream is = new FileInputStream("F:\\360downloads\\filedownload\\activityList.xls");
            byte[] buff = new byte[256];
            int len = 0;
            while ((len = is.read(buff))!=-1){
                out.write(buff,0,len);

            }
            System.out.println("len="+len);
            System.out.println("buff="+buff);*/
//        释放资源
            /*is.close();*/
            workbook.write(out);
            out.flush();
            System.out.println("文件下载完成~~~~~~~");
            System.out.println("第二次修改还");
            System.out.println("第三次修改还");
            System.out.println("master test");
            System.out.println("hot-fix test!");
            System.out.println("push test!");
             System.out.println("pull test!");
             System.out.println("gitee test!");
              System.out.println("gitee test2!");
               System.out.println("gitee test3!");
        }
    }



}
