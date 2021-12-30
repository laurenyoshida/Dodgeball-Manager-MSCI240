//Name: Lauren Yoshida
//Assignment: Assignment 2
//Date: Oct 27, 2021
//Description: The dodgeball manager focuses on implementing and using linked lists, with a dodgeball variant called "Circle Dodgeball"
//Input: The Dodgeball manager is used and called in the Dodgeball main, it manages the game, through the user input from the main.
//Output: This class outputs relevant information for the game of dodgeball called in the main, this includes adding scores, displaying relevant information by printing out the throwers, dodgers, winner, and sorted scores at the end of the game as well as swaping positions in the case a thrower hits a dodger. 

import java.io.PrintStream;
import java.util.List;

public class DodgeballManager {
    DodgeballNode throwerFirstNode = null;
    DodgeballNode dodgerFirstNode = null;

    //Dodgeball Manager constructor with parameters of a string list of throwers and initial dodgers
    public DodgeballManager(List<String> initialThrowers, List<String> initialDodgers) {
        //If statement to check if either list is null or of size 0 (empty)
        if (initialThrowers == null || initialDodgers == null || initialThrowers.isEmpty() || initialDodgers.isEmpty()) {
            //Throwing illegal argument exception
            throw new IllegalArgumentException();
        }

        //Create the first node the thrower and dodger Dodgeball node list and give them a value of the first item (item 0) of the list
        throwerFirstNode = new DodgeballNode(initialThrowers.get(0));
        dodgerFirstNode = new DodgeballNode(initialDodgers.get(0));

        //Assign reference nodes to initialize the rest of the linked list (for both thrower and dodger)
        DodgeballNode tempThrower = throwerFirstNode;
        DodgeballNode tempDodger = dodgerFirstNode;

        //For loop to create the dodgeball linked list, starts at the second node (first one was intialized above) and continues until all throwers have been assigned a node
        for (int x = 1; x < initialThrowers.size(); x++) {
            //Creating new dodgeball node with corresponding name from the list
            tempThrower.next = new DodgeballNode(initialThrowers.get(x));
            //Move pointer to the next node
            tempThrower = tempThrower.next;
        }


        //For loop to create the dodgeball linked list, starts at the second node (first one was intialized above) and continues until all dodgers have been assigned a node
        for (int x = 1; x < initialDodgers.size(); x++) {
            //Creating new dodgeball node with corresponding name from the list
            tempDodger.next = new DodgeballNode(initialDodgers.get(x));
            //Move pointer to the next node
            tempDodger = tempDodger.next;
        }
    }

    //Method to print throwers using a printstream parameter
    public void printThrowers(PrintStream stream) {
        //Creating a new reference node to go through the throwers linked list
        DodgeballNode tempThrowers = throwerFirstNode;
        //While loop to go through the entire list but the last node (last node will have a next of node)
        while (tempThrowers.next != null) {
            //Printing output with name and score in the deisred format (comma at the end)
            stream.print(tempThrowers.name + " " + tempThrowers.score + ", ");
            //Move reference to the next node
            tempThrowers = tempThrowers.next;
        }
        //Printing output for the last node without a comma
        stream.print(tempThrowers.name + " " + tempThrowers.score);
    }

    //Method to print dodgers using a printstream parameter
    public void printDodgers(PrintStream stream) {
        //Creating a new reference node to go through the throwers linked list
        DodgeballNode tempDodgers = dodgerFirstNode;
        //While loop to go through the entire list but the last node (last node will have a next of node)
        while (tempDodgers.next != null) {
            //Printing output with name and score in the deisred format (comma at the end)
            stream.print(tempDodgers.name + " " + tempDodgers.score + ", ");
            //Move reference to the next node
            tempDodgers = tempDodgers.next;
        }
        //Printing output for the last node without a comma
        stream.print(tempDodgers.name + " " + tempDodgers.score);
    }

    //Method to print dodgers using a printstream parameter by finding the winner's name (if there is more than one winner an illegal state exception is thrown)
    public void printWinner(PrintStream stream) {
        //Initializing an empty string for the name of the winner
        String name = "";
        //Initializing and declaring the score of the winner through the maximum score method (the winner will have the maximum score)
        int n = getMaximumScore();
        //Initializing a counter to check if there is more than one winner
        int maxScoreCount = 0;

        //Creating a new reference nodes to go through the throwers and dodgers linked list
        DodgeballNode tempThrowers = throwerFirstNode;
        DodgeballNode tempDodgers = dodgerFirstNode;

        //While loop to go through the entire list it will stop when the node does not exist
        while (tempThrowers != null) {
            //If statement to check if the current thrower has the maximum score
            if (n == tempThrowers.score) {
                //String Name gets the name of the thrower 
                name = tempThrowers.name;
                //Increase the counter by one to tally the number of players with the maximum points (who all should be winners)
                maxScoreCount++;
            }
            //Move reference to the next node
            tempThrowers = tempThrowers.next;
        }
        
        //While loop to go through the entire list it will stop when the node does not exist
        while (tempDodgers != null) {
            //If statement to check if the current dodger has the maximum score
            if (n == tempDodgers.score) {
                //String Name gets the name of the dodger 
                name = tempDodgers.name;
                //Increase the counter by one to tally the number of players with the maximum points (who all should be winners)
                maxScoreCount++;
            }
            //Move reference to the next node
            tempDodgers = tempDodgers.next;
        }

        //If statement to check if there are more than one player who has the max score (which means that there are more than one winner)
        if (maxScoreCount > 1) {
            //Throwing illegal state exception
            throw new IllegalStateException();
        }

        //Printing output with the winner's name and number of points
        stream.print("The winner is " + name + " with " + n + " points");
    }

