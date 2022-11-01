package cn.edu.svtcc.demo.dao;

import cn.edu.svtcc.demo.model.CorporationUser;
import cn.edu.svtcc.demo.model.Information;
import cn.edu.svtcc.demo.model.StudentList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository

public interface CorpDao {
    @Insert("insert into examine(coName,coTel,coUsername,coPassword,roleId,coImages) values (#{coName},#{coTel},#{coUsername},#{coPassword},#{roleId},#{coImages})")
    public Integer corpRegister(@Param("coName") String coName, @Param("coTel") String coTel, @Param("coUsername") String coUsername, @Param("coPassword") String coPassword, @Param("roleId") Integer roleId, @Param("coImages") String coImages);

    @Select("select * from corporation where coUsername=#{coUsername} and coPassword=#{coPassword}")
    public CorporationUser doLogin(@Param("coUsername") String coUsername, @Param("coPassword") String coPassword);

    @Select("select * from st_list where corp=#{corp}")
    public List<StudentList> applicant(@Param("corp") String corp);

    @Select("select * from informations where email=#{email}")
    public Information selDetails(@Param("email") String email);

    @Select("select * from st_list where email=#{email}")
    public StudentList selPersonal(@Param("email") String email);

    @Insert("insert into list(station,corp,education,experience,location,vacancy,type,contentTwo,creatTime,email,tel,web) values (#{station},#{corp},#{education},#{experience},#{location},#{vacancy},#{type},#{contentTwo},#{creatTime},#{email},#{tel},#{web})")
    public Integer post(@Param("station") String station, @Param("corp") String corp, @Param("education") String education, @Param("experience") String experience, @Param("location") String location, @Param("vacancy") int vacancy, @Param("type") String type, @Param("contentTwo") String contentTwo, @Param("creatTime") Date creatTime, @Param("email") String email, @Param("tel") String tel, @Param("web") String web);
}