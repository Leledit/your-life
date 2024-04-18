package com.yourlife.your.life.service.finance.impl;

import com.yourlife.your.life.model.dto.finance.FinanceFixedAccountDTO;
import com.yourlife.your.life.model.entity.finance.FixedAccount;
import com.yourlife.your.life.model.entity.user.User;
import com.yourlife.your.life.model.entity.user.UserAuth;
import com.yourlife.your.life.model.vo.finance.FinanceChangingFixedAccountVO;
import com.yourlife.your.life.model.vo.finance.FinanceRegisterFixedAccountVO;
import com.yourlife.your.life.repository.finance.FixedAccountRepository;
import com.yourlife.your.life.service.finance.FixedAccountService;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FixedAccountServiceImpl implements FixedAccountService {

    @Autowired
    private FixedAccountRepository fixedAccountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FinanceFixedAccountDTO createdFixedAccount(FinanceRegisterFixedAccountVO financeRegisterFixedAccountVO) {

        FixedAccount fixedAccount = modelMapper.map(financeRegisterFixedAccountVO, FixedAccount.class);

        User user = returnUserCorrespondingToTheRequest();

        fixedAccount.setUser(user);

        FixedAccount fixedAccountSalve =  fixedAccountRepository.save(fixedAccount);

        return  modelMapper.map(fixedAccountSalve,FinanceFixedAccountDTO.class);
    }

    @Override
    public ArrayList<FinanceFixedAccountDTO> returnRegisteredFixedAccounts() {

        User user = returnUserCorrespondingToTheRequest();

        Optional<ArrayList<FixedAccount>> fixedAccountOptional = fixedAccountRepository.findAllByUser_Id(user.getId());

        List<FixedAccount> listFixedAccount = new ArrayList<>();

        fixedAccountOptional.ifPresent(listFixedAccount::addAll);

        ArrayList<FinanceFixedAccountDTO> financeFixedAccountDTOS = new ArrayList<>();
        listFixedAccount.forEach(fixedAccount ->
            financeFixedAccountDTOS.add(modelMapper.map(fixedAccount,FinanceFixedAccountDTO.class))
        );

        return financeFixedAccountDTOS;
    }

    @Override
    public FinanceFixedAccountDTO returningAFixedAccountById(String id) {

        Optional<FixedAccount> fixedAccount = fixedAccountRepository.findById(id);

        if(fixedAccount.isEmpty()){
            throw new RuntimeException("Nenhuma conta foi encontrada!");
        }

        return modelMapper.map(fixedAccount.get(),FinanceFixedAccountDTO.class);
    }

    @Override
    public FinanceFixedAccountDTO changingFixedAccount(FinanceChangingFixedAccountVO financeChangingFixedAccountVO) {

        Optional<FixedAccount> fixedAccountOptional = fixedAccountRepository.findById(financeChangingFixedAccountVO.getId());

        if(fixedAccountOptional.isEmpty()){
            throw new RuntimeException("O id informado é invalido");
        }

        FixedAccount fixedAccount = fixedAccountOptional.get();

        if(StringUtils.isNotBlank(financeChangingFixedAccountVO.getName())){
            fixedAccount.setName(financeChangingFixedAccountVO.getName());
        }

        if(financeChangingFixedAccountVO.getValue() != null && financeChangingFixedAccountVO.getValue().intValue() != 0) {
            fixedAccount.setValue(financeChangingFixedAccountVO.getValue());
        }

        if(StringUtils.isNotBlank(financeChangingFixedAccountVO.getDescription())){
            fixedAccount.setDescription(financeChangingFixedAccountVO.getDescription());
        }

        if(financeChangingFixedAccountVO.getDueDate() != null &&financeChangingFixedAccountVO.getDueDate().intValue() != 0 ){
            fixedAccount.setDueDate(financeChangingFixedAccountVO.getDueDate());
        }

        FixedAccount fixedAccountSalved = fixedAccountRepository.save(fixedAccount);

        return modelMapper.map(fixedAccountSalved,FinanceFixedAccountDTO.class);
    }

    @Override
    public Void deletingAFixedAccount(String id) {

        Optional<FixedAccount> fixedAccountOptional = fixedAccountRepository.findById(id);

        if(fixedAccountOptional.isEmpty()){
            throw new RuntimeException("O id informado não é valido!");
        }

        fixedAccountRepository.deleteById(id);

        return null;
    }


    private User returnUserCorrespondingToTheRequest(){
        SecurityContext securityContext = SecurityContextHolder.getContext();

        Authentication authentication = securityContext.getAuthentication();

        UserAuth userAuth = (UserAuth) authentication.getPrincipal();

        return modelMapper.map(userAuth,User.class);
    }
}