package yuliatestprojects;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

            File file = new File("Test_speeches\\Ze_speech.txt");

wordsFrequency(textIntoWords(readFile(file)), 5);

    }


    public static String textIntoWords(String text) {
        ArrayList<String> words = new ArrayList<>();
       List<String> wordsList = Arrays.asList(text
                .toLowerCase()
                .replaceAll("[-–!?«»\"\",:().(0-9)]", "")
                .split("\\s+"));

        for (String word : wordsList)
        {
           if (word.length() > 3) {
               words.add(word);
           }
        }
        return words.toString();
    }

    public static void wordsFrequency(String string, int limit) {

        String[] input = string.replaceAll("[,\\[\\]]", "").split(" ");

        Map<String, Integer> pairs = new HashMap<>();
        LinkedHashMap<String, Integer> output = new LinkedHashMap<>();

        for (int i = 0; i < input.length; i++) {
            if (pairs.containsKey(input[i])) {
                pairs.put(input[i], pairs.get(input[i]) + 1);
            }
            else {
                pairs.put(input[i], 1);
            }
        }
        List<Integer> sortedValues = new ArrayList<>(pairs.values());
        Collections.sort(sortedValues, Collections.reverseOrder());

        for (int value : sortedValues) {
            for (Map.Entry<String, Integer> entry : pairs.entrySet()) {
                if (value == entry.getValue()) {
                    output.put(entry.getKey(), value);
                }
            }
        }
        int count = 0;
        Iterator keysIt = output.keySet().iterator();
        Iterator valuesIt = output.values().iterator();
        System.out.println("The most common word(s) are: ");
        while(count < limit){
            System.out.println(keysIt.next() + " - " + valuesIt.next());
            count++;
        }
    }


    public static String readFile (File file) {

        StringBuilder text = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file))
        ) {
            String line = "";
            while ((line = br.readLine()) != null) {
                text.append(line + " ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }
}
