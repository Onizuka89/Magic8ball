package com.stiandrobak.magic8ball.app;

import java.util.Random;

/**
 * Created by stiansd on 10.04.14.
 */
public class AnswerPicker {
    final private static String later = "Ask again later";
    final private static String[] answers = {
            "Ha! Think again!",
            "Do it!",
            "Abandon all hope"
    };
    final private static Random random = new Random();

    static String getAnswer(){
        int number = random.nextInt(answers.length * 2);
        if(number >= answers.length){
            return later;
        }else{
            return answers[number];
        }
    }

}
