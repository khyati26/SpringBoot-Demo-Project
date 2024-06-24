package springtemplate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("NotificationValidator")
public class NotificationValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return NotificationReq.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NotificationReq notificationReq = (NotificationReq) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "Please enter first name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Please enter last name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "supervisor", "Please select supervisor");
        if (!errors.hasFieldErrors("firstName")) {
            if (!Utility.isAllAlphabet(notificationReq.getFirstName())) {
                errors.rejectValue("firstName", "First name must contain only alphabet");
            }
        }
        if (!errors.hasFieldErrors("lastName")) {
            if (!Utility.isAllAlphabet(notificationReq.getLastName())) {
                errors.rejectValue("lastName", "Last name must contain only alphabet");
            }
        }
        if (notificationReq.getPhoneNumber() != null && !notificationReq.getPhoneNumber().trim().isEmpty()) {
            if (!Utility.isPhNo(notificationReq.getPhoneNumber())) {
                errors.rejectValue("phoneNumber", "PhoneNumber should be in international format. i.e : +111 (202) " +
                        "555-0125");
            }
        }
        if (notificationReq.getEmail() != null && !notificationReq.getEmail().trim().isEmpty()) {
            if (!Utility.isEmail(notificationReq.getEmail())) {
                errors.rejectValue("email", "Please enter valid Email");
            }
        }
    }
}
