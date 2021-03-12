package com.uavserver.controller;


import com.uavserver.bean.AdaptAccess;
import com.uavserver.service.Entropy;
import com.uavserver.service.Topsis;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class AccessController {

    @PostMapping(value = "/access")
    @ApiOperation(value="综合评估", notes="根据评估方法名和数据源名字决定调用哪一个方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "methodname", value = "评估方法名"),
            @ApiImplicitParam(name = "accessname", value = "评估对象名字"),
            @ApiImplicitParam(name = "levelname", value = "等级名字"),
            @ApiImplicitParam(name = "scenename", value = "场景名字"),
            @ApiImplicitParam(name = "dataname", value = "数据源名称"),
    })
    public String adaptAccess(@RequestParam("methodname") String methodname,
                              @RequestParam("accessname") String accessname,
                              @RequestParam("levelname") String levelname,
                              @RequestParam("scenename") String scenename,
                              @RequestParam("dataname") String dataname){

        System.out.println(methodname);

        if(methodname.equals("topsis") || methodname.equals("Topsis") || methodname.equals("TOPSIS")){
            Topsis topsis = new Topsis();
            String res = topsis.access(dataname);
            return res;
        }else if(methodname.equals("Entropy") || methodname.equals("entropy") || methodname.equals("ENTROPY") || methodname=="熵值法"){
            Entropy entropy = new Entropy();
            double res = entropy.entropy(dataname,levelname);
            return res+"";
        }
        return "参数有误，请重新输入";
    }
}
