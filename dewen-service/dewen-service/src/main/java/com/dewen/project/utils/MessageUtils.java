package com.dewen.project.utils;


import com.dewen.project.exception.DataException;

import java.util.Objects;

public class MessageUtils {
    public static void isTrue(Boolean expression , String msg, String... placeholders){
        for (String placeholder : placeholders) {
            msg = msg.replace("{}", placeholder);
        }
        if(!Objects.equals(expression, true)){
            throw new DataException(msg);
        }
    }
}
