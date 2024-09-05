package myapp.app;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Objects;

import myapp.tools.FileReader;
import myapp.tools.ProcessFile;
import myapp.tools.TextFileTools;

public class ApplicationTP1 {

    public static class Occurrences {
        private final char character;
        private int count = 0;

        public Occurrences(char character) {
            this.character = character;
        }

        public Occurrences(char character, int count) {
            this.character = character;
            this.count = count;
        }

        public void incrementCount() {
            count = count + 1;
        }

        public char getCharacter() {
            return character;
        }

        public int getCount() {
            return count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Occurrences that = (Occurrences) o;
            return character == that.character && count == that.count;
        }

        @Override
        public int hashCode() {
            return Objects.hash(character, count);
        }
    }

    public static class DisplayFileName implements ProcessFile {
        @Override
        public void process(Path fileName) {
            System.out.println(fileName);
        }
    }

    public static class DisplayFileNameWithWord implements ProcessFile {

        private final String word;

        public DisplayFileNameWithWord(String word) {
            this.word = word;
        }

        @Override
        public void process(Path fileName) {
            FileReader fileReader = new FileReader(fileName.toString(), Charset.forName("Windows-1252"));
            if (TextFileTools.countWord(fileReader, word) > 0) {
                System.out.println(fileName);
            }

        }
    }

    public Character readChar(FileReader reader) {
        if (reader.isEndOfFile()) return null;
        return reader.readChar();
    }

    public int countAl(FileReader reader) {
        int count = 0;
        boolean foundA = false;
        while (!reader.isEndOfFile()) {
            char c = reader.readChar();
            if (foundA && (c == 'l')) {
                count = count + 1;
            }
            foundA = c == 'a';
        }
        return count;
    }

    public int countWords(FileReader reader) {
        int wordCount = 0;
        while (!reader.isEndOfFile()) {
            int charCount = 0;
            char c = reader.readChar();
            while ((' ' != c) &&
                    ('.' != c) &&
                    (',' != c) &&
                    (';' != c) &&
                    (':' != c) &&
                    ('\n' != c) ) {
                charCount = charCount + 1;
                if (reader.isEndOfFile()) { break; }
                c = reader.readChar();
            }
            if (charCount > 0) {
                wordCount = wordCount + 1;
            }
        }
        return wordCount;
    }

    public String[] read5Words(FileReader reader) {
        String[] words = new String[5];
        int wordCount = 0;
        String word;
        while ((word = TextFileTools.readWord(reader)) != null) {
            words[wordCount] = word;
            wordCount = wordCount + 1;
            if (wordCount == 5) { break; }  
        }
        return words;
    }

    public Occurrences[] countPunctuations(FileReader reader) {
        Occurrences[] occurrences = new Occurrences[] {
                new Occurrences('.'),
                new Occurrences(','),
                new Occurrences(':'),
                new Occurrences(';')
        };
        while (!reader.isEndOfFile()) {
            char c = reader.readChar();
            for (Occurrences occ : occurrences) {
                if (c == occ.getCharacter()) {
                    occ.incrementCount();
                    break;
                }
            }
        }
        return occurrences;
    }

    public void displayDirectoryContent(String path) throws IOException {
        //TextFileTools.walkDirectory(path, new DisplayFileName());
        /*TextFileTools.walkDirectory(path, new ProcessFile() {
            @Override
            public void process(Path fileName) {
                System.out.println(fileName);
            }
        });*/
        TextFileTools.walkDirectory(path, fileName -> {
            System.out.println(fileName);
        });
    }

    public void displayFilesWithWord(String path, String word) throws IOException {
        //TextFileTools.walkDirectory(path, new DisplayFileNameWithWord(word));
        TextFileTools.walkDirectory(path, fileName -> {
            FileReader fileReader = new FileReader(fileName.toString(), Charset.forName("Windows-1252"));
            if (TextFileTools.countWord(fileReader, word) > 0) {
                System.out.println(fileName);
            }
        });
    }

}
