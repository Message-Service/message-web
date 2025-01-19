package com.mservice.msg_web.controller;

import com.mservice.msg_web.mapper.HojunTestMapper;
import com.mservice.msg_web.model.HojunTest;
import org.apache.catalina.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private HojunTestMapper hojunTestMapper;

    @RequestMapping("")
    public String test(Model model) {
        model.addAttribute("title", "test");
        return "test/index";
    }

    @RequestMapping("/db")
    public String testDB(Model model) {
        List<HojunTest> ht = hojunTestMapper.findAll();

        var text = ht
                .stream()
                .findFirst()
                .map(HojunTest::getText)
                .orElse("");

        model.addAttribute("title", text);

        return "test/index";
    }
}
