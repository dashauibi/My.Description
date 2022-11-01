package cn.edu.svtcc.demo.controller;

import cn.edu.svtcc.demo.dao.AdminDao;
import cn.edu.svtcc.demo.model.AdminUser;
import cn.edu.svtcc.demo.model.CorporationUser;
import cn.edu.svtcc.demo.model.Student;
import cn.edu.svtcc.demo.model.StudentUser;
import cn.edu.svtcc.demo.pojo.Corporations;
import cn.edu.svtcc.demo.pojo.Students;
import cn.edu.svtcc.demo.service.Imp.AdminServiceImp;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 作者:谭亮
 * 班级:软件 19-4
 * 时间:2021.10.26
 */

@Controller
@Component
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminServiceImp adminServiceImp;

    @RequestMapping("/springboot")
    public String index() {
        return "index";
    }

    @GetMapping("/main")
    public String handleRequest2() {
        //主页控制台数据显示
        return "index2";
    }


    //管理员登录验证
    @RequestMapping("/admireLogin")
    @ResponseBody
    public AdminUser login(@RequestBody AdminUser adminUser, HttpServletRequest request) {
        String account = adminUser.getAccount();
        String password = adminUser.getPassword();
        AdminUser admire = adminServiceImp.selAccount(account, password);
        if (admire != null) {
            System.out.println("登录成功");
            return admire;
        }
        return null;
    }

    //查看所有学生用户
    @RequestMapping("/selStu")
    public String stu(Model model) {
        List<Student> studentUser = adminServiceImp.selStudents();
        model.addAttribute("st", studentUser);
        System.out.println("studentUser = " + studentUser);
        return "admin";
    }

    //管理删除学生用户
    @RequestMapping("/delStu")
    public String delStu(HttpServletRequest request) {
        String del = request.getQueryString();
        String[] dels = del.split("=");
        int one = adminServiceImp.stuDelete(Integer.parseInt(dels[1]));
        return "forward:/selStu";
    }

    //将数据库查到的学生信息数据库信息放到更新页面上
    @RequestMapping("/upStu")
    public String upStu(HttpServletRequest request, Model model) {
        String sel = request.getQueryString();

        String[] sels = sel.split("=");
        Student studentUser = adminServiceImp.sel1(Integer.parseInt(sels[1]));
        //    int one=admireServiceImp.stuDelete(Integer.parseInt(dels[1]));

        request.getSession().setAttribute("ids", studentUser.getId());
        model.addAttribute("id", studentUser);
        return "updataStu";
    }

    //更新修改后的数据
    @RequestMapping("/upStu1")
    public String upNews(HttpServletRequest request, Students students) {
        String id = request.getSession().getAttribute("ids").toString();
        System.out.println("id = " + id);
        int ids = Integer.parseInt(id);
        String turename = students.getTitle();
        System.out.println("turename====== " + turename);
        String major = students.getMajor();
        String tel = students.getTel();
        String username = students.getUsername();
        String password = students.getPassword();
        adminServiceImp.upStu(ids, turename, major, tel, username, password);
        return "forward:/admin/selStu";
    }

    //管理员精确查找用户信息
    @RequestMapping("/selAlone")
    public String stuQuery(StudentUser user, Model model) {
        String username = user.getSt_username();
        System.out.println("username ======== " + username);
        List<Student> users = adminServiceImp.aloneSel(username);
        model.addAttribute("gg", users);
        return "aloneSelect";
    }

    //管理员查看所有企业信息
    @RequestMapping("/searchCorp")
    public String searchCorp(Model model) {
        List<CorporationUser> contentUser = adminServiceImp.selCorp();
        model.addAttribute("corpMessage", contentUser);
        return "corp";
    }

    //查找需要更新的显示的数据
    @RequestMapping("/upCorp")
    public String upCorp(HttpServletRequest request, Model model) {
        String sel = request.getQueryString();
        String[] sels = sel.split("=");
        CorporationUser contentUser = adminServiceImp.corpSel(Integer.parseInt(sels[1]));
        //    int one=admireServiceImp.stuDelete(Integer.parseInt(dels[1]));
        request.getSession().setAttribute("corpIds", contentUser.getId());
        System.out.println("contentUser = " + sels[1]);
        model.addAttribute("corpId", contentUser);
        return "updateCorp";
    }


    // 更新修改过后的数据
    @RequestMapping("/upCorp2")
    public String udCorp2(Corporations corporations, HttpServletRequest request) {
        String co_name = corporations.getCo_name();
        String co_tel = corporations.getCo_tel();
        String co_Requirement = corporations.getCo_Requirement();
        String co_username = corporations.getCo_username();
        String co_password = corporations.getCo_password();
        String ids = request.getSession().getAttribute("corpIds").toString();
        int id = Integer.parseInt(ids);
        adminServiceImp.upCorp(id, co_name, co_tel, co_Requirement, co_username, co_password);
        return "corp";
    }

    //删除公司
    @RequestMapping("/delCorp")
    public String delCorporations(HttpServletRequest request) {
        String del = request.getQueryString();
        String[] dels = del.split("=");
        int two = adminServiceImp.corpDelete(Integer.parseInt(dels[1]));
        return "forward:/searchCorp";
    }

    /**
     * 企业信息审核
     */

    //信息展示
    @RequestMapping("/showExamine")
    public String showExamine(Model model) {
        List<CorporationUser> list = adminServiceImp.examineCorp();
        model.addAttribute("list", list);
        return "examine";
    }

    //审批
    @RequestMapping("/agree")
    public String agree(HttpServletRequest request) {
        String del = request.getQueryString();
        String[] dels = del.split("=");
        //先把同意的公司写进corporation表
        CorporationUser corp = adminServiceImp.searchCorp(Integer.parseInt(dels[1]));
        String coName = corp.getCoName();
        String coTel = corp.getCoTel();
        String coImages = corp.getCoImages();
        String coUsername = corp.getCoUsername();
        String coPassword = corp.getCoPassword();
        Integer roleId = corp.getRoleId();
        adminServiceImp.corpRegister(coName, coTel, coUsername, coPassword, roleId, coImages);
        //从examine表中删除数据
        int two = adminServiceImp.delExamine(Integer.parseInt(dels[1]));
        return "redirect:showExamine";
    }
}
