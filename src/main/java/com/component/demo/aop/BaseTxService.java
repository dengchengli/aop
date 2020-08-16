package com.component.demo.aop;

import com.component.demo.dao.StudentMapper;
import com.component.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Dely
 * @Date: 2019/12/13 18:22
 */


@Service
public class BaseTxService {
    @Autowired
    private StudentMapper dao;

    /**
     * 结论：事务隔离级别，是相对于自己的方法而言的，与其他事务方法无关，即修改的方法用了可提交读，但是读的方法用了未提交读，则读的方法依然读到脏数据。
     */
    /**
     * 数据不安全的概念：
     * * 脏读：指事务可以读取未提交的数据。即另外一个事务修改了数据，但是还没有提交，如果修改的那个事务回滚了，则读的方法读到的数据就是脏数据。
     * * 不可重复读：指事务只能看到那些已经提交的事务所做的修改。 这样会造成两次执行同样的查询可能会得到不一样的结果。
     * * 可重复读：指其他事务修改事务提交与否不影响该事务的读取结果。
     * * 幻读：
     * <p>
     * 测试隔离级别：使用默认的传播机制来测试。 isolation
     * 1.未提交读：
     * * 首先要明确，读的隔离级别，才会影响到是否读到脏数据之类的。而如果不清除orm框架的一级缓存，则它不会再查数据库。所以要清除才能实验。
     * mybatis中，基于注解的方式可以通过设置每次读前都会清除缓存来进行达到目的。
     * <p>
     * 2.已提交读：测试成功。即修改的事务未提交，读的事务是读不到修改值的。  该隔离级别避免了脏读，但是存在不可重复读和幻读的情况。
     * <p>
     * 3.可重复读：测试失败，无法重现幻读，spring中事务幻读的处理不太清清楚。
     * <p>

     *
     *
     */


    /**
     *
     * @param student
     * @return
     * @throws RuntimeException
     */
    //@Transactional(propagation = Propagation.REQUIRED)
    public String update(Student student) throws RuntimeException{
        int update = 0;
            System.out.println("修改名字前。。。。");
            update = dao.update(student);
            System.out.println("修改后");
            //Thread.sleep(10000);
            System.out.println("返回中、、、、、");


            this.inUpdate(student);

        //throw new IllegalArgumentException("A回滚了");
        return update >= 1 ? "修改成功" : "修改失败";
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String inUpdate(Student student) throws RuntimeException{
        int update = 0;
            System.out.println("修改名字前。。。。");
            update = dao.update(student);
            System.out.println("修改后");
            System.out.println("返回中、、、、、");
            throw new RuntimeException("B回滚了");
        //return "1";

    }


    @Transactional
    public int insert(Student student) {
        int count = 0;
        try {
            Thread.sleep(5000);
            System.out.println("修改名字前。。。。");
            count = dao.insert(student);
            System.out.println("修改后");
            //Thread.sleep(10000);
            System.out.println("返回中、、、、、");
            //throw new RuntimeException("我回滚了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count;
    }


    /**
     * 可重复读隔离级别，使用mybatis作为orm框架时，在spring事务中，无法模拟幻读的场景，可能是因为缓存或者是事务管理器的问题。
     *
     * @param id
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Student getById(int id) {
        //Student s = dao.getById(id);
        List<Student> s = dao.getAll();
        try {
            System.out.println(s);
            Thread.sleep(10000);
            s = dao.getAll();
            //s = dao.getAll(id+1);
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return s.get(0);
    }

}
