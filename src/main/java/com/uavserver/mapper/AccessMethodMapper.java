package com.uavserver.mapper;

import com.uavserver.bean.AccessLevel;
import com.uavserver.bean.AccessMethod;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AccessMethodMapper {

    //增加一个数据源
    @Insert("insert into accessmethod(id,methodname,methodscene,methoddesc)values(#{id},#{methodname},#{methodscene},#{methoddesc})")
    int insert(AccessMethod accessMethod);

    //删除一个数据源
    @Delete("delete from accessmethod where methodname = #{methodname}")
    int deleteAccessMethodByName(String name);

    //更改一个数据源
    @Update("update accessmethod set methodname =#{methodname},methodscene=#{methodscene},methoddesc=#{methoddesc} where methodname=#{methodname}")
    int updateAccessMethod(AccessMethod accessMethod);

    //查询一个Map
    @Select("select * from accessmethod where methodname = #{methodname}")
    AccessMethod selectAccessMethodByName(String name);

    //查询所有的Map
    @Select("select * from accessmethod")
    List<AccessMethod> selectAllAccessMethod();

}
