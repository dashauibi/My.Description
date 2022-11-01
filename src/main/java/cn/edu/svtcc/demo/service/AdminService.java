package cn.edu.svtcc.demo.service;


import cn.edu.svtcc.demo.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminService {

    public AdminUser findAccount(String account);

    public AdminUser selAccount(String account, String password);

    public List<Student> selStudents();

    public int stuDelete(Integer id);

    public Student sel1(Integer id);

    public Integer upStu(Integer id, String name, String major, String tel, String username, String password);

    public List<Student> aloneSel(String st_username);

    public List<CorporationUser> selCorp();

    public CorporationUser corpSel(Integer id);

    public Integer upCorp(Integer id, String co_name, String co_tel, String co_Requirement, String co_username, String co_password);

    public int corpDelete(Integer id);

    public Role getRoles(@Param("rolename") String rolename);

    public Permission getPermission(@Param("permissionname") String permissionname);

    public List<CorporationUser> examineCorp();

    public Integer corpRegister(String co_name, String co_tel, String co_username, String co_password, Integer role_id, String co_images);

    public Integer delExamine(Integer co_username);

    public CorporationUser searchCorp(Integer id);
}
