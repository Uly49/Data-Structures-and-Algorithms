import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class WordLadder {

    public static void main(String[] args) throws FileNotFoundException {
        // Initialize variables
        String start, end;
        Scanner in = new Scanner(System.in);
        HashMap<String, WordNode> wordlist = new HashMap<String, WordNode>();
        String fileName = ""; // Name of the file containing words of a given length

        // Read in the two words
        System.out.println("Enter the beginning word:");
        start = in.next().toLowerCase();
        System.out.println("Enter the ending word:");
        end = in.next().toLowerCase();

        // Check length of the words
        int length = start.length();
        if (length != end.length()) {
            System.err.println("ERROR! Words are not of the same length.");
            return;
        }

        // Choose the appropriate file based on the length of the words
        fileName = "WordLadder Data Files/words." + length;

        // Read in the words from the file
        Scanner wordFile = new Scanner(new File(fileName));
        while (wordFile.hasNext()) {
            String word = wordFile.next().toLowerCase();
            wordlist.put(word, new WordNode(word));
        }
        wordFile.close();

        // Check if the start and end words are in the wordlist
        if (!wordlist.containsKey(start)) {
            System.err.println("ERROR! The starting word is not in the word list.");
            return;
        }
        if (!wordlist.containsKey(end)) {
            System.err.println("ERROR! The ending word is not in the word list.");
            return;
        }

        // Build the graph
        buildGraph(wordlist);

        // Find the shortest path using BFS
        ArrayList<String> ladder = findShortestPath(wordlist.get(start), wordlist.get(end));

        // Print the ladder
        if (ladder == null) {
            System.out.println("No word ladder found.");
        } else {
            System.out.print(ladder.get(0));
            for (int i = 1; i < ladder.size(); i++) {
                System.out.print(" -> " + ladder.get(i));
            }
        }

    }

    // Build the graph
    public static void buildGraph(HashMap<String, WordNode> wordlist) {
        // Iterate through each WordNode in the HashMap
        for (WordNode node : wordlist.values()) {
            // Get the word for this WordNode
            String word = node.getWord();
            // Convert the word to an array of characters
            char[] letters = word.toCharArray();
            // Iterate through each character in the word
            for (int i = 0; i < letters.length; i++) {
                // Save the original character
                char original = letters[i];
                // Iterate through each letter of the alphabet
                for (char c = 'a'; c <= 'z'; c++) {
                    // If this letter is not the same as the original character
                    if (c != original) {
                        // Replace the original character with this new letter
                        letters[i] = c;
                        // Convert the modified character array back to a String
                        String newWord = new String(letters);
                        // If the modified String exists in the wordlist HashMap
                        if (wordlist.containsKey(newWord)) {
                            // Add the WordNode associated with the modified String as a neighbor of the
                            // current WordNode
                            node.addNeighbor(wordlist.get(newWord));
                        }
                    }
                }
                // Restore the original character for the next iteration
                letters[i] = original;
            }
        }
    }

    // Find the shortest path using BFS
    public static ArrayList<String> findShortestPath(WordNode start, WordNode end) {
        // Create a queue to hold the WordNodes to be processed, and a HashMap to keep
        // track of the parent nodes
        Queue<WordNode> queue = new LinkedList<>();
        HashMap<WordNode, WordNode> parent = new HashMap<>();
        // Add the start node to the queue and set its parent to null
        queue.offer(start);
        parent.put(start, null);

        // While there are still WordNodes in the queue
        while (!queue.isEmpty()) {
            // Get the next WordNode from the queue
            WordNode current = queue.poll();
            // If this WordNode is the end node, return the shortest path
            if (current.equals(end)) {
                return getPath(parent, end);
            }
            // Otherwise, iterate through each of the current node's neighbors
            for (WordNode neighbor : current.getNeighbors()) {
                // If this neighbor has not already been processed
                if (!parent.containsKey(neighbor)) {
                    // Add it to the queue and set its parent to the current node
                    queue.offer(neighbor);
                    parent.put(neighbor, current);
                }
            }
        }
        // If no path was found, return null
        return null;

    }

    // Get the path from start to end using parent map
    public static ArrayList<String> getPath(HashMap<WordNode, WordNode> parent, WordNode end) {
        // Create a new ArrayList to store the path
        ArrayList<String> path = new ArrayList<>();
        // Start at the end node
        WordNode current = end;
        // While the current node is not null
        while (current != null) {
            // Add the current node's word to the beginning of the path
            path.add(0, current.getWord());
            // Set the current node to its parent node
            current = parent.get(current);
        }
        // Return the completed path
        return path;
    }
}

// WordNode class represents a node in the graph, with a word and a list of
// neighboring WordNodes
class WordNode {
    // Private member variables for the word and neighboring WordNodes.
    private String word;
    private ArrayList<WordNode> neighbors;

    // Constructor for WordNode class.
    public WordNode(String word) {
        // Initialize the word and neighboring WordNodes.
        this.word = word;
        this.neighbors = new ArrayList<>();
    }

    // Getter method for the word.
    public String getWord() {
        return this.word;
    }

    // Getter method for the neighboring WordNodes.
    public ArrayList<WordNode> getNeighbors() {
        return this.neighbors;
    }

    // Method for adding a neighboring WordNode to the list of neighbors.
    public void addNeighbor(WordNode node) {
        this.neighbors.add(node);
    }
}