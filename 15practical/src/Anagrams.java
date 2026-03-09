import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Anagrams {

    public static String signature(String word) {
        char[] character = word.toCharArray();
        Arrays.sort(character);
        return new String(character);
    }

    public static Map<String, Integer> readAndBuildFrequency(String filename) throws IOException {

        String stripChars = "[]0123456789(,.;:_.!?-)";
        Map<String, Integer> D = new HashMap<String, Integer>();

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(new FileInputStream(filename), "ISO-8859-1")
        );

        String line = reader.readLine();
        while (line != null) {
            String[] tokens = line.split("\\s+");
            for (int i = 0; i < tokens.length; i++) {
                String w = stripChars(tokens[i], stripChars);
                if (w.length() == 0) {
                    continue;
                }
                if (D.containsKey(w)) {
                    D.put(w, D.get(w) + 1);
                } else {
                    D.put(w, 1);
                }
            }
            line = reader.readLine();
        }
        reader.close();
        return D;
    }

    private static String stripChars(String s, String chars) {
        int start = 0;
        int end = s.length();
        while (start < end && chars.indexOf(s.charAt(start)) >= 0) {
            start++;
        }
        while (end > start && chars.indexOf(s.charAt(end - 1)) >= 0) {
            end--;
        }
        return s.substring(start, end);
    }

    public static Map<String, List<String>> buildAnagramDictionary(Map<String, Integer> D) {

        Map<String, List<String>> A = new HashMap<String, List<String>>();

        for (String w : D.keySet()) {
            String a = signature(w);
            if (!A.containsKey(a)) {
                List<String> newList = new ArrayList<String>();
                newList.add(w);
                A.put(a, newList);
            } else {
                A.get(a).add(w);
            }
        }
        return A;
    }

    public static void writeAnagramsFile(Map<String, List<String>> A, String path) throws IOException {

        PrintWriter f = new PrintWriter(new FileWriter(path));

        for (String key : A.keySet()) {
            List<String> group = A.get(key);

            if (group.size() > 1) {
                String anagram_list = "";
                for (int i = 0; i < group.size(); i++) {
                    if (anagram_list.equals("")) {
                        anagram_list = group.get(i);
                    } else {
                        anagram_list = anagram_list + " " + group.get(i);
                    }
                }

                f.print(anagram_list + "\\\\\n");
                for (int repeat = 0; repeat < group.size() - 1; repeat++) {
                    int space = anagram_list.indexOf(' ');
                    anagram_list = anagram_list.substring(space + 1) + ' ' + anagram_list.substring(0, space);
                    f.print(anagram_list + "\\\\\n");
                }
            }
        }
        f.close();
    }

    public static List<String> sortAnagramsFile(String path) throws IOException {
        List<String> lines = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
        while (line != null) {
            lines.add(line);
            line = reader.readLine();
        }
        reader.close();
        Collections.sort(lines);
        return lines;
    }

    public static void writeLatexOutput(List<String> sortedLines, String texPath, String tempFile) throws IOException {

        File latexDir = new File(texPath).getParentFile();
        if (latexDir != null && !latexDir.exists()) {
            latexDir.mkdirs();
        }

        PrintWriter tex = new PrintWriter(new FileWriter(texPath));
        char letter = 'X';

        for (int i = 0; i < sortedLines.size(); i++) {
            String lemma = sortedLines.get(i);
            if (lemma.isEmpty()) {
                continue;
            }
            char initial = lemma.charAt(0);
            if (Character.toLowerCase(initial) != Character.toLowerCase(letter)) {
                letter = initial;
                tex.println();
                tex.println("\\vspace{14pt}");
                tex.println("\\noindent\\textbf{\\Large " + Character.toUpperCase(letter) + "}\\\\*[+12pt]");
            }
            tex.println(lemma);
        }
        tex.close();

        try {
            Files.deleteIfExists(Paths.get(tempFile));
        } catch (IOException e) {
            System.out.println("couldnt delete temp file: " + tempFile);
        }
    }

    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.out.println("Usage: java Anagrams <inputfile>");
            System.exit(1);
        }

        String inputFile = args[0];
        System.out.println("Data file: " + inputFile);

        Map<String, Integer> D = readAndBuildFrequency(inputFile);
        System.out.println("unique words: " + D.size());

        Map<String, List<String>> A = buildAnagramDictionary(D);

        int groups = 0;
        for (List<String> g : A.values()) {
            if (g.size() > 1) {
                groups++;
            }
        }
        System.out.println("anagram groups found: " + groups);

        System.out.println("\nsome anagram groups:");
        int shown = 0;
        List<String> sortedKeys = new ArrayList<String>(A.keySet());
        Collections.sort(sortedKeys);
        for (String key : sortedKeys) {
            List<String> group = A.get(key);
            if (group.size() > 1) {
                System.out.println("  " + key + " -> " + group);
                shown++;
                if (shown >= 15) break;
            }
        }

        String tempFile = "anagrams";
        writeAnagramsFile(A, tempFile);

        List<String> sortedLines = sortAnagramsFile(tempFile);
        System.out.println("total lines: " + sortedLines.size());

        String texPath = "latex/theAnagrams.tex";
        writeLatexOutput(sortedLines, texPath, tempFile);
        System.out.println("done! latex file written to: " + texPath);
    }
}
