package com.uavserver.bean;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccessMethod {
    int id;
    String methodname;
    String methodscene;
    String methoddesc;
}
