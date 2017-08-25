package com.june.controller.workflow;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lenovo on 2017/8/23.
 */
@Controller
public class Modeler {

    @RequestMapping("/modeler")
    public String getMoldeler(){
        return "modeler";
    }
}
