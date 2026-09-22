package com.example.url_shortener.util;

public class Base62Encoder {
    private static final String CHARACTERS =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String encode(long number){

        if(number == 0){
            return "0";
        }
        StringBuilder result = new StringBuilder();

        while(number > 0){
            int remainder = (int) (number % 62);
            result.append(CHARACTERS.charAt(remainder));
            number = number / 62;
        }
        return result.reverse().toString();
    }
    public static long decode(String code){
        long number = 0;

        for(int i = 0; i< code.length(); i++){
            char character = code.charAt(i);
            int value = CHARACTERS.indexOf(character);

            if(value == -1){
                throw new IllegalArgumentException("Invalid short code");
            }
            number = number * 62 + value;
        }
        return number;
    }
}
