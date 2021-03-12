package com.uavserver.controller;


import com.uavserver.bean.AccessLevel;
import com.uavserver.bean.AccessTask;
import com.uavserver.mapper.AccessLevelMapper;
import com.uavserver.mapper.AccessTaskMapper;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AccessTaskController {

    @Autowired
    private AccessTaskMapper mapper;

    @PostMapping(value = "/task_add")
    @ApiOperation(value = "添加新的评估任务", notes = "添加新的评估任务")
    public String add(AccessTask accessTask) {
        int result = mapper.insert(accessTask);
        if (result > 0) {
            return "success";
        }
        return "failure";
    }

    @PutMapping(value = "/task_update")
    @ApiOperation(value = "更新评估任务", notes = "更新评估任务")
    public String update(AccessTask accessTask) {
        int result = mapper.updateAccessTask(accessTask);
        if(result > 0) {
            return "success";
        }
        return "failure";
    }

    @DeleteMapping(value = "/task_delete")
    @ApiOperation(value = "删除评估任务", notes = "删除评估任务")
    public String delete(String name) {
        int result = mapper.deleteAccessTaskByName(name);
        if (result > 0) {
            return "success";
        }
        return "fail";
    }

    @GetMapping(value = "/task_selectone")
    @ApiOperation(value = "查询评估等级", notes = "查询评估等级")
    public String selectOne(String name) {
        AccessTask accessTask = mapper.selectAccessTaskByName(name);
        JSONArray js = JSONArray.fromObject(accessTask);
        return js.toString();
    }

    @GetMapping(value = "/task_selectall")
    @ApiOperation(value = "查询所有评估等级", notes = "查询所有评估等级")
    public String selectAll() {
        List<AccessTask> list = mapper.selectAllAccessTask();
        JSONArray js = JSONArray.fromObject(list);
        System.out.println(js.toString());
        return js.toString();
    }

}
