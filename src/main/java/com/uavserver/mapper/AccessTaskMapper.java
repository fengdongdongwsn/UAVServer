package com.uavserver.mapper;

import com.uavserver.bean.AccessLevel;
import com.uavserver.bean.AccessTask;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AccessTaskMapper {

    //增加一个数据源
    @Insert("insert into accesstask(id,taskname,taskdesc)values(#{id},#{taskname},#{taskdesc})")
    int insert(AccessTask accessTask);

    //删除一个数据源
    @Delete("delete from accesstask where taskname = #{taskname}")
    int deleteAccessTaskByName(String name);

    //更改一个数据源
    @Update("update accesstask set taskname =#{taskname},taskdesc=#{taskdesc} where taskname=#{taskname}")
    int updateAccessTask(AccessTask accessTask);

    //查询一个Map
    @Select("select * from accesstask where taskname = #{taskname}")
    AccessTask selectAccessTaskByName(String name);

    //查询所有的Map
    @Select("select * from accesstask")
    List<AccessTask> selectAllAccessTask();

}
