package com.company.utilities;

import com.company.entities.validations.MessageBody;
import com.company.entities.validations.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @user LuisTroya
 * @date 25/Feb/2017
 */
public class ValidationUtil {
    @Autowired
    private static MessageSource msgSource;

    public static Throwable getRootCause(Throwable e) {
        Throwable cause = null;
        Throwable result = e;

        while(null != (cause = result.getCause())  && (result != cause) ) {
            result = cause;
        }
        return result;
    }
}
