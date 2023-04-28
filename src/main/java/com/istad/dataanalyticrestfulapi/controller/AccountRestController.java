package com.istad.dataanalyticrestfulapi.controller;

import com.istad.dataanalyticrestfulapi.mapper.AutoAccountMapper;
import com.istad.dataanalyticrestfulapi.model.Account;
import com.istad.dataanalyticrestfulapi.model.response.AccountResponse;
import com.istad.dataanalyticrestfulapi.service.AccountService;
import com.istad.dataanalyticrestfulapi.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/accounts")
public class AccountRestController {
    final private AccountService accountService;
    final private AutoAccountMapper autoAccountMapper;
    AccountRestController (AccountService accountService, AutoAccountMapper autoAccountMapper){
        this.accountService = accountService;
        this.autoAccountMapper = autoAccountMapper;
    }
    @GetMapping("/all-accounts")
    public Response<List<AccountResponse>> getAllAccount(){
        try {
            List<Account> allAccounts = accountService.getAllAccounts();
            List<AccountResponse> accountResponses = autoAccountMapper.mapToAccountResponse(allAccounts);
            return Response.<List<AccountResponse>>ok().setPayload(accountResponses).setMessage("Successfully retrieved all account's information");

        } catch (Exception exception){
            System.out.println("Something is wrong : "+ exception.getMessage());
            return Response.<List<AccountResponse>>exception().setMessage("Exception occurs! Fail to retrieve datas.");
        }

    }

}