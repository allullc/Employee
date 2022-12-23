package com.example.empleado.employee;

public class NotFoundIdException extends RuntimeException {
   public NotFoundIdException() {
      super("The id was not found");
   }
}