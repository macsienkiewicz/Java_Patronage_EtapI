package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        menu_start();

    }

    public static void menu_start() {
        System.out.println("Wybierz odpowiednia opcje:");
        System.out.println("1 -> Analiza pliku tekstowego");
        System.out.println("2 -> Zakonczenie dzialania programu");
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        try {
            option = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Niepoprawny znak!");
        }

        if (option == 0) {
            menu_start();
        }
        else if (option == 1) {
            open();
        }
        else if (option != 2) {
            System.out.println("Niepoprawny znak!");
            menu_start();
        }

    }

    public static void menu_save(Analyzer analyzer, File file) throws IOException {
        System.out.println("Do jakiego pliku chcesz zapisac wynik?");
        System.out.println("1 -> Domyslny plik .txt w tym samym folderze, gdzie znajduje sie analizowany plik");
        System.out.println("2 -> Domyslny plik .xlsx w tym samym folderze, gdzie znajduje sie analizowany plik");
        System.out.println("3 -> Wlasny plik .txt");
        System.out.println("4 -> Wlasny plik .xlsx");
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        try {
            option = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Niepoprawny znak!");
        }
        if (option == 0) {
            menu_save(analyzer, file);
        }
        else if (option == 1) {
            save_txt(analyzer,default_file_name(file, ".txt"));
            menu_start();
        }
        else if (option == 2) {
            //save_xlsx(analyzer,default_file_name(file, ".xlsx"));
            //menu_start();
            menu_save(analyzer, file); //tymczasowo
        }
        else if (option == 3) {
            String file_name = my_file_name(".txt");
            if (!file_name.equals("")) {
                save_txt(analyzer,file_name);
                menu_start();
            }
            else {
                menu_save(analyzer, file);
            }
        }
        else if (option == 4) {
            /*
            String file_name = my_file_name(".xlsx");
            if (!file_name.equals("")) {
                save_txt(analyzer,file_name);
                menu_start();
            }
            else {
                menu_save(analyzer, file);
            }

             */
            menu_save(analyzer, file); //tymczasowo
        }
        else {
            System.out.println("Niepoprawny znak!");
            menu_save(analyzer, file);
        }

    }

    public static void open() {
        Analyzer analyzer = new Analyzer();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz nazwe pliku, ktory ma byc analizowany");
        String file_name = scanner.nextLine();
        File file = new File(file_name);
        if (file.length() > 5 * 1024 * 1024) {
            System.out.println("Za duzy plik! Dopuszczalna wielkosc pliku to 5MB!");
            System.out.println("Wielkosc tego pliku to: "+file.length()/(1024*1024)+ " MB");
            menu_start();
        }
        else if (file.getName().length() < 4 || !file.getName().substring(file.getName().length() - 4).equals(".txt")) {
            System.out.println("Nie znaleziono pliku tekstowego! Mozna analizowac pliki z rozszerzeniem .txt!");
            menu_start();
        }
        else {
            try {
                Analyzer.file_analyze(file, analyzer);
                System.out.print(analyzer+"\n");
                menu_save(analyzer, file);

            } catch (Exception e) {
                System.out.println("Nie znaleziono pliku!");
                menu_start();
            }
        }

    }

    public static String default_file_name(File first_file, String format) {
        if (format.equals(".xlsx")) {
            return first_file.getParent() + "\\" + first_file.getName() + "_analized.xlsx";
        }
        else {
            return first_file.getParent() + "\\" + first_file.getName() + "_analized.txt";
        }
    }

    public static String my_file_name(String format) {
        System.out.println("Wpisz sciezke pliku, do ktorego ma byc zapisany");
        Scanner scanner = new Scanner(System.in);
        String file_name = "";
        try {
            file_name = scanner.nextLine();
            if (file_name.length() < format.length() || !file_name.substring(file_name.length() - format.length()).equals(format)) {
                System.out.println("Wpisano zle rozszerzenie! Plik powinien miec rozszerzenie "+format+"!");
                file_name = "";
            }

        } catch (Exception e) {
            System.out.println("Nie znaleziono takiej sciezki!");
        }
        return file_name;
    }

    public static void save_txt(Analyzer analyzer, String file_name) throws IOException {
        File new_file = new File(file_name);
        FileWriter writer = new FileWriter(new_file);
        writer.write(analyzer.toString());
        writer.close();
    }

    //save_xls




    //testy
}
