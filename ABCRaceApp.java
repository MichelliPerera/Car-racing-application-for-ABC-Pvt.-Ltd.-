import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

// Define a Car class to represent car details
class Car {
    int Unumber; // Unique car number
    String Brand; // Car brand
    String Sponsor; // Car sponsor
    String Driver; // Driver's name
}

// Main class for the ABCRaceApp application
public class ABCRaceApp {

    public static void main(String[] args) {
        // Initialize an array to store car objects
        Car cr[] = new Car[6];
        int MenuChoice = 0, carCount = 0, roundCount = 0;
        System.out.println("\tCar race application of ABC Pvt. Ltd.\t");
        Scanner input = new Scanner(System.in);

        // Create a queue and stack to manage car objects
        Queue<Car> carQueue = new LinkedList<>();
        Stack<Car> carStack = new Stack<>();

        // Main menu loop
        while (MenuChoice != 7) {
            // Display menu options
            System.out.println("1. Register Car Details ");
            System.out.println("2. Delete a car ");
            System.out.println("3. Insert 3 Rounds Results. ");
            System.out.println("4. Find out the winners (1st,2nd,3rd) ");
            System.out.println("5. Search for a particular car ");
            System.out.println("6. Display the already registered cars");
            System.out.println("7. Exit Application");

            // Get user choice
            MenuChoice = input.nextInt();
            switch (MenuChoice) {

                case 1: // Register Car Details
                    if (carCount < 6) {
                        // Create a new car object
                        cr[carCount] = new Car();
                        System.out.print("Please enter the car Unumber: ");
                        cr[carCount].Unumber = input.nextInt();
                        input.nextLine();
                        System.out.print("Please enter the car Brand: ");
                        cr[carCount].Brand = input.nextLine();
                        System.out.print("Please enter the car Sponsor: ");
                        cr[carCount].Sponsor = input.nextLine();
                        System.out.print("Please enter the car Driver: ");
                        cr[carCount].Driver = input.nextLine();

                        // Add to queue and stack
                        carQueue.add(cr[carCount]);
                        carStack.push(cr[carCount]);

                        carCount++;
                    } else {
                        System.out.println("It is possible to register only 6 cars for the race");
                    }
                    break;

                case 2: // Delete a car
                    System.out.println("Please enter the car Unumber you require to delete: ");
                    int carNumber = input.nextInt();
                    for (int index = 0; index < carCount; index++) {
                        if (carNumber == cr[index].Unumber) {
                            System.out.println("Car got deleted!");
                            carQueue.remove(cr[index]);
                            carStack.remove(cr[index]);
                            cr[index] = null;
                            carCount--;
                            for (int i = index; i < carCount; i++) {
                                if (i != 6) {
                                    cr[i] = cr[i + 1];
                                }
                            }
                        } else if (index + 1 == carCount) {
                            System.out.println("There is no car found to delete!");
                        }
                    }
                    break;

                case 3: // Insert 3 Rounds Results
                    System.out.println("enter the round" + (++roundCount) + " results are: ");
                    Car temp = new Car();
                    int number, x = 0;
                    for (int index = 0; index < 6 - (roundCount - 1); index++) {
                        System.out.print("place is " + (index + 1) + ": ");
                        number = input.nextInt();
                        for (int m = 0; m < carCount; m++) {
                            if (number == cr[m].Unumber) {
                                temp = cr[m];
                                cr[m] = cr[x];
                                cr[x++] = temp; 
                            }
                        }
                    }
                    break;

                case 4: // Find out the winners (1st, 2nd, 3rd)
                    if (roundCount == 3) {
                        // Display winners from the queue
                        System.out.println("First place: " + carQueue.poll().Unumber + " " + carQueue.poll().Driver);
                        System.out.println("Second place: " + carQueue.poll().Unumber + " " + carQueue.poll().Driver);
                        System.out.println("Third place: " + carQueue.poll().Unumber + " " + carQueue.poll().Driver);
                    } else {
                        // Display current results if the race is ongoing
                        System.out.println("The race is still happening, the current results are: ");
                        System.out.println("End of the round" + roundCount + ": ");
                        for (int index = 0; index < carCount; index++) {
                            System.out.println((index + 1) + " place is:" + cr[index].Unumber);
                        }
                    }
                    break;

                case 5: // Search for a particular car
                    System.out.print("Please enter the car Unumber: ");
                    int n = input.nextInt();
                    for (int index = 0; index < carCount; index++) {
                        if (n == cr[index].Unumber) {
                            // Display details of the found car
                            System.out.println("Brand is: " + cr[index].Brand);
                            System.out.println("Sponsor is: " + cr[index].Sponsor);
                            System.out.println("Driver is: " + cr[index].Driver);
                        } else if (index + 1 == carCount) {
                            System.out.println("");
                        } else if (index == 0) {
                            System.out.println("There is no car in registry!");
                        }
                    }
                    break;

                case 6: // Display the already registered cars
                    System.out.println("---------------The registered car details----------------");
                    for (Car car : carStack) {
                        // Display details of registered cars from the stack
                        System.out.println(car.Unumber + "\t" + car.Brand + "\t" + car.Sponsor + "\t" + car.Driver);
                    }
                    break;

                case 7: // Exit Application
                    System.out.println("Application exit!");
                    break;
            }
        }
    }
}
