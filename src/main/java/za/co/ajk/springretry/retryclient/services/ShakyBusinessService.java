package za.co.ajk.springretry.retryclient.services;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import za.co.ajk.springretry.retryclient.exceptions.BoomException;


public interface ShakyBusinessService {
    
    @Retryable
    void retryService();
    
    @Retryable(
            value={BoomException.class},
            maxAttempts = 2,
            backoff = @Backoff(delay=5000)
    )
    double deriveNumber(int offset) throws Exception;
    
    @Recover
    double recover(BoomException ex, int offset);
    
    void templateRetryService();
}
