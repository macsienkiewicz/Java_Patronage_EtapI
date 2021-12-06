package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Analyzer {
    private List<Word> words;


    public Analyzer() {
        this.words = new ArrayList<Word>();
    } //obiekt przechowuje liste obiektow Word

    public List<Word> getWords() {
        return words;
    }





    public static void file_analyze (File file, Analyzer analyzer) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] line_tab = line.split(" ");
            for (String s : line_tab) {
                //formatowanie na male litery, ignorowanie znakow nie bedacych literami lub cyframi
                s = s.replaceAll("[^a-zA-Z0-9ąćęłńóśźżĄĆĘŁŃÓŚŹŻ]", "").toLowerCase();
                if(!s.equals("")) { //jezeli slowo nie jest puste(np. nie jest spacja)
                    boolean has_word = false; //bool sprawdzajacy czy w obiekcie Analyzer juz sie znajduje podane slowo
                    for (Word w : analyzer.getWords()) {
                        if (w.getWord_name().equals(s)) { //jezeli slowo wystepuje juz w ArrayLiscie
                            has_word = true;
                            w.increaseNumber();
                        }
                    }
                    if (!has_word) { //jezeli nie ma jeszcze takiego slowa to dodajemy je do listy obiektow Word
                        analyzer.getWords().add(new Word(s));
                    }
                }

            }
        }


    }


    @Override
    public String toString() {
        Collections.sort(words, Word.comparator); //sortowanie aby slowa wystepowaly malejaco a w nastepnej kolejnosci alfabetycznie
        String result = "";
        for (Word w : words) {
            result = result + w.toString() + "\n";
        }
        return result;
    }




}
