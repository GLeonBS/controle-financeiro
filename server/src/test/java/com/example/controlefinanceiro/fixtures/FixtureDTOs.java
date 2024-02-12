package com.example.controlefinanceiro.fixtures;

import com.example.controlefinanceiro.dto.DTOFake;

public class FixtureDTOs {

    public static DTOFake createDTOFake() {
        return new DTOFake("usuario", "usuario@gmail.com", "123456");
    }
}
