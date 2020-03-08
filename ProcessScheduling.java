//Jordan Hum - 40095876 | Anthony Iacampo - 40096683
import java.util.*;

class ProcessScheduling {
  boolean hasCPU;
  public static void main(String args[]) {
    //read Input.txt file
    readFile();

  }

    //Read the input file
    private static void readFile() throws Exception 
    {
      FileReader fr = new FileReader("Input.txt");
      BufferedReader br = new BufferedReader(fr);
      
      String input;
      int argsIndex = 0;
      
      numBulbs = Integer.parseInt(br.readLine());
      bulbs = new int[numBulbs];
      defectiveBulbs = new int[numBulbs];
      while((input = br.readLine()) != null)
      {
        if(Integer.parseInt(input) == 0 || Integer.parseInt(input) == 1)
        {
        try
        {
        bulbs[argsIndex] = Integer.parseInt(input);
          argsIndex++;
        }
        catch(Exception err)
        {
          System.out.println("Invalid array size");
            System.exit(0);
        }
        }
        else
        {
        System.out.println("Invalid input");
        System.exit(0);
        }
      }
      if(numBulbs > argsIndex)
      {
      System.out.println("Invalid array size");
        System.exit(0);
      }
      br.close();
    }
  
}