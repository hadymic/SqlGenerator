package com.hadymic.sqlgenerator.controller;

import com.hadymic.sqlgenerator.model.JsonRootBean;
import com.hadymic.sqlgenerator.service.IAdJson2SqlService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
public class AdJson2SqlController {
    @Autowired
    private IAdJson2SqlService adJson2SqlService;

    private static int num = 0;

    @PostMapping("save")
    public String adJson2Sql(@RequestBody JsonRootBean root) {
        log.info(++num + " : " + "AdJsonBean: " + root.toString());
        boolean b = adJson2SqlService.save(root);
        return b ? "success" : "false";
    }
}
