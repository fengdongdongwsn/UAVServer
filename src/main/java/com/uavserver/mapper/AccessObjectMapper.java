package com.uavserver.mapper;

import com.uavserver.bean.AccessData;
import com.uavserver.bean.AccessObject;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AccessObjectMapper {

    //增加一个数据源
    @Insert("insert into accessobject(id,accessname,accessscale,accessdesc,imgpath)values(#{id},#{accessname},#{accessscale},#{accessdesc},#{imgpath})")
    int insert(AccessObject accessObject);

    //删除一个数据源
    @Delete("delete from accessobject where accessname = #{accessname}")
    int deleteAccessObjectByName(String name);

    //更改一个数据源
    @Update("update accessobject set accessname =#{accessname},accessscale=#{accessscale},accessdesc=#{accessdesc},imgpath=#{imgpath} where accessname=#{accessname}")
    int updateAccessObject(AccessObject accessObject);

    //查询一个Map
    @Select("select * from accessobject where accessname = #{accessname}")
    AccessObject selectAccessObjectByName(String name);

    //查询所有的Map
    @Select("select * from accessobject")
    List<AccessObject> selectAllAccessObject();

}
