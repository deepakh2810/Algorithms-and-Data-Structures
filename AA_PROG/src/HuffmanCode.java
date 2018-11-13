//Algorithm to implement Huffman code by taking an input from a text file.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

// node class for Huffman code implementation
class HuffmanNode {

	int value;
	char c;

	HuffmanNode left;
	HuffmanNode right;
}


//compare value values at nodes
class MyComparator implements Comparator<HuffmanNode> {
	public int compare(HuffmanNode a, HuffmanNode b) {

		return a.value - b.value;
	}
}

public class HuffmanCode {

	//HashMap to store the occurance of the character in the formatter 32 character String.
	static HashMap<Character, Integer> chartoFreqmap = new HashMap<Character, Integer>();
	static int huffmanCodeLength = new Integer(0);

	public static void printCode(HuffmanNode root, String s) {

		// base case
		if (root.left == null && root.right == null) {
            // c is the character in the node
			System.out.println(root.c + ":" + s);
			//Huffman code bit length calculation.
			huffmanCodeLength += s.length() * chartoFreqmap.get(root.c);
			return;
		}

		// if we go to left then add "0" to the code.
		// if we go to the right add"1" to the code.
		// recursive calls for left and
		// right sub-tree of the generated tree.
		printCode(root.left, s + "0");
		printCode(root.right, s + "1");
	}

	//Parsing and extracting the frequencies of characters
	static int freqCalculate(char c, String input) {

		int frequency = 0;

		for (int i = 0; i < input.length(); i++) {
			if (c == input.charAt(i)) {
				++frequency;
			}
		}
		System.out.println("Frequency of " + c + " = " + frequency);
		return frequency;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new FileReader("/Users/dhanuman/Documents/CSCI-505_WORKSPACE/AA_PROG/src/pg37581.txt"));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		while (line != null) {
			sb.append(line).append("\n");
			line = br.readLine();
		}

		String fileAsString = sb.toString();
		String formattedString = fileAsString.replaceAll("[^a-z ,.!?']", "");

		
		char[] charArray = { ' ', '!', '\'', ',', '.', '?', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
				'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		int frequency;
		int[] freq = new int[32];
		for (int i = 0; i < 32; i++) {
			frequency = freqCalculate(charArray[i], formattedString);
			freq[i] = frequency;
			//Using hashmap to map frequencies to characters. 
			chartoFreqmap.put(charArray[i], frequency);

		}
		//calculating the fixed length by multiplying 5 with length of String because we are using 32 characters, which is 2^5 and occurances of characters is equal to the number of characters.
		int fixedLength=5*formattedString.length();
		System.out.println("Huffman Codes for individual Characters:");

		//used 32 since we are using just 32 characters. 
		PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(32, new MyComparator());
        
		
		//used 32 since we are using just 32 characters. 
		for (int i = 0; i < 32; i++) {

			// creating a huffman node objectand adding it to the priority-queue.
			HuffmanNode hn = new HuffmanNode();
			hn.c = charArray[i];
			hn.value = freq[i];
			hn.left = null;
			hn.right = null;
			q.add(hn);
		}

		// create a root node
		HuffmanNode root = null;

		// Here we will extract the minimum value from nodes
		while (q.size() > 1) {
            // first min extract.
			HuffmanNode x = q.peek();
			q.poll();
			HuffmanNode y = q.peek();
			q.poll();
			HuffmanNode f = new HuffmanNode();
            f.value = x.value + y.value;
			f.c = '-';
			f.left = x;
			f.right = y;
			root = f;
			q.add(f);
		}

		
		printCode(root, "");
		System.out.println("Huffman Coding Bits used: "+huffmanCodeLength);
		System.out.println("Fixed Length Bits used: "+fixedLength);
		System.out.println("Bits Saved: "+ (fixedLength-huffmanCodeLength));

	}

}
