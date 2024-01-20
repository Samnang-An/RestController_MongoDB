package webshop.database.exception;

public class NoCustomerFoundException extends Exception{

  public NoCustomerFoundException() {
    super("No Customer found!");
  }

  public NoCustomerFoundException(String message) {
    super(message);
  }
}
