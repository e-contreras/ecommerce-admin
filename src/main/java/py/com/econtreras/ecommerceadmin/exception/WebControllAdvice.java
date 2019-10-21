package py.com.econtreras.ecommerceadmin.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebControllAdvice {

    private static final Logger LOGGER = LogManager.getLogger(WebControllAdvice.class);

    @ExceptionHandler({EcontrerasException.class})
    public String apiExceptionHandler(EcontrerasException ex){
        LOGGER.error(ex);
        return "error";
    }

}
