package cn.edu.svtcc.demo.service.Imp;

import cn.edu.svtcc.demo.dao.AdminDao;
import cn.edu.svtcc.demo.dao.PermissionDao;
import cn.edu.svtcc.demo.dao.RoleDao;
import cn.edu.svtcc.demo.model.*;
import cn.edu.svtcc.demo.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
       service层写的方法里面的参数与controller相同，

 */

@Service
//@Transactional
public class AdminServiceImp implements AdminService {
    @Resource
    private AdminDao adminDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private PermissionDao permissionDao;

    @Override
    public AdminUser selAccount(String account, String password) {
        AdminUser adminUser = adminDao.selAccount(account, password);
        return adminUser;
    }

    @Override
    public List<Student> selStudents() {
        List<Student> students = adminDao.selStudents();
        return students;
    }

    @Override
    public int stuDelete(Integer id) {
        adminDao.stuDelete(id);
        return 1;
    }

    @Override
    public Student sel1(Integer id) {
        return adminDao.sel1(id);
    }

    @Override
    public Integer upStu(Integer id, String name, String major, String tel, String username, String password) {
        return adminDao.upStu(id, name, major, tel, username, password);
    }

    @Override
    public List<Student> aloneSel(String st_username) {

        return adminDao.aloneSel(st_username);
    }

    @Override
    public List<CorporationUser> selCorp() {

        return adminDao.selCorp();
    }

    @Override
    public CorporationUser corpSel(Integer id) {
        return adminDao.corpSel(id);
    }

    @Override
    public Integer upCorp(Integer id, String co_name, String co_tel, String co_Requirement, String co_username, String co_password) {
        return adminDao.upCorp(id, co_name, co_tel, co_Requirement, co_username, co_password);
    }

    @Override
    public int corpDelete(Integer id) {
        adminDao.corpDelete(id);
        return 0;
    }

    @Override
    public Role getRoles(String rolename) {
        return roleDao.getRoles(rolename);
    }

    @Override
    public Permission getPermission(String permissionname) {
        return permissionDao.getPermissions(permissionname);
    }

    @Override
    public AdminUser findAccount(String account) {
        return adminDao.findAccount(account);
    }

    @Override
    public List<CorporationUser> examineCorp() {
        return adminDao.examineCorp();
    }

    @Override
    public Integer corpRegister(String co_name, String co_tel, String co_username, String co_password, Integer role_id, String co_images) {
        return adminDao.corpRegister(co_name, co_tel, co_username, co_password, role_id, co_images);
    }

    @Override
    public CorporationUser searchCorp(Integer id) {
        return adminDao.searchCorp(id);
    }

    @Override
    public Integer delExamine(Integer co_username) {
        return adminDao.delExamine(co_username);
    }
}
