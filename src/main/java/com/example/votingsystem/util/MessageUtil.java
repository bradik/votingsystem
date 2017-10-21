package com.example.votingsystem.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageUtil {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code, Locale locale) {
        //return code;
        return messageSource.getMessage(code, null, locale);
    }

    public String getMessage(String code) {
        //return code;
        return getMessage(code, LocaleContextHolder.getLocale());
    }
}
