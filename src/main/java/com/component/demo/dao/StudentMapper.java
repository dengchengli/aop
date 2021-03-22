package com.component.demo.dao;

import com.component.demo.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: Dely
 * @Date: 2019/12/9 14:23
 */
@Mapper
public interface StudentMapper {

    @Options(timeout = 10000,  flushCache = Options.FlushCachePolicy.TRUE)
    @Select("select * from student where id= #{id}")
    Student getById(Integer id);

    @Options(timeout = 0, flushCache = Options.FlushCachePolicy.TRUE)
    @Select("select * from student")
    List<Student> getAll();

    @Update({"update student set name = #{student.name} where id = #{student.id}"})
    int update(@Param("student") Student student);

    @Delete("delete from student where id = #{id}")
    int delete(int id);

    @Insert("insert into student(student.name) values(#{student.name})")
    int insert(@Param("student") Student student);


}
