package com.uavserver.mapper;

import com.uavserver.bean.AccessData;
import com.uavserver.bean.AccessScene;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AccessSceneMapper {

    //增加一个数据源
    @Insert("insert into accessscene(id,scenename,taskname,taskdesc,envname,envimg)values(#{id},#{scenename},#{taskname},#{taskdesc},#{envname},#{envimg})")
    int insert(AccessScene accessScene);

    //删除一个数据源
    @Delete("delete from accessscene where scenename = #{scenename}")
    int deleteAccessSceneByName(String name);

    //更改一个数据源
    @Update("update accessscene set scenename =#{scenename},taskname=#{taskname},taskdesc=#{taskdesc},envname=#{envname},envimg=#{envimg} where scenename=#{scenename}")
    int updateAccessScene(AccessScene accessScene);

    //查询一个Map
    @Select("select * from accessscene where scenename = #{scenename}")
    AccessScene selectAccessSceneByName(String name);

    //查询所有的Map
    @Select("select * from accessscene")
    List<AccessScene> selectAllAccessScene();

}
