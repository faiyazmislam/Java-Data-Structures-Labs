//Faiyaz Islam
//CIS 2168
//Project: ArrayList Assignments
//The methods in this file use ArrayLists to complete specific actions

import java.util.*;

public class ArrayListAssignment {

    public static void main(String[] args) {

        Integer i = 5;
        System.out.println(i);
        List<String> uniqueTest1 = new ArrayList<>();{
            uniqueTest1.add("cheese");
            uniqueTest1.add("34");
            uniqueTest1.add("cheese");
            uniqueTest1.add("parmesian");
            uniqueTest1.add("swiss");
        }
        System.out.println(uniqueTest1);
        System.out.println("Uniqueness of the set: " + uniqueness(uniqueTest1));

        List<Integer> uniqueTest2 = new ArrayList<>();{
            uniqueTest2.add(78);
            uniqueTest2.add(8);
            uniqueTest2.add(38);
            uniqueTest2.add(230);
        }
        System.out.println(uniqueTest2);
        System.out.println("Uniqueness of the set: " + uniqueness(uniqueTest2));

        List<Integer> multiplesTest1 = new ArrayList<>();{
            multiplesTest1.add(2);
            multiplesTest1.add(2);
            multiplesTest1.add(25);
            multiplesTest1.add(5);
            multiplesTest1.add(30);
            multiplesTest1.add(2);
            multiplesTest1.add(19);
            multiplesTest1.add(2);
            multiplesTest1.add(2);
            multiplesTest1.add(57);
            multiplesTest1.add(2);
        }
        System.out.println("The multiples are: " + allMultiples(multiplesTest1, 5));

        List<String> stringSizeTest1 = new ArrayList<>();{
            stringSizeTest1.add("watermelon");
            stringSizeTest1.add("cherry");
            stringSizeTest1.add("apple");
            stringSizeTest1.add("grape");
            stringSizeTest1.add("lemon");
            stringSizeTest1.add("orange");
        }
        System.out.println("All strings of size 5: " + allStringsOfSize(stringSizeTest1, 5));

        List<String> set1 = new ArrayList<>();{
            set1.add("milk");
            set1.add("water");
            set1.add("water");
            set1.add("3");
            set1.add("3");
            set1.add("88");
            set1.add("water");
        }
        List<String> set2 = new ArrayList<>();{
            set2.add("water");
            set2.add("water");
            set2.add("water");
            set2.add("milk");
            set2.add("3");
            set2.add("88");
            set2.add("3");
        }
        System.out.println("Are the lists permutations: " + isPermutations(set1, set2));

        String wordListTest1 = "Red,Orange $$$$Yellow, G*R*E*E*N, (Blue) Indigo???? Violet!!!! Pink.";
        System.out.println("List of words: " + stringToListOfWords(wordListTest1));

        removeAllInstances(uniqueTest1, "34");
        System.out.println("Cheese only list: " + uniqueTest1);

        removeAllInstances(multiplesTest1, 2);
        System.out.println("No more 2s :" + multiplesTest1);
    }

    public static <E> boolean uniqueness(List<E> list){

        boolean unique = true; //the unique variable is set to true by default

        for(int i = 0; i < list.size(); i++){       //loop that picks an element and compares it to every other element
            for(int j = 0; j < list.size(); j++){   //inner loop for the comparisons
                if(i != j ){                        //ensures that the chosen element does not get compared to itself
                    if(list.get(i).equals(list.get(j))){ //the elements are called and if they are equal, unique is set to false
                        unique = false;
                    }
                }
            }
        }

        return unique;
    }

    public static List<Integer> allMultiples(List<Integer> list, int x){

        List<Integer> multiples = new ArrayList<>(); //creates a new list for the multiples

        for(int i = 0; i < list.size(); i++){   //loop that goes through each element
            if(list.get(i)%x == 0){             //gets the element and checks if it is divisible by the given int
                multiples.add(list.get(i));     //adds to the multiples list if it satisfies the condition
            }
        }

        return multiples;
    }

    public static List<String> allStringsOfSize(List<String> list, int length){

        List<String> correctSize = new ArrayList<>();   //creates new list for the satisfying strings

        for (int i = 0; i < list.size(); i++) {     //loops through each of the stirngs
            if (list.get(i).length() == length) {   //gets the length of the string and compares to the given int
                correctSize.add(list.get(i));       //if the size is right, it is added to the new list
            }
        }

        return correctSize;
    }

    public static <E> boolean isPermutations(List<E> list1, List<E> list2) {

        if (list1.size() != list2.size()) { //checking that both lists are at least the same size
            return false;
        }

        for(E elementOfList1 : list1){      //loop that goes through each element of the first list

            int elementCountList1 = 0;      //for recording the amount of one element in list 1
            int elementCountList2 = 0;      //for recording the amount of one element in list 2

            for(int j = 0; j < list1.size(); j++){        //loop that goes through and records the amount of the element in list 1
                if(elementOfList1.equals(list1.get(j))){  //if it is the same element, added to the total
                    elementCountList1++;
                }
            }

            for(int k = 0; k < list2.size(); k++){        //loop that goes through and records the amount of that element in list 2
                if(elementOfList1.equals(list2.get(k))){  //if it is the same element, added to the total
                    elementCountList2++;
                }
            }

            if(elementCountList1 != elementCountList2){ //if the lists are permutations, they need to have the same amount of each element
                return false;
            }
        }
        return true;
    }

    public static List<String> stringToListOfWords(String givenString){
        //webpage I used to help me figure out how to use the split() method: https://www.geeksforgeeks.org/split-string-java-examples/
        //webpage I used to help me remove punctuation from the words with the replaceAll method: 
        //https://www.geeksforgeeks.org/how-to-remove-all-non-alphanumeric-characters-from-a-string-in-java/
        List<String> listOfWords = new ArrayList<>();   //new list
        int limit = 1;                                  //limit is set to 1 so the loop doesn't miss the last space

        for(int i = 0; i < givenString.length(); i++){  //loop that checks and adds the number of spaces to the limit
            if(givenString.charAt(i) == ' '){
                limit++;
            }
        }

        String[] wordsPlaceholder = givenString.split(" ", limit);  //creates a string array that will hold the
                                                                          //return value of the split method

        for(int i = 0; i < wordsPlaceholder.length; i++){   //loop that goes through the placeholder array
            String removedPunctuation = wordsPlaceholder[i].replaceAll("[^a-zA-Z0-9]", "");
                            //the replaceAll method removes all chars that are not letters or numbers,
                            //each word from the array uses this method and then is added to a placeholder string
            listOfWords.add(removedPunctuation);           //adds each string to the list
        }

        return listOfWords;
    }

    public static <E> void removeAllInstances(List<E> list, E item){

        for(int i = list.size() - 1; i >= 0 ; i--){ //loop goes through each element of the lis, starting at the end
            if(list.get(i).equals(item)){           //checks if the element is equal to the given item
                list.remove(i);                     //if it is the same, then it is removed from the list
            }
        }
    }

}
