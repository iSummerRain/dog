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
//    @Query("select p from users p where p.name=:name and p.age=:age")
//    UserBean withNameAndAgeQuery(@Param("name") String name, @Param("age") String address);

//    @Query("select p from users p where p.id=:id")
//    UserBean getUserById(String id);
}
