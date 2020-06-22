package com.hadymic.sqlgenerator.controller;

import com.hadymic.sqlgenerator.model.JsonRootBean;
import com.hadymic.sqlgenerator.service.IAdJson2SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdJson2SqlController {
    @Autowired
    private IAdJson2SqlService adJson2SqlService;

    @PostMapping("save")
    public String adJson2Sql(@RequestBody JsonRootBean root) {
        System.out.println(root);
        boolean b = adJson2SqlService.save(root);
        return b ? "success" : "false";
    }
}
