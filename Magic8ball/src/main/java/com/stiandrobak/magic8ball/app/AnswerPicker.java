package com.stiandrobak.magic8ball.app;

import java.util.Random;

/**
 * Created on 10.04.14.
 * @author Stian Drøbak
 * @version 1.0b1
 */

/**
 *  Class for getting a "random" answers among the ones listed.
 */
public class AnswerPicker {
    final private static String later = "Ask again later";
    final private static String[] answers = {
            "Ha! Think again!",
            "Do it!",
            "Abandon all hope"
    };
    final private static Random random = new Random();

    /**
     * Get a "random" answer from the static answers.
     * @return string from AnswerPicker.answers
     */
    public static String getAnswer(){
        int number = random.nextInt(answers.length * 2);
        if(number >= answers.length){
            return later;
        }else{
            return answers[number];
        }
    }

}
