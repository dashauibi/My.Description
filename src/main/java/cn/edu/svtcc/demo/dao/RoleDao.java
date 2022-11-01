package cn.edu.svtcc.demo.dao;

import cn.edu.svtcc.demo.model.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao {
    @Select("select * from t_new_role where rolename=#{rolename}")
    public Role getRoles(@Param("rolename") String rolename);
}
