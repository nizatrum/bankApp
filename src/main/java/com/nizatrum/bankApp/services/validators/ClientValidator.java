package com.nizatrum.bankApp.services.validators;

import com.nizatrum.bankApp.models.Client;
import org.apache.commons.lang3.StringUtils;

public final class ClientValidator {
    private ClientValidator() { }
    public static boolean clientValidator(Client client)  {
        if (nameOrSurnameOrPatronymicValidator(client.getName()) &&
                nameOrSurnameOrPatronymicValidator(client.getSurname()) &&
                nameOrSurnameOrPatronymicValidator(client.getPatronymic()) &&
            passwordValidator(client.getPassword())) {
             return true;
        }
        return false;
    }
    private static boolean nameOrSurnameOrPatronymicValidator(String value) {
        if (value != null) {
            return value.matches("^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$");
        }
        return false;
    }
    private static boolean passwordValidator(String password) {
        if (password != null) {
            return StringUtils.isNumeric(password) && password.length() >= 6 && password.length() <= 10;
        }
        return false;
    }
}
