package com.istad.dataanalyticrestfulapi.mapper;

import com.istad.dataanalyticrestfulapi.model.Account;
import com.istad.dataanalyticrestfulapi.model.response.AccountResponse;
//import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper (componentModel = "spring")
public interface AutoAccountMapper {
    // account to account response
    List<AccountResponse> mapToAccountResponse(List<Account> accounts);
    // account response to account
    List<Account> mapToAccount(List<AccountResponse> accountResponses);

}
