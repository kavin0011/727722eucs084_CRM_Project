package com.crmbank.crm.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.crmbank.crm.Model.Bank;
import com.crmbank.crm.Model.BankUser;
import com.crmbank.crm.Model.DifferentAcc;
import com.crmbank.crm.Repositary.BankRepositary;
import com.crmbank.crm.Repositary.BankUserRepository;
import com.crmbank.crm.Repositary.DifferentAccRepositary;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

@Service
public class BankService {
    @Autowired
    public BankRepositary bankRepositary;

    @Autowired
    public BankUserRepository bankUserRepository;

    @Autowired
    DifferentAccRepositary differentAccRepositary;

    public Bank savedata(Bank bank) {
        return bankRepositary.save(bank);
    }

    public Bank getspecificedata(int id) {
        return bankRepositary.findById(id).orElse(null);
    }

    public List<Bank> getalldata(int pgno, int pgsize, String columnname) {
        List<Bank> l = bankRepositary.findAll(PageRequest.of(pgno, pgsize, Sort.by(Direction.DESC, columnname)))
                .getContent();
        return l;
    }

    public void deletespecificid(int id) {
        bankRepositary.deleteById(id);
        return;
    }

    public List<Bank> pageableoutput(int balance, int pgno, int pgsize, Pageable pg) {
        return bankRepositary.findBybalancegreaterthan(balance, pgno, pgsize, pg);
    }

    public void updateemailbyjpa(String newmail, int id) {
        // bankRepositary.updateemail(newmail, id);
        // // return;
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("id");
        // EntityManager em = emf.createEntityManager();
        // Query q = em.createQuery("update Bank u set u.email ='karthi@gmail.com' where
        // u.id = 1 ");
        // int deleted = q.executeUpdate();
        // System.out.print(deleted);
    }

    public BankUser savearea(BankUser bankUser) {
        return bankUserRepository.save(bankUser);
    }

    public DifferentAcc saveaccdata(DifferentAcc dAcc) {
        return differentAccRepositary.save(dAcc);
    }

    public void deleteonetoone(int id) {
        differentAccRepositary.deleteById(id);
        return;
    }
}
