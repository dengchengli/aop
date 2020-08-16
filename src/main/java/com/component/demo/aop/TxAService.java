package com.component.demo.aop;

/**
 * @Author: Dely
 * @Date: 2019/12/15 19:31
 */

import com.component.demo.dao.StudentMapper;
import com.component.demo.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 由于
 */

/**
 * 外层逻辑
 */
@Service
public class TxAService {
    private static final Logger LOG = LoggerFactory.getLogger(TxAService.class);

    @Autowired
    private StudentMapper studentDao;

    @Autowired
    private TxBService txBService;

    /**
     * 事务传播机制：
     * * 事务传播机制：propagation
     *      * 1.REQUIRED：默认传播级别：
     *      *    *如果调用方已经开启了事务，则被调用方就直接添加到调用方的事务里边，如果调用方没有事务，则创建新事务。此时的事务会一起提交和回滚，就是把多个事务绑定到一个事务里边进行操作。
     *      *         **回滚情况：调用方和被调用方都有事务。
     *      *            [1]如果被调用的事务方法抛出了异常，调用方没捕获该异常，则一起回滚。
     *      *            [2]如果被调用方发生了异常，调用方捕获了异常，并且捕获了之后事务正常提交，则会抛出异常，照样会回滚。
     *      *            [3]如果调用方运行期间异常，则都会回滚。
     *      *            [4]如果外层不存在事务，则外层的异常对内层事务不产生影响。
     *      *
     *      * 2.REQUIRES_NEW:会新建一个事务，无论外层是否有事务。
     *      *         **回滚情况：
     *      *            [1]如果被调用方抛异常，外部事务不捕获，则都回滚。
     *      *            [2]如果被调用方抛异常，外部事务捕获的话，则被调用方回滚，外部事务不回滚。
     *      *            [3]如果外部事务抛异常，被调用方不跑异常，则外部事务回滚，被调用方不回滚。
     *      *
     *      * 3.SUPPORTS:如果调用内部事务的方法存在事务，则被调用方运行在外部的事务环境中，如果外部没有事务，则运行在非事务环境中。
     *                   注意：SUPPORTS类型的事务，不会自定创建事务，故而如果该类型的事务作为最外部事务的话，实质上该事务运行在非事务环境中。
     *                **回滚情况：
     *                   [1]如果调用方不存在事务，则无论内外出现异常，都不会回滚。
     *                   [2]如果调用方存在事务，被调用方抛出异常，都会回滚。
     *                   [3]如果调用方存在事务，调用方抛出异常，都会回滚。
     *
     *       *4.NOT_SUPPORTED:
     *
     *       *5.
     *
     */

    /**
     *
     *
     * @param student
     * @return
     * @throws RuntimeException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public String update(Student student) throws RuntimeException {
        int update = 0;
        System.out.println("修改名字前。。。。");
        update = studentDao.update(student);
        System.out.println("修改后");
        //Thread.sleep(10000);
        System.out.println("返回中、、、、、");


        try {
            txBService.inUpdate(student);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        //throw new IllegalArgumentException("A回滚了");
        return update >= 1 ? "修改成功" : "修改失败";
    }


    /**
     * 默认事务传播机制：都针对unchecked异常来说。其他的被检测异常都无效。
     * 1.调用的方法和被调用的方法都没加事务，则修改后都生效。
     * 2.被调用方法加了事务，调用方不加事务，无论哪里跑异常，修改都生效。
     * 3.都加事务：
     * [1]被调用方抛异常：
     * -- 调用方不捕获被调用方的异常，则修改都不生效。
     * -- 调用方捕获被调用的异常，且不抛出，则修改都生效。
     * -- 调用方捕获被调用方异常，且抛出的也是未检查异常，则修改都不生效。
     * [2]调用方抛异常，被调用方不抛异常
     * -- 调用方抛异常，被调用方不跑异常，则修改都不生效。
     * [3]3 个及其以上事务嵌套：
     * -- 第二层的事务抛异常，即使最外层不抛异常，也都生效。
     * -- 只有最内层抛异常，其他不抛异常，也都生效。
     * -- 只有最外层抛异常，则修改都不生效
     * <p>
     * ** 总结：凡是最外层事务抛出了unchecked 异常，则事务都不生效。其他层抛出都生效。
     * **       如果最外层是非事务的方法，则无论哪里抛异常，修改都会生效。
     *
     * @param student
     * @param newName
     * @return
     */

    @Transactional
    public Student defaultPropagation(Student student, String newName) throws Exception {
        LOG.info("事务执行前：{}", student);
        // 外层事务执行正常，内层事务抛异常
        int count = studentDao.insert(student);
        student.setName(newName);
        if (count == 1) {
            try {
                defaultInner(student);
            } catch (Exception e) {
                e.printStackTrace();
                // throw e; // 这是未检查的异常
                // throw new SQLException("checked 异常抛出。"); // 这是受检查的异常
                // throw new RuntimeException("unchecked 异常抛出");
            }
        }
        throw new RuntimeException("外部抛异常");
         // return student;
    }

    @Transactional
    public void defaultInner(Student student) throws Exception {
        LOG.info("参数: {}", student);
        student = studentDao.getById(19);
        LOG.info("第一次修改后：{}", student);
        int count = studentDao.update(student);
        // 更新完了抛异常
        if (count == 1) {
            try {
                inner(student);
            } catch (Exception e) {
                e.printStackTrace();
                // throw e;
            }
             throw new RuntimeException("this is default propagation.");
        }
    }

    /**
     * @param student
     * @throws Exception
     */
    @Transactional
    public void inner(Student student) throws Exception {
        // int count = studentDao.update("最内层事务", student.getId());
        int count = studentDao.insert(student);
        // 更新完了抛异常
        if (count == 1) {
            throw new RuntimeException("this is default propagation.");
        }
    }




}
