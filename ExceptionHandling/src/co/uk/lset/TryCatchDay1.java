package co.uk.lset;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TryCatchDay1 {
    public static void main(String[] args) {
        int x, y, z;

        Scanner scanner = new Scanner(System.in);

        try{
            System.out.println("Please enter 2 numbers:");
            x = scanner.nextInt();
            y = scanner.nextInt();

            z = x/y;
            System.out.println("Value of z: " + z);
        }catch(ArithmeticException e){
            System.out.println("Error when dividing by zero");
        }catch (InputMismatchException e){
            System.out.println("Please enter numbers!");
        }finally {
            scanner.close();
            System.out.println("Program completed!");
        }

    }
}
