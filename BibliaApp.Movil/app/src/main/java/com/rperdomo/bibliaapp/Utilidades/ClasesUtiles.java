package com.rperdomo.bibliaapp.Utilidades;

public class ClasesUtiles {

    public static boolean isNullOrBlank(String param) {
        return param == null || param.trim().length() == 0;
    }
}
