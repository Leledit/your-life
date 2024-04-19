package com.yourlife.your.life.model.vo.finance;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVariableExpenseRegisterVO {

    private String name;

    private String description;

    private String type; //CategoryVariableExpensesTypes
}
