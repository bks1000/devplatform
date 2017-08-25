package com.june.controller.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.june.core.utils.JsonUtils;
import com.june.dto.bo.Bodef;
import com.june.dto.form.Forminfo;
import com.june.dto.form.Formtype;
import com.june.service.bo.IBodefService;
import com.june.service.form.IForminfoService;
import com.june.service.form.IFormtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/8/21.
 */
@Controller
@RequestMapping("/comm")
public class CommonController {

    @Autowired
    private IForminfoService forminfoService;

    @Autowired
    private IFormtypeService formtypeService;

    @Autowired
    private IBodefService bodefService;

    /**
     * 选择实体对象
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping("/forminfo")
    public ModelAndView getEntityInfo() throws JsonProcessingException {
        ModelAndView mav = new ModelAndView("common/commonkv");
        List<Forminfo> lst = forminfoService.getForminfoList();
        List<Map<String,Object>> data = new ArrayList<Map<String, Object>>();
        for (Forminfo forminfo:lst) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("id",forminfo.getFid());
            map.put("text",forminfo.getFmark());
            map.put("name",forminfo.getFname());
            data.add(map);
        }
        mav.addObject("data", JsonUtils.listToJson(data));
        return mav;
    }

    /**
     * 选择业务对象
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping("/boinfo")
    public ModelAndView getBoInfo() throws JsonProcessingException {
        ModelAndView mav = new ModelAndView("common/commonkv");
        List<Bodef> lst = bodefService.getBodefList();
        List<Map<String,Object>> data = new ArrayList<Map<String, Object>>();
        for (Bodef bodef:lst) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("id",bodef.getBoid());
            map.put("text",bodef.getBomark());
            map.put("name",bodef.getBoname());
            data.add(map);
        }
        mav.addObject("data", JsonUtils.listToJson(data));
        return mav;
    }

    @RequestMapping("/formtype")
    @ResponseBody
    public String getFormType() throws JsonProcessingException {
       List<Formtype> lst = formtypeService.getFormtypeList();
       return JsonUtils.listToJson(lst);
    }
}
