package com.component.demo.dao;

import com.component.demo.entity.Course;
import org.apache.ibatis.annotations.*;

/**
 * @Author: Dely
 * @Date: 2020/7/28 21:59
 */

@Mapper
public interface CourseMapper {
    // @Options(timeout = 10000,  flushCache = Options.FlushCachePolicy.TRUE)
    @Select("select * from course where id= #{id}")
    Course getById(int id);

    @Update({"update course set name = #{course.name}, grade = #{course.grade} where id = #{course.id}"})
    int update(@Param("course") Course course);

    @Delete("delete from course where id = #{id}")
    int delete(int id);

    @Insert("insert into course(name, grade) values(#{course.name}, #{course.grade})")
    int insert(@Param("course") Course course);
}
