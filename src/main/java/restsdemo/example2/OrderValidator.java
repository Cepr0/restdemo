package restsdemo.example2;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Cepro, 2016-12-29
 */
@Component(value = "beforeCreateOrderValidator")
public class OrderValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Order.class.equals(aClass);
    }
    
    @Override
    public void validate(Object o, Errors errors) {
        Order order = (Order) o;
        // errors.rejectValue("status","error.test");
    }
}
