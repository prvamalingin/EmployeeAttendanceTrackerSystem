import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class mao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> idList = new ArrayList<>();
        ArrayList<String> timeInList = new ArrayList<>();
        ArrayList<String> timeOutList = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        while (true) {//main loop(menu)
            System.out.println("\n----[Options]----\n1. Time In\n2. Time Out\n3. View List\n4. Exit");
            System.out.print("Choice: ");

            if (scanner.hasNextInt()) {//integer chekcer
                int choice = scanner.nextInt();
                if (choice >= 1 && choice <= 4) {

                    if (choice == 1) {//time in
                        System.out.print("Enter ID: ");
                        if (scanner.hasNextInt()) {
                            int idInt = scanner.nextInt();
                            String id = String.valueOf(idInt);
                        
                            boolean isActive = false;
                            for (int i = 0; i < idList.size(); i++) {
                                if (idList.get(i).equals(id) && timeOutList.get(i).equals("None")) {
                                   isActive = true;
                                   break;
                                  }
                                }

                                if (isActive) {
                                    System.out.println("ID: " + id + " is already timed in!");
                                } else {
                                   idList.add(id);
                                   timeInList.add(LocalDateTime.now().format(dtf));
                                   timeOutList.add("None");
                                   System.out.println("Time In successfully.");
                                }
                        }
                    } else if (choice == 2) {//time out
                        System.out.print("Enter ID: ");
                        String id = scanner.next();

                        boolean found = false;
                        for (int i = 0; i < idList.size(); i++) {
                            if (idList.get(i).equals(id) && timeOutList.get(i).equals("None")) {
                                timeOutList.set(i, LocalDateTime.now().format(dtf));
                                System.out.println("Time Out recorded.");
                                found = true;
                                break;
                            }
                        }
                        if (!found) System.out.println("The ID hasn't been timed in yet/enter a number!");

                    } else if (choice == 3) {// attendance list
                        System.out.println("\n--- ATTENDANCE LIST ---");
                        if (idList.isEmpty()) {
                            System.out.println("List is empty.");
                        } else {
                            for (int i = 0; i < idList.size(); i++) {
                                System.out.println("ID: " + idList.get(i) + 
                                " | In: " + timeInList.get(i) + 
                                " | Out: " + timeOutList.get(i));
                            }
                        }
                    } else if (choice == 4) {//end while loop
                        System.out.println("Exit...\nSystem closed.");
                        break;
                    }
                } else {
                    System.out.println("Please enter a number from 1-4.");
                }
            } else {
                System.out.print("\nPlease only enter a number.");
                scanner.next();// to allow you input again
            }
        }
    }
}