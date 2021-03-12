package com.uavserver.bean;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccessLevel {

    int id;
    String levelaccess;//评估等级
    String levelname;//等级名字
    String leveldesc;//等级描述

}
