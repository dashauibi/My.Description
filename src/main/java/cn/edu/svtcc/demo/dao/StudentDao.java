package cn.edu.svtcc.demo.dao;

import cn.edu.svtcc.demo.model.StudentUser;
import cn.edu.svtcc.demo.pojo.CorpList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {
    @Select("select * from student where stUsername=#{stUsername} and stPassword=#{stPassword}")
    public StudentUser selLogin(@Param("stUsername") String stUsername, @Param("stPassword") String stPassword);

    @Insert("insert into student(stName,stMajor,stTel,stUsername,stPassword,roleId) values (#{stName},#{stMajor},#{stTel},#{stUsername},#{stPassword},#{roleId})")
    public Integer stuRegister(@Param("stName") String stName, @Param("stMajor") String stMajor, @Param("stTel") String stTel, @Param("stUsername") String stUsername, @Param("stPassword") String stPassword, @Param("roleId") Integer roleId);

    @Select("select * from list")
    public List<CorpList> selAllCorp();

    //    通过id查询公司详细信息
    @Select("select * from list where id=#{id}")
    public CorpList aloneCorp(Integer id);

    //    学生用户对企业的模糊查询
    public List<CorpList> selCorp(@Param("station") String station, @Param("type") String type, @Param("location") String location);

    @Insert("insert into st_list(real_name,email,information,corp,station) values (#{real_name},#{email},#{information},#{corp},#{station})")
    public int sendMessages(@Param("real_name") String real_name, @Param("email") String email, @Param("information") String information, @Param("corp") String corp, @Param("station") String station);
}

