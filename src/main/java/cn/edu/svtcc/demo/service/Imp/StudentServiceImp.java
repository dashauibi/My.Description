package cn.edu.svtcc.demo.service.Imp;

import cn.edu.svtcc.demo.dao.StudentDao;
import cn.edu.svtcc.demo.model.StudentUser;
import cn.edu.svtcc.demo.pojo.CorpList;
import cn.edu.svtcc.demo.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
//@Transactional
public class StudentServiceImp implements StudentService {
    @Resource
    private StudentDao studentDao;

    @Override
    public StudentUser selLogin(String st_username, String st_password) {
        StudentUser studentList = studentDao.selLogin(st_username, st_password);
        return studentList;
    }

    @Override
    public Integer stuRegister(String st_name, String st_major, String st_tel, String st_username, String st_password, Integer role_id) {
        return studentDao.stuRegister(st_name, st_major, st_tel, st_username, st_password, role_id);
    }

    @Override
    public List<CorpList> selAllCorp() {
        return studentDao.selAllCorp();
    }

    @Override
    public List<CorpList> selCorp(String station, String type, String location) {
        return studentDao.selCorp(station, type, location);
    }

    @Override
    public int input(String me1, String em, String tel) {

        return 1;
    }

    @Override
    public CorpList aloneCorp(Integer id) {
        return studentDao.aloneCorp(id);
    }

    @Override
    public int sendMessages(String real_name, String email, String information, String corp, String station) {
        return studentDao.sendMessages(real_name, email, information, corp, station);
    }

}
