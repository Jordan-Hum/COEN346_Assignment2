//Jordan Hum - 40095876 | Anthony Iacampo - 40096683
import java.io.*;
import java.util.*;

class ProcessScheduling {
  boolean hasCPU;
  static int arrivalTime[];
  static int execTime[];
  public static void main(String args[]) throws Exception 
  {
    //Read Input.txt file
    readFile();

    printArray(arrivalTime);
    System.out.println();
    printArray(execTime);
  }

  //Read the input file
  private static void readFile() throws Exception 
  {
    File file = new File("Input.txt");
    List<String> inputList = new ArrayList<String>();
    
    try 
    {
      Scanner reader = new Scanner(file);
      while (reader.hasNext()) 
      {
        String input = reader.next();
        inputList.add(input);
      }
      reader.close();
    } 
    catch (Exception e) 
    {
      System.out.println(e);
    }

    //Set size for arrays
    int arraySize = inputList.size()/2;
    arrivalTime = new int[arraySize];
    execTime = new int[arraySize];

    //All even indexes for process number
    int processSetCounter = 0;
    for(int i=0; i < inputList.size(); i += 2)
    {
      try 
      {
        arrivalTime[processSetCounter++] = Integer.parseInt(inputList.get(i));
      } 
      catch (Exception e) 
      {
        System.out.println("Invalid input");
        System.exit(0);
      } 
    }

    //All odd indexes for execution time
    int timeSetCounter = 0;
    for(int i=1; i < inputList.size(); i += 2)
    {
      try
      {
        execTime[timeSetCounter++] = Integer.parseInt(inputList.get(i));
      }
      catch (Exception e) 
      {
        System.out.println("Invalid input");
        System.exit(0);
      } 
    }
  }

   //Prints the array
   private static void printArray(int[] array)
   {
     for(int i = 0; i < array.length; i++)
     {
         System.out.print(array[i]);
     }
   }
}