package com.bettercallwu.test;


import com.zongzewu.bettercallwu.utils.EmailSender;
import com.zongzewu.bettercallwu.utils.VerificationCodeGenerator;
import com.zongzewu.bettercallwu.utils.VerificationManager;

import java.util.Scanner;

public class EmailTester {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("What's ur email:");
        String email = scanner.nextLine();


        String code = VerificationCodeGenerator.generateCode();
        EmailSender.sendEmail(email, code);
        VerificationManager.saveCode(email, code);


        System.out.println("Show me what you got:");
        String inputCode = scanner.nextLine();


        if (VerificationManager.verifyCode(email, inputCode)) {
            System.out.println("I knew it's you!");
        } else {
            System.out.println("Who the deuce are you???");
        }

        scanner.close();
    }
}