    //Boolean method to check if a given player is in the thrower's linked list (ignores case) with the player's name as the parameter
    public boolean throwersContains(String name) {
        //Creating a new reference node to go through the throwers linked list
        DodgeballNode tempThrowers = throwerFirstNode;

        //While loop to go through the entire list it will stop when the node does not exist
        while (tempThrowers != null) {
            //If statement to check if at the given node the name is the name in the parameter ignoring the case
            if (tempThrowers.name.equalsIgnoreCase(name)) {
                //Return true if the name in the linked list is the same as the parameter
                return true;
            }
            //Move reference to the next node
            tempThrowers = tempThrowers.next;
        }
        //If name is not found return false
        return false;
    }

    //Boolean method to check if a given player is in the dodger's linked list (ignores case) with the player's name as the parameter
    public boolean dodgersContains(String name) {
        //Creating a new reference node to go through the dodgers linked list
        DodgeballNode tempDodgers = dodgerFirstNode;
        //While loop to go through the entire list it will stop when the node does not exist
        while (tempDodgers != null) {
            //If statement to check if at the given node the name is the name in the parameter ignoring the case
            if (tempDodgers.name.equalsIgnoreCase(name)) {
                //Return true if the name in the linked list is the same as the parameter
                return true;
            }
            //Move reference to the next node
            tempDodgers = tempDodgers.next;
        }
        //If name is not found return false
        return false;
    }

    //Method managing the changes in the game when a thrower hits a dodger, the throwers score increases and swaps places with the dodger in the relevant place in the linked list - throws and illegal exception if the parameters (thrower name and dodger name) are null, empty or not in the relevant linked list
    public void hit(String throwerName, String dodgerName) {

        //If statement to check if the thrower name is not in the throwers linked list, the dodger name is not in the dodgers linked list, or if either name is empty or null
        if (!throwersContains(throwerName) || !dodgersContains(dodgerName) || throwerName == null || throwerName.isEmpty() || dodgerName == null || dodgerName.isEmpty()) {
            //Throwing illegal argument exception
            throw new IllegalArgumentException();
        }

        //Creating a new reference nodes to go through the throwers linked list, the temp represents the current node, after is the node after the current and before is the one before the current node
        DodgeballNode tempThrowers = throwerFirstNode;
        DodgeballNode afterThrower = tempThrowers.next;
        DodgeballNode beforeThrower = null;

        //While loop that continues until it reaches the thrower's name making sure that it is not an empty node that points to null, when the while loop ends temp will be the thrower node from the thrower name input
        while (!tempThrowers.name.equalsIgnoreCase(throwerName) && tempThrowers.next != null) {
            //Assigning the before thrower to the temp (current) thrower before the temp reference moves to the next node
            beforeThrower = tempThrowers;
            //Move reference to the next node
            tempThrowers = tempThrowers.next;
        }
        //Thrower's score increases by 1 
        tempThrowers.score++;
        //Assigning the after thrower to be the next node after the thrower which will be the thrower who hits the dodger
        afterThrower = tempThrowers.next;

        //Creating a new reference nodes to go through the dodgers linked list, the temp represents the current node, after is the node after the current and before is the one before the current node
        DodgeballNode tempDodgers = dodgerFirstNode;
        DodgeballNode afterDodger = null;
        DodgeballNode beforeDodger = null;

        //While loop that continues until it reaches the dodger's name making sure that it is not an empty node that points to null, when the while loop ends temp will be the dodger node from the dodger name input
        while (!tempDodgers.name.equalsIgnoreCase(dodgerName) && tempDodgers.next != null) {
            //Assigning the before dodger to the temp (current) dodger before the temp reference moves to the next node
            beforeDodger = tempDodgers;
            //Move reference to the next node
            tempDodgers = tempDodgers.next;
        }
        //Assigning the after dodger to be the next node after the dodger which will be the dodger who hits the dodger
        afterDodger = tempDodgers.next;

        //The following if/else if/else statement is used to determine how the nodes are being rearranged - if any nodes are the first node in the given list the dodgerFirstNode or throwerFirstNode and has to be reassigned a new value

        //both the dodger and thrower are in the first nodes in their respective lists
        if (dodgerFirstNode.name.equalsIgnoreCase(dodgerName) && throwerFirstNode.name.equalsIgnoreCase(throwerName)) {
            //Rearranging the dodger node by assigning the first node to the thrower node and then reattaching the dodger list through the .next = after statement
            dodgerFirstNode = tempThrowers;
            tempThrowers.next = afterDodger;
            //Rearranging the thrower node by assigning the first node to the dodger node and then reattaching the thrower list through the .next = after statement
            throwerFirstNode = tempDodgers;
            tempDodgers.next = afterThrower;

        } else if (dodgerFirstNode.name.equalsIgnoreCase(dodgerName)) {
        //If the dodger is the first node in the list
            //Rearranging the dodger node by assigning the first node to the thrower node and then reattaching the dodger list through the .next = after statement
            dodgerFirstNode = tempThrowers;
            tempThrowers.next = afterDodger;
            //Rearranging the thrower node by assigning the node after the before node to the dodger node and then reattaching the thrower list through the .next = after statement
            beforeThrower.next = tempDodgers;
            tempDodgers.next =  afterThrower;

        } else if (throwerFirstNode.name.equalsIgnoreCase(throwerName)) {
        //If the thrower is the first node in the list
            //Rearranging the thrower node by assigning the first node to the dodger node and then reattaching the thrower list through the .next = after statement
            throwerFirstNode = tempDodgers;
            tempDodgers.next = afterThrower;
            //Rearranging the dodger node by assigning the node after the before node to the thrower node and then reattaching the dodger list through the .next = after statement
            beforeDodger.next = tempThrowers;
            tempThrowers.next = afterDodger;

        } else {
        //Else statement when none of the parameter names are found in the first node of the respective linked lists
            //Rearranging the thrower node by assigning the node after the before node to the dodger node and then reattaching the thrower list through the .next = after statement
            beforeThrower.next = tempDodgers;
            tempDodgers.next =  afterThrower;
            //Rearranging the dodger node by assigning the node after the before node to the thrower node and then reattaching the dodger list through the .next = after statement
            beforeDodger.next = tempThrowers;
            tempThrowers.next = afterDodger;
        }
    }

