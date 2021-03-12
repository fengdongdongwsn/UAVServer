package com.uavserver.controller;

import com.uavserver.bean.AccessData;
import com.uavserver.bean.AccessObject;
import com.uavserver.mapper.AccessDataMapper;
import com.uavserver.mapper.AccessObjectMapper;
import com.uavserver.util.ZipUtils;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class AccessObjectController {

    @Autowired
    private AccessObjectMapper mapper;

    //单文件上传
    @PostMapping(value = "/object_upload")
    @ApiOperation(value="评估对象上传", notes="评估对象上传")
    public String upload(@RequestParam("accessname") String accessname,
                         @RequestParam("accessscale") String accessscale,
                         @RequestParam("accessdesc") String accessdesc,
                         @RequestParam("file") MultipartFile file) {
        try {
            //第一步：判断文件是否为空
            if (file.isEmpty()) {
                return "file is empty";
            }
            //第二步：取得文件名字
            String fileName = file.getOriginalFilename();
            //第三步：确定文件保存的路径
            String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\objectimg\\";
            String path = filePath + accessname;
            File dest = new File(path);
            //第四步：检测保存的路径是否存在目录
            if (!dest.exists()) {
                dest.mkdirs();
            }

            //第五步：写入
            file.transferTo(dest);
            //第六步：将文件信息保存在数据库
            List<AccessObject> list = mapper.selectAllAccessObject();
            AccessObject accessObject = new AccessObject(list.size()+1,accessname, accessscale,accessdesc,dest.getPath());
            mapper.insert(accessObject);
            //第七步：处理上传的异常
            return  dest.getPath();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "failure";
    }

    //删除文件
    @DeleteMapping(value = "/object_delete")
    public String mapdelete(String accessname){
        String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\objectimg\\";
        File f = new File(filePath);
        File[] files=f.listFiles();
        for(File file: files){
            if(file.isFile()){
                String filename=file.getName();
                if(filename.equals(accessname)){
                    boolean status = file.delete();
                    if(status==true){
                        mapper.deleteAccessObjectByName(accessname);
                        return  accessname+" delete success";
                    }else{
                        return  accessname+" delete failure";
                    }
                }
            }
        }
        return "file delete fail";
    }

    //单文件查询
    @PostMapping("/object_selectone")
    public String  testDownload(HttpServletResponse response , String accessname) {
        //1、设置为文件配置
        response.setContentType("image/png");
        response.setHeader("Content-Disposition", "attachment; filename=" + accessname);
        byte[] buff = new byte[1024];
        //2、创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            //3、这个路径为待下载文件的路径
            String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\objectimg\\";
            bis = new BufferedInputStream(new FileInputStream(new File(filePath + accessname )));
            int read = bis.read(buff);

            //4、通过while循环写入到指定了的文件夹中
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
        } catch ( IOException e ) {
            e.printStackTrace();
            //出现异常返回给页面失败的信息
            return "fail";
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //成功后返回成功信息
        return "下载成功";
    }

    //多文件查询：文件下载
    @PostMapping("/object_selectall_down")
    public String  selectAll(HttpServletResponse response) {

        //1、创建临时文件夹
        String tempPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\objectimgtmp\\";
        File temDir = new File(tempPath);
        if (!temDir.exists()) {
            temDir.mkdirs();
        }
        //2、找到项目文件存放地址
        String fileUrl = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\objectimg\\";
        //3、移动项目文件到临时文件夹
        try {
            ZipUtils.copyDir(fileUrl, tempPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4、设置response的header
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=uchainfile.zip");

        //5、调用工具类，下载zip压缩包
        try {
            ZipUtils.toZip(temDir.getPath(), response.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //6、删除临时文件和文件夹
        File[] listFiles = temDir.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            listFiles[i].delete();
            System.out.println("正在删除第"+i+"个文件");
        }
        boolean s = temDir.delete();
        if(s==true)return "success";
        return "failure";
    }

    //多文件查询:名字查询
    @PostMapping("/object_selectall_name")
    public String selectAllMapList(){
        ArrayList<AccessObject> lists = (ArrayList<AccessObject>) mapper.selectAllAccessObject();
        JSONArray js = JSONArray.fromObject(lists);
        return js.toString();
    }
}
