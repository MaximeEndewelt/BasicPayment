package com.payment.pollen.repositories;

import com.payment.pollen.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBankRepository extends JpaRepository<BankAccount, Long>
{

}
