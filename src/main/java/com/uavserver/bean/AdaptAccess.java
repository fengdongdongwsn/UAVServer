package com.uavserver.bean;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdaptAccess {

    private int id;

    private String sourcename; //数据源

    private double obsnum; //障碍物数量
    private double obsden; //障碍物密度
    private double obshm; //障碍物高度均值
    private double obshd; //障碍物高度方差
    private double obsregd; //障碍物区域密度方差
    private double obsgridd; //障碍物栅格密度方差
    private double effect; //效果
    private double effecincy; //效率
    private double benefit; //效益

}
