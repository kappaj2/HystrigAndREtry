package za.co.ajk.springretry.retryclient.exceptions;

public class BoomException extends RuntimeException {
  
    public BoomException() {
    }
    
    public BoomException(String message) {
        super(message);
    }
}
