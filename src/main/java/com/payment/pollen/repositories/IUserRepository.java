package com.payment.pollen.repositories;

import com.payment.pollen.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends JpaRepository<User, String>
{
    @Query(value = "SELECT true FROM User u WHERE u.id =:id")
    public Boolean checkIfUserExistsFromId(@Param("id") long id);

    @Query(value = "SELECT password FROM User u WHERE u.email =:email")
    public String getPasswordFromEmail(@Param("email") String email);
}
