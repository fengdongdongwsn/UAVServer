package com.uavserver.mapper;

import com.uavserver.bean.AccessData;
import com.uavserver.bean.AccessEnv;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AccessEnvMapper {

    //增加一个数据源
    @Insert("insert into accessenv(id,envname,envimg)values(#{id},#{envname},#{envimg})")
    int insert(AccessEnv accessEnv);

    //删除一个数据源
    @Delete("delete from accessenv where envname = #{envname}")
    int deleteAccessEnvByName(String name);

    //更改一个数据源
    @Update("update accessenv set envname =#{envname},envimg=#{envimg} where envname=#{envname}")
    int updateAccessEnv(AccessEnv accessEnv);

    //查询一个Map
    @Select("select * from accessenv where envname = #{envname}")
    AccessEnv selectAccessEnvByName(String name);

    //查询所有的Map
    @Select("select * from accessenv")
    List<AccessEnv> selectAllAccessEnv();

}
