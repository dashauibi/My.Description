package cn.edu.svtcc.demo.model;

import lombok.Data;

@Data
public class StudentUser {
    private Integer id;
    private String st_name;
    private String st_major;
    private String st_tel;
    private String st_username;
    private String st_password;
    private int role_id;
}
