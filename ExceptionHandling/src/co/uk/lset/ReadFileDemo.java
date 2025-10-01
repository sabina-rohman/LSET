package co.uk.lset;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ReadFileDemo {
    public static void readFileAndCalculate(String fileName){
        int x, y, z;
        Scanner scanner;
        try{
            File file = new File(fileName);
            scanner = new Scanner(file);
            System.out.println("Please enter 2 numbers:");
            x = scanner.nextInt();
            y = scanner.nextInt();

            z = x/y;
            System.out.println("Value of z: " + z);
        } catch(FileNotFoundException e){
//            System.out.println(e.getMessage());
              e.printStackTrace();
        } catch (ArithmeticException e){
            System.out.println("Error when dividing by zero");
        }catch (InputMismatchException e){
            System.out.println("Please enter numbers!");
        }catch(Exception e){
            System.out.println("Parent class Exception thrown");
        }
        finally {
//            scanner.close();
            System.out.println("Program completed!");
        }


    }

    public static void main(String[] args) {
        readFileAndCalculate("abc.txt");
    }
}
