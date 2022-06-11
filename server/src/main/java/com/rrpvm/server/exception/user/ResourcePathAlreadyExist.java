package com.rrpvm.server.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "resource already exist")
public class ResourcePathAlreadyExist extends Exception {

}
