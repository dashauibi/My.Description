package cn.edu.svtcc.demo.service.Imp;

import cn.edu.svtcc.demo.dao.CorpDao;
import cn.edu.svtcc.demo.model.CorporationUser;
import cn.edu.svtcc.demo.model.Information;
import cn.edu.svtcc.demo.model.StudentList;
import cn.edu.svtcc.demo.service.CorpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@Service
//@Transactional
public class CorpServiceImp implements CorpService {
    @Resource
    private CorpDao corpDao;

    @Override
    public Integer corpRegister(String coName, String coTel, String coUsername, String coPassword, Integer roleId, String coImages) {
        return corpDao.corpRegister(coName, coTel, coUsername, coPassword, roleId, coImages);
    }

    @Override
    public CorporationUser doLogin(String co_username, String co_password) {
        return corpDao.doLogin(co_username, co_password);
    }

    @Override
    public List<StudentList> applicant(String corp) {
        return corpDao.applicant(corp);
    }

    @Override
    public Information selDetails(String email) {
        return corpDao.selDetails(email);
    }

    @Override
    public StudentList selPersonal(String email) {
        return corpDao.selPersonal(email);
    }

    @Override
    public Integer post(String station, String corp, String education, String experience, String location, int vacancy, String type, String contentTwo, Date creatTime, String email, String tel, String web) {
        return corpDao.post(station, corp, education, experience, location, vacancy, type, contentTwo, creatTime, email, tel, web);
    }
}
