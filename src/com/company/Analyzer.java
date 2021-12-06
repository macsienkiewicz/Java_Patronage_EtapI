package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Analyzer {
    private List<Word> words;


    public Analyzer() {
        this.words = new ArrayList<Word>();
    }

    public List<Word> getWords() {
        return words;
    }





    public static void file_analyze (File file, Analyzer analyzer) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] line_tab = line.split(" ");
            for (String s : line_tab) {
                s = s.replaceAll("[^a-zA-Z0-9ąćęłńóśźżĄĆĘŁŃÓŚŹŻ]", "").toLowerCase();
                boolean has_word = false;
                for (Word w : analyzer.getWords()) {
                    if (w.getWord_name().equals(s)) {
                        has_word = true;
                        w.setNumber();
                    }
                }
                if (!has_word) {
                    analyzer.getWords().add(new Word(s));
                }

            }
        }


    }


    @Override
    public String toString() {
        Collections.sort(words, Word.comparator);
        String result = "";
        for (Word w : words) {
            result = result + w.toString() + "\n";
        }
        return result;
    }




}
