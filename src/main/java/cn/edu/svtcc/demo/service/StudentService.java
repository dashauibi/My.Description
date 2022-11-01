package cn.edu.svtcc.demo.service;

import cn.edu.svtcc.demo.model.StudentUser;
import cn.edu.svtcc.demo.pojo.CorpList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentService {
    public StudentUser selLogin(String st_username, String st_password);

    public Integer stuRegister(String st_name, String st_major, String st_tel, String st_username, String st_password, Integer role_id);

    public List<CorpList> selAllCorp();

    public List<CorpList> selCorp(String station, String type, String location);

    public int input(String me1, String em, String tel);

    public CorpList aloneCorp(Integer id);

    public int sendMessages(String real_name, String email, String information, String corp, String station);
}
