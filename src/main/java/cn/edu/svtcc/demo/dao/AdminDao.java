package cn.edu.svtcc.demo.dao;

import cn.edu.svtcc.demo.model.AdminUser;
import cn.edu.svtcc.demo.model.CorporationUser;
import cn.edu.svtcc.demo.model.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *管理员操作
 *
 *
 * */
@Repository
public interface AdminDao {
    //    管理员登录账号密码验证
    @Select("select * from administrator where account=#{account} and password=#{password}")
    public AdminUser selAccount(@Param("account") String account, @Param("password") String password);

    @Select("select * from student")
    public List<Student> selStudents();

    @Delete("delete from student where id=#{id}")
    public int stuDelete(Integer id);

    //    通过id把编辑前的信息显示在create.html
    @Select("select * from student where id=#{id}")
    public Student sel1(Integer id);

    //    管理员更新学生用户信息
    @Update("update student set stName=#{stName},stMajor=#{stMajor},stTel=#{stTel},stUsername=#{stUsername},stPassword=#{stPassword} where id=#{id}")
    public Integer upStu(@Param("id") Integer id, @Param("stName") String stName, @Param("stMajor") String stMajor, @Param("stTel") String stTel, @Param("stUsername") String stUsername, @Param("stPassword") String stPassword);

    //单独查询一个学生用户
    @Select("select * from student where stUsername=#{stUsername}")
    public List<Student> aloneSel(String stUsername);

    @Select("select * from corporation")
    public List<CorporationUser> selCorp();

    //    根据传过来的id查出该公司的信息
    @Select("select * from corporation where id=#{id}")
    public CorporationUser corpSel(Integer id);

    //    修改公司信息
    @Update("update corporation set coName=#{coName},coTel=#{coTel},coImages=#{coImages},coUsername=#{coUsername},coPassword=#{coPassword} where id=#{id}")
    public Integer upCorp(@Param("id") Integer id, @Param("coName") String coName, @Param("coTel") String coTel, @Param("coImages") String coImages, @Param("coUsername") String coUsername, @Param("coPassword") String coPassword);

    @Delete("delete from corporation where id=#{id}")
    public int corpDelete(Integer id);

    //    根据账号查找管理员
    @Select("select * from administrator where account=#{account}")
    public AdminUser findAccount(@Param("account") String account);

    @Select("select * from examine")
    public List<CorporationUser> examineCorp();

    @Select("select * from examine where id=#{id}")
    public CorporationUser searchCorp(Integer id);

    @Insert("insert into corporation(coName,coTel,coUsername,coPassword,roleId,coImages) values (#{coName},#{coTel},#{coUsername},#{coPassword},#{roleId},#{coImages})")
    public Integer corpRegister(@Param("coName") String coName, @Param("coTel") String coTel, @Param("coUsername") String coUsername, @Param("coPassword") String coPassword, @Param("roleId") Integer roleId, @Param("coImages") String coImages);

    @Delete("delete from examine where id=#{id}")
    public Integer delExamine(Integer id);

}
