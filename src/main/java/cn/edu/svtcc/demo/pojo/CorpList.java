package cn.edu.svtcc.demo.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Data
@Component
public class CorpList {
    private Integer id;
    private String station;
    private String corp;
    private String education;
    private String experience;
    private String location;
    private Integer vacancy;
    private String level;
    private String type;
    private String contentOne;
    private String contentTwo;
    private String contentThree;
    private Date creatTime;
    private String position;
    private String email;
    private String web;
    private String tel;
    private String images;
}
