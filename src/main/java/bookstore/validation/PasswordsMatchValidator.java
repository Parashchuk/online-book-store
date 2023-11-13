package bookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        Object password = new BeanWrapperImpl(object).getPropertyValue(firstFieldName);
        Object repeatPassword = new BeanWrapperImpl(object).getPropertyValue(secondFieldName);
        return password != null && password.equals(repeatPassword);
    }
}
