package org.experis.lamiapizzeria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class PizzaNotFoundException extends ResponseStatusException {
  
  
  public PizzaNotFoundException(HttpStatusCode status) {
    super(status);
  }
  
  public PizzaNotFoundException(String reason) {
    
    super(HttpStatus.NOT_FOUND, reason);
  }
  
  public PizzaNotFoundException(int rawStatusCode, String reason, Throwable cause) {
    super(rawStatusCode, reason, cause);
  }
  
  public PizzaNotFoundException(HttpStatusCode status, String reason, Throwable cause) {
    super(status, reason, cause);
  }
  
  protected PizzaNotFoundException(HttpStatusCode status, String reason, Throwable cause, String messageDetailCode, Object[] messageDetailArguments) {
    super(status, reason, cause, messageDetailCode, messageDetailArguments);
  }
}
