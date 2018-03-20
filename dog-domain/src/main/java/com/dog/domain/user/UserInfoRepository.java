package com.dog.domain.user;

import com.dog.beans.login.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
// https://spring.io/guides/gs/accessing-data-jpa/
public interface UserInfoRepository extends JpaRepository<UserBean, String>,
        CrudRepository<UserBean, String> {

//    @Transactional
    /*
    * 使用jpa自定义sql查询时，此时from后面的表名应为Entity对应定义的bean类名称，不应该是真正的表名，
    * 例如：表名users对应映射的bean为UserBean，故@Query注解后面应使用bean类不应该是表名了！
    * 否则springboot启动会报IllegalArgumentException
     */

    @Query("select u from UserBean u where u.name=?1 and u.password=?2")
    UserBean withNamePasswordQuery(@Param("name") String name, @Param("password") String password);

//    @Query("select p from UserBean p where p.id=?1")
//    UserBean getUserById(String id);
}
