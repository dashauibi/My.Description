package cn.edu.svtcc.demo.dao;

import cn.edu.svtcc.demo.model.Permission;
import cn.edu.svtcc.demo.model.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDao {
    @Select("select * from t_permission where rolename=#{rolename}")
    public Permission getPermissions(@Param("permissionname") String permissionname);

}
