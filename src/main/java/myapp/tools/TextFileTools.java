package myapp.tools;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import myapp.collections.List;

public class TextFileTools {

    public static List<List<String>> readDirectory(String path) throws IOException {
        final List<List<String>> mainList = new List<>();
        walkDirectory(path, new ProcessFile() {
            @Override
            public void process(Path fileName) {
                FileReader fileReader = new FileReader(fileName.toString(), Charset.forName("Windows-1252"));
                mainList.add(TextFileTools.readFile(fileReader));
            }
        });
        return mainList;
    }

    public static List<String> readFile(FileReader reader) {
        List<String> wordList = new List<>();
        String word;
        while ((word = readWord(reader)) != null) {
            wordList.add(word);
        }
        return wordList;
    }

    public static String readWord(FileReader reader) {
        StringBuilder stringBuilder = new StringBuilder();
        if (reader.isEndOfFile()) return null;
        int charCount = 0;
        char c = reader.readChar();
        while ((' ' != c) &&
                ('.' != c) &&
                (',' != c) &&
                (';' != c) &&
                (':' != c) &&
                ('"' != c) &&
                ('(' != c) &&
                (')' != c) &&
                ('*' != c) &&
                ('-' != c) &&
                ('\n' != c) &&
                ('\r' != c)) {
            stringBuilder.append(c);
            charCount = charCount + 1;
            if (reader.isEndOfFile()) { break; }
            c = reader.readChar();
        }
        if (charCount > 0) {
            return stringBuilder.toString();
        }
        return readWord(reader);
    }

    public static int countWord(FileReader reader, String wordToFind) {
        int count = 0;
        String word;
        while ((word = readWord(reader)) != null) {
            if (wordToFind.equals(word)) {
                count = count + 1;
            }
        }
        return count;
    }

    public static void walkDirectory(String path, ProcessFile processFile) throws IOException {
        Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .sorted()
                .forEach(processFile::process);
    }

}
