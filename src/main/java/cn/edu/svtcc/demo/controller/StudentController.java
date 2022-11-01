package cn.edu.svtcc.demo.controller;

import cn.edu.svtcc.demo.model.StudentList;
import cn.edu.svtcc.demo.model.StudentUser;
import cn.edu.svtcc.demo.pojo.CorpList;
import cn.edu.svtcc.demo.pojo.SelectList;
import cn.edu.svtcc.demo.service.Imp.StudentServiceImp;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Component
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentServiceImp stuServiceImp;

    //    返回首页
    @RequestMapping("/backMain")
    public String index() {
        return "studentIndex";
    }

    //    登录页面显示
    @RequestMapping("/showLogin")
    public String showLogin() {
        return "log-in";
    }

    //    注册页面
    @RequestMapping("/showRegister")
    public String showRegister() {
        return "register-in";
    }

    //登录验证
    @RequestMapping("/login")
    public String doLogin(StudentUser student, HttpSession session) {
        String username = student.getSt_username();
        String password = student.getSt_password();
        String userId = "欢迎您  :   " + username;
        session.setAttribute("msg", userId);
        int roles = student.getRole_id();
        System.out.println("role = " + roles);
        StudentUser studentList = stuServiceImp.selLogin(username, password);
        if (studentList != null) {
            return "studentIndex"; //再弄个新的首页
        }
        return "log-in";
    }

    //    注册
    @RequestMapping("/register")
    public String doRegister(StudentUser studentUser) {
        String st_name = studentUser.getSt_name();
        String st_major = studentUser.getSt_major();
        String st_tel = studentUser.getSt_tel();
        String st_username = studentUser.getSt_username();
        String st_password = studentUser.getSt_password();
        Integer role_id = studentUser.getRole_id();
        System.out.println("role_id = ==========" + role_id);
        stuServiceImp.stuRegister(st_name, st_major, st_tel, st_username, st_password, role_id);
        return "log-in";
    }

    //学生用户查看所有的企业
    @RequestMapping("selAllCorp")
    public String allCorp(Model model) {
        List<CorpList> list = stuServiceImp.selAllCorp();
        model.addAttribute("recruit", list);
        return "employers-listing";
    }

    //    学生模糊查找公司
    @RequestMapping("selCorp")
    public List<CorpList> doSel(@RequestBody SelectList list, HttpSession session) {
        String station = list.getSearch();
        String type = list.getType();
        String diZhi = list.getDiZhi();
        System.out.println("type = " + type);
        System.out.println("diZhi = " + diZhi);
        List<CorpList> corpLists = stuServiceImp.selCorp(station, type, diZhi);
        System.out.println("corpLists = " + corpLists);
        session.setAttribute("listOne", corpLists);
        return corpLists;
    }

    //    发送求职信息给企业
//    @RequestMapping("sendMessage")
//    public String send(Messages messages){
//         String message1=messages.getMessage1();
//         String email=messages.getEmail();
//         String tel=messages.getTel();
//         stuServiceImp.input(message1,email,tel);
//            return "employers-listing";
//    }
//模糊查询结果
    @RequestMapping("/aloneCorp")
    public String selectCorp(HttpSession session, Model model) throws Exception {
        List<CorpList> list = (List<CorpList>) session.getAttribute("listOne");
        model.addAttribute("fs", list);
        return "listing-select";
    }

    // 查看企业详情
    @RequestMapping("/lookDetails")
    public String look(HttpServletRequest request, Model model) {
        String sel = request.getQueryString();
        String[] sels = sel.split("=");
        Integer id = Integer.parseInt(sels[1]);
        CorpList list = stuServiceImp.aloneCorp(id);
        model.addAttribute("tt", list);
        String station = list.getStation();
        String corp = list.getCorp();

        request.getSession().setAttribute("corp", corp);
        request.getSession().setAttribute("station", station);
        System.out.println("station ============ " + corp);
        return "employers-details";
    }

    //学生发送信息给企业
    @RequestMapping("sendMessage")
    public String send(StudentList studentList, HttpServletRequest request) {
        String name = studentList.getRealName();
        String email = studentList.getEmail();
        String info = studentList.getInformation();
        String corp = request.getSession().getAttribute("corp").toString();
        String station = request.getSession().getAttribute("station").toString();
        stuServiceImp.sendMessages(name, email, info, corp, station);

        return "employers-listing";
    }
}
