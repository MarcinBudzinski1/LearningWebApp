package com.example.learning.users;

import lombok.Getter;

@Getter
public enum Countries {

    POLAND("PL", "Polska"),
    GERMANY("DE", "Niemcy"),
    ENGLAND("ENG", "Anglia"),
    ITALY("ITA", "Włochy"),
    SPAIN("SPA", "Hiszpania"),
    PORTUGAL("POR", "Portugalia"),
    FRANCE("FRA", "Francja");

    private String symbol;
    private String plName;

    Countries(String symbol, String plName) {
        this.symbol = symbol;
        this.plName = plName;
    }

}
