import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Frequency implements WordFrequency {
	static String inputText = "This is a text text TExt TEXT";

	public static void main(String[] args) throws IOException {
		
		// Create a class of itself
		Frequency wordFreq = new Frequency();
        String word = wordFreq.getWord();
        int freq = wordFreq.getFrequency();
        
        Map<String, Integer> pairList = new HashMap<>();
        pairList.put(word, freq);
    
	}

	// Create ArrayList for all words and returns the first word
	@Override
	public String getWord() {
		ArrayList<String> words = new ArrayList<String>();
		
		String[] splitter = inputText.split("\\s+");
		for(int i = 0; i < splitter.length; i++) {
			// Determine if the next word isn't already in the ArrayList and add it
			if(!words.contains(splitter[i])) {
				words.add(splitter[i]);
			}
		}
		System.out.println(words.get(0));
		return words.get(0);
	}

	
	// Create ArrayList for all frequencies and returns the first frequency
	@Override
	public int getFrequency() {
		
		ArrayList<Integer> freq = new ArrayList<Integer>(); 
		ArrayList<String> words = new ArrayList<String>();
		
		String[] splitter = inputText.split("\\s+");
		for(int i = 0; i < splitter.length; i++) {
			if(words.contains(splitter[i])) {
				int idx = words.indexOf(splitter[i]);
				// Increase the frequency
				freq.set(idx, freq.get(idx) + 1);
			} else {
				// If not, set the frequency to 1
				freq.add(1);
			}
		}
		System.out.println(freq.get(0));
		return freq.get(0);
	}

}
