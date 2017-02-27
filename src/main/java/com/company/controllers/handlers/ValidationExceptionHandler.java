package com.company.controllers.handlers;

import com.company.entities.validations.MessageBody;
import com.company.entities.validations.MessageDTO;
import com.company.utilities.StringUtil;
import com.company.utilities.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @user LuisTroya
 * @date 22/Feb/2017
 */
@ControllerAdvice
public class ValidationExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(ValidationExceptionHandler.class);

    @Autowired
    private MessageSource msgSource;

    /**
     * Database constraints that will be check on the exception to return a proper
     * message to the user
     */
    private static Map<String, String> constraintCodeMap = new HashMap<String, String>() {
        {
            put("users_unique_username_idx", "exception.users.duplicate_username");
            put("users_unique_email_idx", "exception.users.duplicate_email");
        }
    };

    /**
     * Handler for missing params or lengths contraints
     *
     * @param ex MethodArgumentNotValidException
     * @return Status: Bad Request, Messages: MessageDTO {messages: [], type: ERROR}
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> errors = result.getFieldErrors();

        return processFieldError(errors);
    }

    /**
     * Handler for missing params
     *
     * @param ex BindException
     * @return Status: Bad Request, Messages: MessageDTO {messages: [], type: ERROR}
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO processValidationError(BindException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> errors = result.getFieldErrors();

        return processFieldError(errors);
    }

    /**
     * Handler for constraint exceptions. This method used constraintCodeMap at the
     * top of the file.
     *
     * @param req request that triggered the exception
     * @param e DataIntegrityViolationException
     * @return Status: Conflict, Messages: MessageDTO {messages: [], type: ERROR}
     */
//    @ResponseStatus(value = HttpStatus.CONFLICT)  // 409
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    @ResponseBody
//    public MessageDTO conflict(HttpServletRequest req, DataIntegrityViolationException e) {
//        MessageDTO message = null;
//        Locale currentLocale = LocaleContextHolder.getLocale();
//        String rootMsg = ValidationUtil.getRootCause(e).getMessage();
//        if (rootMsg != null) {
//            Optional<Map.Entry<String, String>> entry = constraintCodeMap.entrySet().stream()
//                    .filter((it) -> StringUtil.containsIgnoreCase(rootMsg, it.getKey()))
//                    .findAny();
//
//            if (entry.isPresent()) {
//                String value = entry.get().getValue();
//                String msg = msgSource.getMessage(value, null, currentLocale);
//                message = new MessageDTO(MessageDTO.MessageType.ERROR, new MessageBody(value, msg));
//            }
//        }
//        return message;
//    }

    /**
     * Return a MessageDTO {messages: [], type: ERROR}'s object with the message.properties's key and
     * its message
     *
     * @param errors list of errors with message.properties' keys
     * @return MessageDTO {messages: [], type: ERROR}
     */
    private MessageDTO processFieldError(List<FieldError> errors) {
        MessageDTO message = null;
        if (errors.size() > 0) {
            Locale currentLocale = LocaleContextHolder.getLocale();
            List<MessageBody> messages = new ArrayList<>();
            for (FieldError error: errors) {
                String msg = msgSource.getMessage(error.getDefaultMessage(), null, currentLocale);
                messages.add(new MessageBody(error.getDefaultMessage(), msg));
            }
            message = new MessageDTO(MessageDTO.MessageType.ERROR, messages);
        }
        return message;
    }
}
