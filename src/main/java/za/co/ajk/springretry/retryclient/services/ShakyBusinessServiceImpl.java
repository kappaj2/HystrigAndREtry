package za.co.ajk.springretry.retryclient.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import za.co.ajk.springretry.retryclient.exceptions.BoomException;

@Service
public class ShakyBusinessServiceImpl implements ShakyBusinessService {
    
    private static final Logger logger = LoggerFactory.getLogger(ShakyBusinessServiceImpl.class);
    
    @Override
    public void retryService() {
        logger.info("throw RuntimeException in method retryService()");
        throw new RuntimeException();
    }
    
    /**
     * For Hystrix enabledment then add the following annotation
     * @HystrixCommand(fallbackMethod="fallback")
     * @return
     * @throws Exception
     */
    @Override
    public double deriveNumber(int offset) throws Exception{
        double res = Math.random();
        logger.info("Res : "+res);
        
        if(res > 0.5){
            logger.info("Calling derivedNumber");
            Thread.sleep(1000*3l);
            throw new BoomException("Boom!");
        }
        return res;
    }
    
    /**
     * For SpringRetry add @Recover - for Hystrix leave empty.
     * @return
     */
    @Override
    public double recover(BoomException bex, int offset){
        logger.info("Doing fallback "+System.currentTimeMillis());
        return 2;
    }
    
    @Override
    public void templateRetryService() {
        logger.info("throw RuntimeException in method templateRetryService()");
        throw new RuntimeException();
    }
}
