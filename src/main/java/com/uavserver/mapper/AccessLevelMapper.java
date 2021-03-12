package com.uavserver.mapper;

import com.uavserver.bean.AccessData;
import com.uavserver.bean.AccessLevel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AccessLevelMapper {

    //增加一个数据源
    @Insert("insert into accesslevel(id,levelaccess,levelname,leveldesc)values(#{id},#{levelaccess},#{levelname},#{leveldesc})")
    int insert(AccessLevel accessLevel);

    //删除一个数据源
    @Delete("delete from accesslevel where levelname = #{levelname}")
    int deleteAccessLevelByName(String name);

    //更改一个数据源
    @Update("update accesslevel set levelaccess =#{levelaccess},levelname=#{levelname},leveldesc=#{leveldesc} where levelaccess=#{levelaccess}")
    int updateAccessLevel(AccessLevel accessLevel);

    //查询一个Map
    @Select("select * from acceasslevel where levelname = #{levelname}")
    AccessLevel selectAccessLevelByName(String name);

    //查询所有的Map
    @Select("select * from accesslevel")
    List<AccessLevel> selectAllAccessLevel();

}
