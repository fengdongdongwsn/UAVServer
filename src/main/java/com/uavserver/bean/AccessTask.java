package com.uavserver.bean;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccessTask {

    int id;
    String taskname;//任务名字
    String taskdesc;//任务描述

}
