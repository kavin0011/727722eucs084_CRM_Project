package com.crmbank.crm.Repositary;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.crmbank.crm.Model.Bank;

@Repository
public interface BankRepositary extends JpaRepository<Bank, Integer> {

    @Query("select u from Bank u where u.balance> ?1")
    List<Bank> findBybalancegreaterthan(int balance, int pgno, int pgsize, Pageable pg);

    @Modifying
    @Query("update Bank u set u.email =?1 where u.id = ?2 ")
    void updateemail(String newmail, int oldmail);
}
