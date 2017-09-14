package com.june.controller.workflow;

import com.june.core.utils.PageUtils;
import com.june.core.utils.UserUtil;
import com.june.dto.form.Webform;
import com.june.service.form.IWebformService;
import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 用户流程应用
 * 先完善，用户，角色，权限，再配置流程，不同用户（角色），不同任务。最后，发起流程，记得配置哪个用户发起的。
 * Created by lenovo on 2017/9/4.
 */
@Controller
@RequestMapping("/mywf")
public class UserWfController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private FormService formService;

    @Autowired
    private IWebformService webformService;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private IdentityService identityService;

    /**
     * 获取（当前用户下的）所有可以用的流程列表
     * 用于发起流程
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView getWfList(){
        ModelAndView mav = new ModelAndView("workflow/user-process-list");
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        //query.startableByUser("") //获取用户可以发起的流程。目前没有用户信息，获取全部
        List<ProcessDefinition> lst =  query.list();
        mav.addObject("ProcessDefinition",lst);
        return  mav;
    }

    /**
     * 发起流程，1.返回自定义表单
     * @param processid
     * @param deployid
     * @return
     */
    @RequestMapping("/start") //TODO:还没有找到表单html代码
    public ModelAndView start(@RequestParam("processid")String processid,
                              @RequestParam("deployid")String deployid,
                              @RequestParam("key")String processkey){
        ModelAndView mav = new ModelAndView("workflow/user-process-start");
        // 根据流程定义ID读取外置表单
        //Object startForm = formService.getRenderedStartForm(processid);
        //mav.addObject("form",startForm);
        ProcessDefinition pd = repositoryService.getProcessDefinition(processid);
        //boolean hasStartFormKey = pd.hasStartFormKey();
        // 判断是否有formkey属性
        /*if (hasStartFormKey) {
            Object renderedStartForm = formService.getRenderedStartForm(processid);
            //Object renderedStartForm = formService.getRenderedStartForm(processDefinition.getId(), "juel");

            mav.addObject("startFormData", renderedStartForm);
            mav.addObject("processDefinition", pd);
        } else { // 动态表单字段
            StartFormData startFormData = formService.getStartFormData(processid);
            mav.addObject("startFormData", startFormData);
        }*/
        StartFormData startFormData = formService.getStartFormData(processid);
        String formkey = startFormData.getFormKey();
        //mav.addObject("startFormData", startFormData);
        mav.addObject("formkey", formkey);
        mav.addObject("processid",processid);
        mav.addObject("deployid",deployid);
        Webform form = webformService.getWebformByFormkey(formkey);
        //mav.addObject("processDefinitionId", processid);
        mav.addObject("form",form);
        return  mav;
    }

    /**
     * 保存并启动流程
     * @param request
     * @return
     */
    @RequestMapping("/run")
    @ResponseBody
    public String run(HttpServletRequest request, HttpSession session){
        Map<String,Object> param = PageUtils.getParameters(request);
        String processid = param.get("processid").toString();
        param.remove("processid");
        String boid = param.get("boid").toString();
        //保存数据到业务实体表
        String businessid = webformService.saveBusinessData(param);
        //启动流程
        User user = UserUtil.getUserFromSession(session);
        identityService.setAuthenticatedUserId(user.getId());
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processid).singleResult();
        //// 使用流程定义的key启动流程实例，key对应bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(processDefinition.getKey(),businessid);
        System.out.println("流程实例成功");
        System.out.println("流程实例ID:" + processInstance.getId());// 流程实例ID
        System.out.println("流程定义ID:" + processInstance.getProcessDefinitionId());

        //将流程实例ID更新到业务表中
        webformService.saveBusinessInst(boid,businessid,processInstance.getId());
        return "OK";
    }
}
