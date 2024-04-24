package com.crmbank.crm.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.crmbank.crm.Model.Bank;
import com.crmbank.crm.Model.BankUser;
import com.crmbank.crm.Model.DifferentAcc;
import com.crmbank.crm.Service.BankService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class BankController {

    @Autowired
    public BankService bankService;

    @PostMapping("/postdata")
    public ResponseEntity<String> postdata(@RequestBody Bank bank) {
        bankService.savedata(bank);
        return new ResponseEntity<>("Value Stored Succesfully", HttpStatus.ACCEPTED);
    }

    @ResponseStatus(value = HttpStatus.FOUND)
    @GetMapping("/getspecificdata")
    public Bank getspecificdata(@RequestParam int id) {
        return bankService.getspecificedata(id);
    }

    @GetMapping("/getalldata")
    public List<Bank> getAllData(@RequestParam int pgno, @RequestParam int pgsize, @RequestParam String columnname) {
        return bankService.getalldata(pgno, pgsize, columnname);
    }

    @PutMapping("/updatedata")
    public ResponseEntity<String> updatethedata(@RequestParam int id, @RequestParam String email) {
        Bank olddata = bankService.getspecificedata(id);
        if (olddata != null) {
            olddata.setEmail(email);
            bankService.savedata(olddata);
            return new ResponseEntity<>("Email updated succesfully", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Value Not Found", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletethedata(@RequestParam int id) {
        bankService.deletespecificid(id);
        return new ResponseEntity<>("Data deleted Succesfully", HttpStatus.FOUND);
    }

    @GetMapping("/pagablerequest")
    public List<Bank> getMethodName(@RequestParam int pgno, @RequestParam int pgsize, @RequestParam String column,
            @RequestParam int balance) {
        PageRequest pg = PageRequest.of(pgno, pgsize, Sort.by(Direction.DESC, column));
        return bankService.pageableoutput(balance, pgno, pgsize, pg);
    }

    @PutMapping("/updatebyjpa")
    public ResponseEntity<String> updateusingjpamethod(@RequestParam String newmail, @RequestParam int id) {
        // Bank bank = bankService.getspecificedata(id);
        // if (bank != null)
        bankService.updateemailbyjpa(newmail, id);
        return new ResponseEntity<>("email updated using JPA methos",
                HttpStatus.CREATED);
    }

    @PostMapping("/postarea")
    public BankUser savearea(@RequestBody BankUser bankUser) {
        return bankService.savearea(bankUser);
    }

    @PostMapping("/differentacc")
    public String postMethodName(@RequestBody DifferentAcc data) {
        bankService.saveaccdata(data);
        return "Value added";
    }

    @DeleteMapping("/deletefromdifferent")
    public String DeletefromDifferentacc(@RequestParam int id) {
        bankService.deleteonetoone(id);
        return "deleted";
    }
}
