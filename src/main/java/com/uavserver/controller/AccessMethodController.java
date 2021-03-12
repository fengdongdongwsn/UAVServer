package com.uavserver.controller;


import com.uavserver.bean.AccessLevel;
import com.uavserver.bean.AccessMethod;
import com.uavserver.mapper.AccessLevelMapper;
import com.uavserver.mapper.AccessMethodMapper;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AccessMethodController {

    @Autowired
    private AccessMethodMapper mapper;

    @PostMapping(value = "/method_add")
    @ApiOperation(value = "添加新的评估方法", notes = "添加新的评估方法")
    public String add(AccessMethod accessMethod) {
        int result = mapper.insert(accessMethod);
        if (result > 0) {
            return "success";
        }
        return "failure";
    }

    @PostMapping(value = "/method_update")
    @ApiOperation(value = "更新评估方法", notes = "更新评估方法")
    public String update(AccessMethod accessMethod) {
        int result = mapper.updateAccessMethod(accessMethod);
        if (result > 0) {
            return "success";
        }
        return "failure";
    }

    @PostMapping(value = "/method_delete")
    @ApiOperation(value = "删除评估方法", notes = "删除评估方法")
    public String delete(String name) {
        int result = mapper.deleteAccessMethodByName(name);
        if (result > 0) {
            return "success";
        }
        return "fail";
    }

    @PostMapping(value = "/method_selectone")
    @ApiOperation(value = "查询评估方法", notes = "查询评估方法")
    public String selectOne(String name) {
        AccessMethod accessMethod = mapper.selectAccessMethodByName(name);
        JSONArray js = JSONArray.fromObject(accessMethod);
        return js.toString();
    }

    @PostMapping(value = "/method_selectall")
    @ApiOperation(value = "查询所有评估方法", notes = "查询所有评估方法")
    public String selectAll() {
        List<AccessMethod> list = mapper.selectAllAccessMethod();
        JSONArray js = JSONArray.fromObject(list);
        System.out.println(js.toString());
        return js.toString();
    }

}
