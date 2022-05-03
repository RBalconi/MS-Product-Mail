package com.ms.email.application.domain.enumerations;

import java.util.Arrays;

public enum Situation {
    SENT(0, "Enviado"),
    ERROR(1, "Erro");

    private int id;
    private String description;

    Situation(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static Situation getSituation(int id) {
        Situation[] situation = values();
        return Arrays.stream(situation)
                .filter(obj -> obj.getId() == id)
                .toList().get(0);
    }
}
