package com.example.sweet.domain.request;

import com.example.sweet.util.constant.TypeUserEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO<T> {

    @NotNull(message = "Data cannot be null")
    private T data;

    @NotNull(message = "Type cannot be null")
    private TypeUserEnum type;
}
