package cn.edu.svtcc.demo.controller;

import cn.edu.svtcc.demo.model.CorporationUser;
import cn.edu.svtcc.demo.model.Information;
import cn.edu.svtcc.demo.model.StudentList;
import cn.edu.svtcc.demo.pojo.CorpList;
import cn.edu.svtcc.demo.service.Imp.CorpServiceImp;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.util.List;

@Controller
@Component
@RequestMapping("/corporation")
public class CorporationController {
    @Resource
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private CorpServiceImp corpServiceImp;

    @RequestMapping("/showLogin2")
    public String showlogin() {
        return "corp-log-in";
    }

    @RequestMapping("/showRegister2")
    public String showRegister2() {
        return "corp-register-in";
    }

    //文件上传路径
    @Value("${file.upload.path}")
    private String path;

    //    企业用户注册
    @RequestMapping("/register")
    public String doRegister(@RequestPart MultipartFile file, CorporationUser corporationUser) throws IOException {
        String co_name = corporationUser.getCoName();
        String co_tel = corporationUser.getCoTel();
        String co_username = corporationUser.getCoUsername();
        String co_password = corporationUser.getCoPassword();
        Integer role_id = corporationUser.getRoleId();
        String fileName = file.getOriginalFilename();
        String filePath = path + fileName;
        File dest = new File(filePath);
        Files.copy(file.getInputStream(), dest.toPath());
        String[] sels = dest.toPath().toString().split("resources");
        String imagesPath = ".." + sels[1];
        corpServiceImp.corpRegister(co_name, co_tel, co_username, co_password, role_id, imagesPath);
        return "corp-register-in";
    }

    //    登录验证
    @RequestMapping("/login")
    public String doLogin(CorporationUser corporationUser, Model model, HttpServletRequest request) throws Exception {
        String username = corporationUser.getCoUsername();
        String password = corporationUser.getCoPassword();
        CorporationUser corporationUser1 = corpServiceImp.doLogin(username, password);
        try {
            String corp = corporationUser1.getCoName();
            String name = corporationUser1.getCoUsername();
            request.getSession().setAttribute("corp1", corp);
            request.getSession().setAttribute("name", name);
        } catch (NullPointerException e) {
            System.out.println("密码错误");
        }
        if (corporationUser1 == null) {
//            String error="账号或者密码错误";
//            model.addAttribute("error",error);
            return "corp-log-in";
        } else {
            return "userIndex";
        }
    }


    //    返回主页
    @RequestMapping("/backMain")
    public String index() {
        return "userIndex";
    }

    //发帖主页
    @RequestMapping("/posting")
    public String postingShow() {
        return "post";
    }

    //    发帖
    @ResponseBody
    @RequestMapping("/accept")
    public CorpList posting(@RequestBody CorpList list, HttpServletRequest request) {
        String station = list.getStation();
        String location = list.getLocation();
        String type = list.getType();
        int vacancy = list.getVacancy();
        Date date = list.getCreatTime();
        String contentTwo = list.getContentTwo();
        String tel = list.getTel();
        String email = list.getEmail();
        String experience = list.getExperience();
        String web = list.getWeb();
        String corp = request.getSession().getAttribute("corp1").toString();
        String education = list.getEducation();
        System.out.println("date = " + corp);
        corpServiceImp.post(station, corp, education, experience, location, vacancy, type, contentTwo, date, email, tel, web);
        return list;
    }

    //    查看求职人员
    @RequestMapping("/applicant")
    public String students(HttpServletRequest request, Model model) {
        String corp = request.getSession().getAttribute("corp1").toString();
        List<StudentList> list = corpServiceImp.applicant(corp);
        System.out.println("list =========== " + list);
        model.addAttribute("message1", list);
        return "candidates-listing";
    }

    //        个人信息详情
    @RequestMapping("details")
    public String lookDetails(HttpServletRequest request, Model model) {
        String sel = request.getQueryString();
        String[] emails = sel.split("=");
        String email = emails[1];
        System.out.println("email ============= " + email);
        Information information = corpServiceImp.selDetails(email);
        StudentList studentList = corpServiceImp.selPersonal(email);
        model.addAttribute("personal", studentList);
        model.addAttribute("details", information);
        return "candidates-details";
    }
}
