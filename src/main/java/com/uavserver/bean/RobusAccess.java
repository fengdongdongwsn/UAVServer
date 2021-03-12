package com.uavserver.bean;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RobusAccess {

    private int id;

    private String sourcename; //数据源

    private double obsden; //突发障碍物密度
    private double dynnum; //动态人物目标个数
    private double noderadio; //失效节点比例
    private double nodetimeradio; //节点失效起始时间比例

    private double changeradio;//任务变化率完成率；
    private double distanceradio;//平均飞行距离变化率
    private double comdelayradio;//通信时延变化率
    private double comlossradio;//通信丢包率变化率

}
