package com.zongzewu.bettercallwu.utils;

import java.util.HashMap;
import java.util.Map;

public class VerificationManager {
    private static final Map<String, String> verificationCodes = new HashMap<>();

    public static void saveCode(String email, String code) {
        verificationCodes.put(email, code);
        System.out.println("Code saved for email: " + email);
    }

    public static boolean verifyCode(String email, String inputCode) {
        if (!verificationCodes.containsKey(email)) {
            System.out.println("No verification code found for email.");
            return false;
        }
        String correctCode = verificationCodes.get(email);
        return correctCode.equals(inputCode);
    }
}
