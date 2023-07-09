package com.nizatrum.bankApp.services.validation;

public final class Validation {
    private Validation () {}
    public static boolean validateAccountName(String accountName) {
        String regex = "([0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4})";
        boolean isPattern = accountName.matches(regex);
        if (isPattern) {
            String value = accountName.replace("-", "");
            if (algorithmLuna(value)) {
                return true;
            }
        }
        return false;
    }
    private static boolean algorithmLuna(String value) {
        String resultAfterFirstCheck = "";
        String resultAfterSecondCheck = "";
        int finalResult = 0;
        for (int i = 0; i < value.length(); i+=2) {
            int firstCheck = Integer.parseInt(String.valueOf(value.charAt(i))) * 2;
            if (firstCheck > 9) {
                firstCheck -= 9;
                resultAfterFirstCheck += Integer.toString(firstCheck);
            } else {
                resultAfterFirstCheck += Integer.toString(firstCheck);
            }
        }
        for (int i = 1; i < value.length(); i+=2) {
            resultAfterSecondCheck += String.valueOf(value.charAt(i));
        }
        for (int i = 0; i < value.length(); i++) {
            String thirdCheck = resultAfterFirstCheck + resultAfterSecondCheck;
            finalResult += Integer.parseInt(String.valueOf(thirdCheck.charAt(i)));
        }
        if (finalResult % 10 == 0) {
            return true;
        }
        return false;
    }
}
