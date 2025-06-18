/**
 * This is the main driver class which contains all the operations of take away order
 * system. It first displays the seven options for user, and allows user to pick
 * one of the operation to perform, users must input valid number option or else
 * user will be keep prompted the valid input. The whole program will be continuous
 * until customer chose the option 4 which is to quit and exit the system.
 * This main driver class uses do-while loop and switch statement to perform
 * all operations.
 *
 * @author Hew Kai Feng
 * @version ver 1.3.3
 */

import java.util.Scanner;


public class TakeAwayOrder
{

    //Main method

    /**
     * Method to being the program.
     *
     * @param args          An array of Strings representing command line arguments.
     *
     * This is the main method which acts as the program execution.
     */

    public static void main(String[] args)
    {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);

        do
        {
            System.out.println("------------------------------------------------------------------");
            System.out.println("Welcome to our take away order management system:");
            System.out.println("1. Enter customer order");
            System.out.println("2. Deliver an order");
            System.out.println("3. Display order details");
            System.out.println("4. Exit");
            System.out.println("5. Remove order");
            System.out.println("6. Search order");
            System.out.println("7. Update order");
            System.out.println("8. Save order\n");
            System.out.println("* note that you can select option 3 to view your order number");
            System.out.println("------------------------------------------------------------------\n");
            System.out.print("Please select one of the option from menu above: ");

            String choice=scanner.nextLine();
            switch (choice)
            {
                case "1":
                    OrderOperation.addOrder();
                    break;

                case "2":
                    OrderOperation.deliverOrder();
                    break;

                case "3":
                    OrderOperation.displayOrders();
                    break;

                case "4":
                    System.out.println("Exiting system........");
                    System.out.println("Please come again.Goodbye!");
                    flag = false;
                    break;

                case "5":
                    OrderOperation.removeOrder();
                    break;

                case "6":
                    OrderOperation.searchOrder();
                    break;

                case "7":
                    OrderOperation.updateOrder();
                    break;

                case "8":
                    OrderOperation.saveOrder();
                    break;

                default:
                    System.out.println("Unknown choice. Try again!");
            }
        } while (flag);
    }
}


