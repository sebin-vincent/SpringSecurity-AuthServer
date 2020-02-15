package com.example.springauth.repository;

import com.example.springauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public  User findByUserEmail(String userEmail);

    @Query(value="select pm.PREVILAGE_CODE from  user_table ut inner join" +
            " user_role ur on (ut.USER_ID=ur.USER_ID) INNER join" +
            " role_previlage rp on (rp.ROLE_ID=ur.ROLE_ID) inner join" +
            " previlage_master pm on (pm.PREVILAGE_ID=rp.PREVILAGE_ID) where ut.USER_EMAIL=:userEmail",nativeQuery = true)
    public List<String> getAuthorizations(@Param("userEmail") String userEmail);
}
