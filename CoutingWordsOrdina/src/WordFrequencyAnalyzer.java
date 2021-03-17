import java.util.LinkedHashMap;

public interface WordFrequencyAnalyzer {
	int calculateHighestFrequency(String text);
    int calculateFrequencyForWord(String text, String word);
    LinkedHashMap<String, Integer> calculateMostFrequentNWords(String text, int n);
}
