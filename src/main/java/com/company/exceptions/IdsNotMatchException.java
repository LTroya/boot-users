package com.company.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @user LuisTroya
 * @date 26/Feb/2017
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Url and object's id does not match")
public class IdsNotMatchException extends RuntimeException {
}
