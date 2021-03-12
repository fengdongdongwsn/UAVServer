package com.uavserver.bean;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccessData {

    int id;
    String accessname;//数据源名称
    String accessobject;//要评估的对象
    String accesslevel;//评估等级
    String accessscene;//评估场景

    String accessdesc;//数据源描述信息
    String accesspath;//数据源保存的文件路径

}
