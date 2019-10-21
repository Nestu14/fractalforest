package com.eclipsekingdom.fractalforest.populator.validation;

public class NameValidation {

    public static NameStatus clean(String name){
        if (!name.matches("^[a-zA-Z0-9\\_\\-]+$")) {
            return NameStatus.SPECIAL_CHARACTERS;
        }else if(name.length() > 20){
            return NameStatus.TOO_LONG;
        }else{
            return NameStatus.VALID;
        }
    }

}