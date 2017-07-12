package cn.com.senfun.account;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by leo on 12/07/2017.
 */
public interface AccountMapper {

    @Select("SELECT * FROM tb_user")
    List<Account> getAllUsers();

    @Select("SELECT * FROM tb_user WHERE username=#{username}")
    Account getByUsername(String username);

    @Insert("INSERT INTO tb_user(username, password, role, salt) VALUES (#{username}, #{password}, #{role}, #{salt}) ")
    void insertUser(Account account);
}
