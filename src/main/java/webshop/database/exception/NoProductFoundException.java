package webshop.database.exception;

public class NoProductFoundException extends Exception{

  public NoProductFoundException() {
    super("No Product found!");
  }

  public NoProductFoundException(String message) {
    super(message);
  }
}
