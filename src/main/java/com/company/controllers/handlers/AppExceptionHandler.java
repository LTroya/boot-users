package com.company.controllers.handlers;

import com.company.entities.validations.MessageBody;
import com.company.entities.validations.MessageDTO;
import com.company.exceptions.IdsNotMatchException;
import com.company.exceptions.UserNotFoundException;
import com.company.utilities.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

/**
 * @user LuisTroya
 * @date 22/Feb/2017
 */
@ControllerAdvice
public class AppExceptionHandler {
    @Autowired
    private MessageSource msgSource;

    /**
     * Exception triggered if an user does not exist.
     *
     * @return Status: Bad Request, Reason: User not found
     */
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public MessageDTO handleUserNotFound() { return processExceptionReason("exception.users.not_found"); }

    @ExceptionHandler(IdsNotMatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO handleIdsNotMatchException() { return processExceptionReason("exception.object.id_not_match"); }

    private MessageDTO processExceptionReason(String key) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String msg = msgSource.getMessage(key, null, currentLocale);
        return new MessageDTO(MessageDTO.MessageType.ERROR, new MessageBody(key, msg));
    }
}
