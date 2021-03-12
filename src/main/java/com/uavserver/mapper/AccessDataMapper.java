package com.uavserver.mapper;

import com.uavserver.bean.AccessData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AccessDataMapper {

    //增加一个数据源
    @Insert("insert into accessdata(id,accessname,accessobject,accesslevel,accessscene,accessdesc,accesspath)values(#{id},#{accessname},#{accessobject},#{accesslevel},#{accessscene},#{accessdesc},#{accesspath})")
    int insert(AccessData accessData);

    //删除一个数据源
    @Delete("delete from accessdata where accessname = #{accessname}")
    int deleteAccessDataByName(String name);

    //更改一个数据源
    @Update("update accessdata set accessname =#{accessname},accessobject=#{accessobject},accesslevel=#{accesslevel},accessscene=#{accessscene},accessdesc=#{accessdesc},accesspath=#{accesspath} where accessname=#{accessname}")
    int updateAccessData(AccessData adaptSource);

    //查询一个Map
    @Select("select * from accessdata where accessname = #{accessname}")
    AccessData selectAccessDataByName(String name);

    //查询所有的Map
    @Select("select * from accessdata")
    List<AccessData> selectAllAccessData();

}
