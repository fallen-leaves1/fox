package com.hsy.creatorservice.controller;

import com.hsy.commonutil.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("creatorservice/user")
public class FoxCreatorLoginController {
    @PostMapping("login")
    public Result login(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token","admin");
        return Result.ok(map);
    }
    @GetMapping("info")
    public Result logininfo(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }


}
