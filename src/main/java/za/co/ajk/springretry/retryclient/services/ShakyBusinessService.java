package za.co.ajk.springretry.retryclient.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import za.co.ajk.springretry.retryclient.exceptions.BoomException;

@Service
public class ShakyBusinessService {
    
    private static final Log log = LogFactory.getLog(ShakyBusinessService.class);
    
    /**
     * For SpringRetry add @Recover - for Hystrix leave empty.
     * @return
     */
    @Recover
    public int fallback(BoomException bex){
        log.info("Doing fallback "+System.currentTimeMillis());
        return 2;
    }
    
    /**
     * For Hystrix enabledment then add the following annotation
     * @HystrixCommand(fallbackMethod="fallback")
     * @return
     * @throws Exception
     */
    @Retryable(include=BoomException.class, maxAttempts = 10)
    public double deriveNumber() throws Exception{
        double res = Math.random();
        log.info("Res : "+res);
        
        if(res > 0.5){
            log.info("Calling derivedNumber");
            Thread.sleep(1000*3l);
            throw new BoomException("Boom!");
        }
        return res;
    }
}
