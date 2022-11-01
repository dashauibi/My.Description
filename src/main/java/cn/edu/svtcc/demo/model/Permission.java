package cn.edu.svtcc.demo.model;

import lombok.Data;

@Data
public class Permission {
    private Integer id;
    private String permissionname;
    private int role_id;
}
