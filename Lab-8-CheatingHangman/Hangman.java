//Faiyaz Islam
//CIS 2168
//Project: Cheating Hangman
//This version of hangman will use data structures, mostly maps and sets, to keep the player from winning

import java.io.*;
import java.util.*;

public class Hangman {

    public static void main(String[] args) {

        String fileName = "words.txt";                          //word file
        boolean playAgain = true;                               //tracks if user wants to play again

        while(playAgain){

            Map<String, Integer> dictionary = new HashMap<>();   //holds all the words and their lengths
            Set<String> dictionarySet = new HashSet<>();         //only holds all the words
            boolean activeGame = true;                           //checks for an active game
            boolean solvedWord = false;                          //checks if word had been solved by user
            int wordSize = 0;                                    //size of the word


            try {
                //code that records all the possible words from the file
                Scanner scanner = new Scanner(new File(fileName));
                String word = scanner.next();

                while(scanner.hasNext()){
                    dictionary.put(word, word.length());
                    dictionarySet.add(word);
                    word = scanner.next();
                }
                scanner.close();

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }


            //asks the user for the size of the word they want, will keep asking until a valid size is given
            boolean wordSizeSet = false;
            while(!wordSizeSet){

                Scanner input = new Scanner(System.in);
                System.out.println("What size word would you like: ");
                Integer enteredSize = input.nextInt();

                if(dictionary.containsValue(enteredSize)){
                    wordSize = enteredSize;
                    wordSizeSet = true;
                }
                else{
                    System.out.println("Size not found. Pick another");
                }
            }


            //this set will hold all the words that fit the given length of the word
            HashSet<String> wordList = new HashSet<>();
            for(String value : dictionarySet){
                if(value.length() == wordSize){
                    wordList.add(value);
                }
            }


            //asks the user for the number of guesses
            Scanner input3 = new Scanner(System.in);
            System.out.println("How many guesses do you want:");
            int guessNumber = input3.nextInt();


            //these 2 sets will hold the good guesses and the bad guesses
            Set<Character> goodGuesses = new HashSet<>();
            Set<Character> badGuesses = new HashSet<>();

            //this string will be the placeholder for the gaps and letters in the hidden word
            String status = "";
            for(int i = 0; i < wordSize; i++){
                status += "_";
            }


            //initial prints of the data
            System.out.println("Word: " + status);
            System.out.println("Guesses: " + badGuesses);
            System.out.println("Turns left: " + guessNumber);


            //loop is only active while the game is still ongoing and the number of guesses is not 0
            while(activeGame && (guessNumber >= 0)){

                char inputLetter;                                               //the player's guess
                Map<String, HashSet<String>> wordFamilies = new HashMap<>();    //the map that will hold the word families
                HashSet<String> bestWordFamily = new HashSet<>();               //the set that will hold the largest word family
                String hiddenWord = "";                                         //the string for the hidden word, used to create word families


                //this while loop ensures that the guess has not been entered already
                while(true){
                    Scanner input4 = new Scanner(System.in);
                    System.out.println("Enter a letter: ");
                    inputLetter = input4.nextLine().toLowerCase().charAt(0);

                    if(badGuesses.contains(inputLetter) || goodGuesses.contains(inputLetter)){
                        System.out.println("Already entered. Try another letter.");
                    }
                    else{
                        break;
                    }
                }


                //this section creates word families from the guess
                for(String word : wordList){

                    String wordFamily = "";

                    //loop here creates a word family made out of gaps and letters
                    for(Character c : word.toCharArray()){
                        if(c == inputLetter){
                            wordFamily += c;
                        }else{
                            wordFamily += "_";
                        }
                    }

                    //if word family is not already in the map that contains word families, a new set is created for it, else it is added
                    if(!wordFamilies.containsKey(wordFamily)){
                        wordFamilies.put(wordFamily, new HashSet<>());
                    }else{
                        wordFamilies.get(wordFamily).add(word);
                    }
                }


                //goes through each of the word families and finds the largest one
                for(String wordFamily : wordFamilies.keySet()){
                    if(wordFamilies.get(wordFamily).size() > bestWordFamily.size()){
                        hiddenWord = wordFamily;                            //hidden word is set to the word family
                        bestWordFamily = wordFamilies.get(wordFamily);      //largest word family is set
                    }
                }


                //the word list is now replaced by the largest word family
                wordList = bestWordFamily;


                //if the hidden word does not contain the guess, then it will be recorded and the number of guesses will decrement
                if(!hiddenWord.contains(""+inputLetter)){
                    System.out.println("Bad guess");
                    badGuesses.add(inputLetter);
                    guessNumber--;

                //if the hidden word does have the letter, then the status is recreated to print the word with the letter now
                }else{
                    System.out.println("Good guess");
                    goodGuesses.add(inputLetter);
                    status = "";
                    for(char c : wordList.toArray()[0].toString().toCharArray()){
                        if(goodGuesses.contains(c)){
                            status += c;
                        }else{
                            status += "_";
                        }
                    }
                }


                System.out.println("Word: " + status);
                System.out.println("Guesses: " + badGuesses);
                System.out.println("Turns left: " + guessNumber);


                //if there is only one word left and the status has no gaps, then the word has been solved
                if(wordList.size() == 1 && !status.contains("_")){
                    solvedWord = true;
                }

                //conditions where the player has won
                if(solvedWord && guessNumber > 0){
                    System.out.println("YOU WON!");
                    System.out.println("The word was:" + wordList);
                    activeGame = false;
                }

                //conditions where the player loses
                if(!solvedWord && (guessNumber == 0)){
                    System.out.println("YOU LOSE!");
                    System.out.println("The word was: " + wordList.toArray()[0]); //prints out the "first" word in the set, which will be random
                    activeGame = false;
                }
            }


            //this section asks the player for another round, it will end the game if N/n is entered
            Scanner input2 = new Scanner(System.in);
            System.out.println("Do you want to play again? Enter Letter (N/n for NO):");
            String playAgainInput = input2.nextLine();
            if(playAgainInput.equals("N") || playAgainInput.equals("n")){
                playAgain = false;
                break;
            }
            else{
                System.out.println("LETS PLAY AGAIN");
            }
        }
    }
}
