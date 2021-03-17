import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//import com.sun.tools.classfile.CharacterRangeTable_attribute.Entry;

public class Analyzer implements WordFrequencyAnalyzer {
	static ArrayList<Integer> freq = new ArrayList<Integer>(); 
	static ArrayList<String> words = new ArrayList<String>();
	static Map<String, Integer> pairList = new HashMap<>();

	public static void main(String[] args) {
		String inputText = "This is a text text TExt TEXT";
		inputText = inputText.toLowerCase();
		
		// Create a class of itself
		Analyzer analyzer = new Analyzer();
		int highest = analyzer.calculateHighestFrequency(inputText);
        int frequency = analyzer.calculateFrequencyForWord(inputText, "is");
        LinkedHashMap<String, Integer> nMostFrequent = analyzer.calculateMostFrequentNWords(inputText, 2);
        System.out.println(highest);
        System.out.println(frequency);
        System.out.println(nMostFrequent);

	}

	
	//  returns the highest frequency in the text (several words might have this frequency)
	@Override
	public int calculateHighestFrequency(String text) {
		getWordFrequencyArrays(text);
		return Collections.max(freq);
	}

	
	//  returns the frequency of the specified word
	@Override
	public int calculateFrequencyForWord(String text, String word) {
		int idx = words.indexOf(word); 
		return freq.get(idx);
	}

	
	/* 
	 * returns a list of the most frequent "n" words in the input text, all 
	 * the words returned in lower case. If several words have the same
	 * frequency, this method should return them in ascendant alphabetical
	 * order (for input text “The sun shines over the lake” and n = 3, it
	 * should return the list {(“the”, 2), (“lake”, 1), (“over”, 1) }  
	 * 
	 * I'm not completely sure how I should use the WordFrequency Interface 
	 * and Frequency class in here, so I slightly changed the function to
	 * make it work another way.
	 */
	@Override
//	public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
	public LinkedHashMap<String, Integer> calculateMostFrequentNWords(String text, int n) {
		
		// I know I should return this type of list, but it doens't work...
//		List<WordFrequency> nPairList = new List<WordFrequency>();
        
		// Sort the hashmap of words and frequencies in reverse order (from large to small)
        LinkedHashMap<String, Integer> reverseSortedList = new LinkedHashMap<>();   
        pairList.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
                .thenComparing(Map.Entry.comparingByKey())).forEachOrdered(x -> reverseSortedList.put(x.getKey(), x.getValue()));
        
        // Get the "n" most frequent words
        LinkedHashMap<String, Integer> nPairList = new LinkedHashMap<>();
        reverseSortedList.entrySet().stream().limit(n).forEach(x -> nPairList.put(x.getKey(), x.getValue()));
        
		return nPairList;
	}
	
	public void getWordFrequencyArrays(String text) {
	    
	    String[] splitter = text.split("\\s+");
		for(int i = 0; i < splitter.length; i++) {
			if(words.contains(splitter[i])) {
				int idx = words.indexOf(splitter[i]);
				// Increase the frequency
				freq.set(idx, freq.get(idx) + 1);
			} else {
				// If not, set the frequency to 1
				words.add(splitter[i]);
				freq.add(1);
			}
		}
		
		// Put words and corresponding frequencies in hashmap
		for(int j = 0; j < words.size(); j++) {
			pairList.put(words.get(j), freq.get(j));
		}
		
	    return;
	}

}
