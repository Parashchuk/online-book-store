package bookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsbnValidator implements ConstraintValidator<Isbn, String> {
    private static final String ISBN_REGEX = "\\d{13}";

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        return string != null && string.matches(ISBN_REGEX);
    }
}
