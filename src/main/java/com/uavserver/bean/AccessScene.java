package com.uavserver.bean;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccessScene {

    int id;
    String scenename;//场景名称
    String taskname;//任务名称
    String taskdesc;//任务描述
    String envname;//环境名称
    String envimg;//环境图片保存的文件路径

}
