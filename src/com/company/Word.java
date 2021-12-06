package com.company;

import java.util.Comparator;

public class Word implements Comparable<Word> {

    private final String word_name;
    private int number;

    public Word(String word_name) {
        this.word_name = word_name;
        this.number = 1;
    }

    public String getWord_name() {
        return word_name;
    }

    public int getNumber() {
        return number;
    }

    public void increaseNumber() {
        this.number = this.number + 1;
    }


    @Override
    public String toString() {
        return word_name + ": " + number + ",";
    }


    @Override
    public int compareTo(Word o) {
        return comparator.compare(this, o);
    }

    public static Comparator<Word> comparator = new Comparator<Word>() {
        @Override
        public int compare(Word o1, Word o2) {
            int result = 0;
            if (o1.getNumber() == o2.getNumber()) {
                result = o1.getWord_name().compareTo(o2.getWord_name());
            }
            else {
                if (o1.getNumber() > o2.getNumber()) {
                    result = -1;
                }
                else {
                    result = 1;
                }
            }
            return result;
        }
    };
}
