package cn.edu.svtcc.demo.model;

import lombok.Data;

@Data
public class CorporationUser {

    private Integer id;
    private String coName;
    private String coTel;
    private String coImages;
    private String coUsername;
    private String coPassword;
    private Integer roleId;
    private String rdpassword;


}
