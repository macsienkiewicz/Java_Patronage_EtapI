package com.company;

import java.io.*;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
        System.out.println("2 -> Domyslny plik .txt w tym samym folderze, gdzie znajduje sie analizowany plik wraz z plikiem .zip");
        System.out.println("3 -> Wlasny plik .txt");
        System.out.println("4 -> Wlasny plik .txt wraz z plikiem .zip");
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
            save_txt(analyzer,default_file_name(file, ".txt"), false);
            menu_start();
        }
        else if (option == 2) {
            save_txt(analyzer,default_file_name(file, ".txt"), true);
            menu_start();
        }
        else if (option == 3) {
            String file_name = my_file_name(".txt");
            if (!file_name.equals("")) {
                save_txt(analyzer,file_name, false);
                menu_start();
            }
            else {
                menu_save(analyzer, file);
            }
        }
        else if (option == 4) {
            String file_name = my_file_name(".txt");
            if (!file_name.equals("")) {
                save_txt(analyzer,file_name, true);
                menu_start();
            }
            else {
                menu_save(analyzer, file);
            }
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
        return first_file.getParent() + "\\" + first_file.getName() + "_analized" + format;
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

    public static void save_txt(Analyzer analyzer, String file_name, boolean zip) throws IOException {
        File new_file = new File(file_name);
        FileWriter writer = new FileWriter(new_file);
        writer.write(analyzer.toString());
        writer.close();
        if(zip) {
            FileOutputStream fos = new FileOutputStream(file_name+"_zipped.zip");
            ZipOutputStream out = new ZipOutputStream(fos);
            FileInputStream fis = new FileInputStream(new_file);
            ZipEntry zipEntry = new ZipEntry(new_file.getName());
            out.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while((length = fis.read(bytes)) >= 0) {
                out.write(bytes, 0, length);
            }
            out.close();
            fis.close();
            fos.close();
        }
    }

    /*public static void save_xlsx(Analyzer analyzer, String file_name) {
        File new_file = new File(file_name);
        XSSFWorkbook workbook = new XSSFWorkbook(new_file);
    }

     */

    //save_xls




    //testy
}
