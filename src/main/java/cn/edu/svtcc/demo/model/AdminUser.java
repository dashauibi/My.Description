package cn.edu.svtcc.demo.model;

import lombok.Data;

@Data
public class AdminUser {
    private Integer id;
    private String account;
    private String password;
    private String name;
    private String tel;
    private String co_username;
    private String st_username;
    private int role_id;
}
