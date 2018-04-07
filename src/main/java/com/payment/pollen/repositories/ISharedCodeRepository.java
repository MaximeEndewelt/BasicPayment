package com.payment.pollen.repositories;

import com.payment.pollen.entities.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISharedCodeRepository  extends JpaRepository<Code, Long>
{
    @Query(value = "SELECT * FROM Code c WHERE c.recipient_email =:recipientEmail", nativeQuery = true)
    public List<Code> getAllByRecipientEmail(@Param("recipientEmail") String recipientEmail);
}
