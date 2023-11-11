package bookstore.validation;

import bookstore.exception.RegistrationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        try {
            Object firstObj = getProperty(obj, firstFieldName);
            Object secondObj = getProperty(obj, secondFieldName);
            if (firstObj.equals(secondObj)) {
                return true;
            } else {
                throw new RegistrationException("Passwords don't match");
            }
        } catch (Exception e) {
            throw new RuntimeException("Unsuccessful try to match passwords", e);
        }
    }

    private Object getProperty(Object obj, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }
}
