package com.example.empleado.employee;

public class NotFoundIdException extends RuntimeException {
   NotFoundIdException() {
      super("The id was not found");
   }
}