    //Method for when the dodger dodges, takes two strin parameters the names of the players involved
    public void dodge(String throwerName, String dodgerName) {
        //If statement to check if the thrower name is not in the throwers linked list, the dodger name is not in the dodgers linked list, or if either name is empty or null 
        if (!throwersContains(throwerName) || !dodgersContains(dodgerName) || throwerName == null || throwerName.isEmpty() || dodgerName == null || dodgerName.isEmpty()) {
            //Throwing illegal argument exception
            throw new IllegalArgumentException();
        }

        //Creating a new reference node to go through the dodgers linked list
        DodgeballNode tempDodgers = dodgerFirstNode;

        //While loop to go through the entire list it will stop when the node does not exist
        while (tempDodgers != null) {
            if (tempDodgers.name.equalsIgnoreCase(dodgerName)) {
                tempDodgers.score++;
            }
            //Move reference to the next node
            tempDodgers = tempDodgers.next;
        }
    }

    public int getMaximumScore() {
        //Set max score to zero as it is the lowest score
        int maxScore = 0;
        //Creating a new reference nodes to go through the dodgers and throwers linked list
        DodgeballNode tempThrowers = throwerFirstNode;
        DodgeballNode tempDodgers = dodgerFirstNode;
        //While loop to go through the entire list it will stop when the node does not exist
        while (tempThrowers != null) {
            if (maxScore < tempThrowers.score) {
                maxScore = tempThrowers.score;
            }
            //Move reference to the next node
            tempThrowers = tempThrowers.next;
        }
        //While loop to go through the entire list it will stop when the node does not exist
        while (tempDodgers != null) {
            if (maxScore < tempDodgers.score) {
                maxScore = tempDodgers.score;
            }
            //Move reference to the next node
            tempDodgers = tempDodgers.next;
        }
        return maxScore;
    }

    //Bonus method prints out the scores in sorted order drawing from both lists on a new line each
    public void printSortedScores(PrintStream stream){
        //For loop to start at the highest score and iterate through to 0, to print out the according player names and score from highest to lowest
        for(int x = getMaximumScore(); x >= 0 ; x--){
            //Creating a new reference node to go through the throwers linked list
            DodgeballNode tempThrowers = throwerFirstNode;    

            //While loop to go through the entire list it will stop when the node does not exist        
            while(tempThrowers != null){
                //If statement if the score at the given node is equal to the value of x in the for loop (which decreases)
                if(tempThrowers.score == x){
                //Printing output with name and score in the deisred format on a new line each time
                    stream.println(tempThrowers.name + " " + tempThrowers.score);
                }
                //Move reference to the next node
                tempThrowers = tempThrowers.next;
            }
            //Creating a new reference node to go through the dodgers linked list
            DodgeballNode tempDodgers = dodgerFirstNode;  
            //While loop to go through the entire list it will stop when the node does not exist
            while(tempDodgers != null){
                //If statement if the score at the given node is equal to the value of x in the for loop (which decreases)
                if(tempDodgers.score == x){
                //Printing output with name and score in the deisred format on a new line each time
                    stream.println(tempDodgers.name + " " + tempDodgers.score);
                }
                //Move reference to the next node
                tempDodgers = tempDodgers.next;
           }
        }
    }
}