package cn.edu.svtcc.demo.service;

import cn.edu.svtcc.demo.model.CorporationUser;
import cn.edu.svtcc.demo.model.Information;
import cn.edu.svtcc.demo.model.StudentList;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

public interface CorpService {
    public Integer corpRegister(String coName, String coTel, String coUsername, String coPassword, Integer roleId, String coImages);

    public CorporationUser doLogin(String co_username, String co_password);

    public List<StudentList> applicant(String corp);

    public Information selDetails(String email);

    public StudentList selPersonal(String email);

    public Integer post(String station, String corp, String education, String experience, String location, int vacancy, String type, String contentTwo, Date creatTime, String email, String tel, String web);
}
