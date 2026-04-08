package com.example.auth.utils;

import java.util.Random;

public class TestDataFactory {

    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*";
    private static final Random RANDOM = new Random();

    public static String username() {
        // Generate random username with at least 6 letters
        StringBuilder username = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            username.append(LETTERS.charAt(RANDOM.nextInt(LETTERS.length())));
        }
        return username.toString();
    }

    public static String password() {
        // Generate random password with at least 6 characters (mix of letters, numbers, special chars)
        StringBuilder password = new StringBuilder();

        // Add at least 1 letter, 1 number, 1 special char, then fill rest randomly
        password.append(LETTERS.charAt(RANDOM.nextInt(LETTERS.length())));
        password.append(NUMBERS.charAt(RANDOM.nextInt(NUMBERS.length())));
        password.append(SPECIAL_CHARS.charAt(RANDOM.nextInt(SPECIAL_CHARS.length())));

        // Fill remaining 6 characters with random mix
        String allChars = LETTERS + NUMBERS + SPECIAL_CHARS;
        for (int i = 0; i < 6; i++) {
            password.append(allChars.charAt(RANDOM.nextInt(allChars.length())));
        }

        return password.toString();
    }

    public static String phone() {
        // Generate random phone number starting with 0, followed by 9 random digits (10 digits total)
        StringBuilder phone = new StringBuilder("0");
        for (int i = 0; i < 9; i++) {
            phone.append(NUMBERS.charAt(RANDOM.nextInt(NUMBERS.length())));
        }
        return phone.toString();
    }
}
