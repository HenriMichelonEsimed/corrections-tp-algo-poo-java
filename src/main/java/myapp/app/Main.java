package myapp.app;

import java.io.IOException;
import java.nio.charset.Charset;

import myapp.collections.EndOfListException;
import myapp.tools.FileReader;

public class Main {

    public static void main(String[] args) {
        /**** TP 1 */
        //System.out.println("Hello World!");
        //ApplicationHelloWorld applicationHelloWorld = new ApplicationHelloWorld();
        //applicationHelloWorld.displayMessage();
        ApplicationTP1 application = new ApplicationTP1();
        FileReader fileReader = new FileReader(args[0]);
        /*Character c = application.readChar(fileReader);
        if (c != null) {
            System.out.format("1er caractère : %c\n", c);
        } else {
            System.err.println("Fichier vide");
        }*/
        //System.out.format("Nombre de 'al': %d\n", application.countAl(fileReader));
        //System.out.format("Nombre de mots : %d\n", application.countWords(fileReader));
        /*System.out.println("Cinq premiers mots du fichier:");
        for(String word : application.read5Words(fileReader)) {
            System.out.format("%s ", word);
        }*/
        /*for(Application.Occurrences occurrences : application.countPunctuations(fileReader)) {
            System.out.format("%c : %d\n", occurrences.getCharacter(), occurrences.getCount());
        }*/
        //System.out.format("Le mot 'et' apparaît %d fois\n", TextFileTools.countWord(fileReader, "et"));
        /*try {
            application.displayDirectoryContent("textes");
        } catch (IOException ioe) {
            System.err.format("Erreur lors du parcours du répertoire : %s\n", ioe.getMessage());
        }*/
        /*System.out.println("Fichiers contenants le mot 'conformément': ");
        try {
            application.displayFilesWithWord("textes", "conformément");
        } catch (IOException ioe) {
            System.err.format("Erreur lors du parcours du répertoire : %s\n", ioe.getMessage());
        }*/

        /**** TP 2 */
        /*ApplicationTP2 applicationTP2 = new ApplicationTP2();
        try {
            //FileReader fileReader = new FileReader("textes/code_civil/1.txt", Charset.forName("Windows-1252"));
            //applicationTP2.exo3(fileReader);
            applicationTP2.exo4("textes");
        } catch (IOException | EndOfListException e) {
            e.printStackTrace();
        }*/

        /**** TP 3 */
        ApplicationTP3 applicationTP3;
        try {
            applicationTP3 = new ApplicationTP3();
            //applicationTP3.exo2("textes");
            applicationTP3.exo3("textes", "vente1");
            applicationTP3.exo3("textes", "vente");
        } catch (IOException | EndOfListException e) {
            e.printStackTrace();
        }
       
        /*try {
            new WebSearchEngine("textes", 8282);
        } catch (IOException | EndOfListException e) {
            e.printStackTrace();
        }*/

    }
}
