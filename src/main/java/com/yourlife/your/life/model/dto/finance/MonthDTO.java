package com.yourlife.your.life.model.dto.finance;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yourlife.your.life.model.entity.finance.Appetizer;
import com.yourlife.your.life.model.entity.finance.CategoryVariableExpense;
import com.yourlife.your.life.model.entity.finance.FixedAccount;
import com.yourlife.your.life.model.entity.finance.Installment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude
public class MonthDTO {
    private String id;
    private String name;
    private Integer year;
    private LocalDate date;
    private List<Appetizer> appetizer;
    private List<CategoryVariableExpense> categoryVariableExpens;
    private List<FixedAccount> fixedAccounts;
    private List<Installment> installments;
}
