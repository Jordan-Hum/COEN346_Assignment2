//Jordan Hum - 40095876 | Anthony Iacampo - 40096683
import java.io.*;
import java.util.*;

class ProcessComparator implements Comparator<Process>
{
  public int compare(Process p1, Process p2)
  {
    if(p1.execTime > p2.execTime)
      return 1;
    else if(p1.execTime < p2.execTime)
      return -1;
    return 0;
  }
}

class Process {
  boolean hasCPU;
  static int arrivalTime[];
  static int executeTime[];
  static int timeCounter = 1;

  //Member variables
  public int arrTime;
  public int execTime;
  public double quantumTime;
  public double waitTime;

  //Constructor
  public Process() {
    this.arrTime = 0;
    this.execTime = 0;
    this.quantumTime = 0;
    this.waitTime = 0;
  }

  public Process(int arrival, int exec) {
    this.arrTime = arrival;
    this.execTime = exec;
    this.quantumTime = 0.1 * this.execTime;
    this.waitTime = 0;
  }

  public void setExecTime() {
    this.execTime -= this.quantumTime;
  }

  public void setQuantumTime() {
    this.quantumTime = 0.1 * this.execTime;
  }

  //Priority Queues
  static PriorityQueue<Process> pq1 = new PriorityQueue<Process>(new ProcessComparator());
  static PriorityQueue<Process> pq2 = new PriorityQueue<Process>(new ProcessComparator());

  public static void main(String args[]) throws Exception 
  {
    //Read Input.txt file
    readFile();

    //Initialize Queue
    initQueue();

    //Schedule
    RRScheduling();
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
    executeTime = new int[arraySize];

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
        executeTime[timeSetCounter++] = Integer.parseInt(inputList.get(i));
      }
      catch (Exception e) 
      {
        System.out.println("Invalid input");
        System.exit(0);
      } 
    }
  }

  public static void initQueue()
  {
    for(int i = 0; i < arrivalTime.length; i++) {
      Process process = new Process(arrivalTime[i], executeTime[i]);
      System.out.println("Time " + timeCounter + ", Process " + process.arrTime + ", Started");
      System.out.println("Time " + timeCounter + ", Process " + process.arrTime + ", Resumed");
      process.setExecTime();
      process.setQuantumTime();
      timeCounter++;
      System.out.println("Time " + timeCounter + ", Process " + process.arrTime + ", Paused");
      if(process.execTime != 0) {
        pq1.add(process);
      }
      else{
        System.out.println("Time " + timeCounter + ", Process " + process.arrTime + ", Finished");
      }
    }
  }

  public static void RRScheduling()
  {
    while (!pq1.isEmpty() ^ !pq2.isEmpty()) {
      Process processEdit = new Process();
      //Check pq1 
      while(!pq1.isEmpty()) {
        processEdit = pq1.poll();
        System.out.println("Time " + timeCounter + ", Process " + processEdit.arrTime + ", Resumed");
        processEdit.setExecTime(); 
        processEdit.setQuantumTime();
        timeCounter++;
        System.out.println("Time " + timeCounter + ", Process " + processEdit.arrTime + ", Paused");
        if(processEdit.execTime != 0){
          pq2.add(processEdit);
        }
        else{
          System.out.println("Time " + timeCounter + ", Process " + processEdit.arrTime + ", Finished");
        }
      }

      //Check pq2
      while(!pq2.isEmpty()) {
        processEdit = pq2.poll();
        System.out.println("Time " + timeCounter + ", Process " + processEdit.arrTime + ", Resumed");
        processEdit.setExecTime();
        processEdit.setQuantumTime();
        timeCounter++;
        System.out.println("Time " + timeCounter + ", Process " + processEdit.arrTime + ", Paused");
        if(processEdit.execTime != 0){
          pq1.add(processEdit);
        }
        else{
          System.out.println("Time " + timeCounter + ", Process " + processEdit.arrTime + ", Finished");
        }
      }
    }
  }
}