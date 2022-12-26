package com.example.commerce.util;

public class NotFoundIdException extends RuntimeException {
   public NotFoundIdException() {
      super("The id was not found");
   }
}