package com.uavserver.bean;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccessObject {
    int id;
    String accessname;//评估对象名称
    String accessscale;//评估对象的规模：比如多少无人设备
    String accessdesc;//评估对象描述信息
    String imgpath;//评估对象图片描述：这里是文件保存的路径

}
