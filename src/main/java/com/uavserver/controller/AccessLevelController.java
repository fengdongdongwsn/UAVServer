package com.uavserver.controller;


import com.uavserver.bean.AccessLevel;
import com.uavserver.mapper.AccessLevelMapper;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AccessLevelController {

    @Autowired
    private AccessLevelMapper mapper;

    @PostMapping(value = "/accessLevel_add")
    @ApiOperation(value = "添加新的评估等级", notes = "添加新的评估等级")
    public String add(AccessLevel accessLevel) {
        int result = mapper.insert(accessLevel);
        if (result > 0) {
            return "success";
        }
        return "failure";
    }
    @PutMapping(value = "/accessLevel_update")
    @ApiOperation(value = "更新评估等级", notes = "更新评估等级")
    public String update(AccessLevel accessLevel) {
        int result = mapper.updateAccessLevel(accessLevel);
        if (result > 0) {
            return "success";
        }
        return "failure";
    }

    @DeleteMapping(value = "/accessLevel_delete")
    @ApiOperation(value = "删除评估等级", notes = "删除评估等级")
    public String delete(String name) {
        int result = mapper.deleteAccessLevelByName(name);
        if (result > 0) return "success";
        return "fail";
    }

    @GetMapping(value = "/accessLevel_selectone")
    @ApiOperation(value = "查询评估等级", notes = "查询评估等级")
    public String selectOne(String name) {
        AccessLevel accessLevel = mapper.selectAccessLevelByName(name);
        JSONArray js = JSONArray.fromObject(accessLevel);
        return js.toString();
    }

    @GetMapping(value = "/accessLevel_selectall")
    @ApiOperation(value = "查询所有评估等级", notes = "查询所有评估等级")
    public String selectAll() {
        List<AccessLevel> list = mapper.selectAllAccessLevel();
        JSONArray js = JSONArray.fromObject(list);
        System.out.println(js.toString());
        return js.toString();
    }

}
