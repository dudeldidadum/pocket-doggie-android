package com.n3rditorium.persistence.databse;

public class ObjectNotFoundException extends Exception {

   public ObjectNotFoundException() {
      super();
   }

   public ObjectNotFoundException(String message) {
      super(message);
   }

   public ObjectNotFoundException(Throwable cause) {
      super(cause);
   }

   public ObjectNotFoundException(String message, Throwable cause) {
      super(message, cause);
   }
}
