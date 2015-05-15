import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {		
		int nrOfParallelismLevels = 10;
		int nrOfTestCases = 7;
		int nrOfTests = 100;
		int nrOffalseMessages = 10;
		String[] paths = new String[]{"Random Order/","Increased Order/", "Decreased Order/"};
		
		for(int p = 10; p<=nrOfParallelismLevels; p++){
			for(int path = 0; path<paths.length; path++){
				
			int[] Data_16 		= new int[16];			readData(Data_16, paths[path]);
			int[] Data_256 		= new int[256];			readData(Data_256, paths[path]);
			int[] Data_4096 	= new int[4096];		readData(Data_4096, paths[path]);
			int[] Data_65536 	= new int[65536];		readData(Data_65536, paths[path]);
			int[] Data_1048576	= new int[1048576];		readData(Data_1048576, paths[path]);
			int[] Data_4194304 	= new int[4194304];		readData(Data_4194304, paths[path]);
			int[] Data_16777216 = new int[16777216];	readData(Data_16777216, paths[path]);
				
			long[] elapsedTime_16 		= new long[nrOfTests];
			long[] elapsedTime_256	 	= new long[nrOfTests];
			long[] elapsedTime_4096 	= new long[nrOfTests];
			long[] elapsedTime_65536 	= new long[nrOfTests];
			long[] elapsedTime_1048576 	= new long[nrOfTests];
			long[] elapsedTime_4194304 	= new long[nrOfTests];
			long[] elapsedTime_16777216 = new long[nrOfTests];
			
			for(int i = 0; i<nrOfTestCases; i++){
				for(int j = 0; j<nrOfTests; j++){
					int[] data = null;
					if(i == 0){
						data = new int[Data_16.length];
						for(int k = 0; k<Data_16.length; k++){
							data[k] = Data_16[k];
						}
					}
					else if(i == 1){
						data = new int[Data_256.length];
						for(int k = 0; k<Data_256.length; k++){
							data[k] = Data_256[k];
						}
					}
					else if(i == 2){
						data = new int[Data_4096.length];
						for(int k = 0; k<Data_4096.length; k++){
							data[k] = Data_4096[k];
						}
					}
					else if(i == 3){
						data = new int[Data_65536.length];
						for(int k = 0; k<Data_65536.length; k++){
							data[k] = Data_65536[k];
						}
					}
					else if(i == 4){
						data = new int[Data_1048576.length];
						for(int k = 0; k<Data_1048576.length; k++){
							data[k] = Data_1048576[k];
						}
					}
					else if(i == 5){
						data = new int[Data_4194304.length];
						for(int k = 0; k<Data_4194304.length; k++){
							data[k] = Data_4194304[k];
						}
					}
					else if(i == 6){
						data = new int[Data_16777216.length];
						for(int k = 0; k<Data_16777216.length; k++){
							data[k] = Data_16777216[k];
						}
					}
					
					if(data != null){
						long start = System.nanoTime();
						MergeSort.sort(data, p);
						long end = System.nanoTime()-start;
						System.out.println(data.length +" elements took | " + end + " nano time");
						
						if(i == 0)
							elapsedTime_16[j] 		= end;
						else if(i == 1)
							elapsedTime_256[j] 		= end;
						else if(i == 2)
							elapsedTime_4096[j] 	= end;
						else if(i == 3)
							elapsedTime_65536[j] 	= end;
						else if(i == 4)
							elapsedTime_1048576[j] 	= end;
						else if(i == 5)
							elapsedTime_4194304[j] 	= end;
						else if(i == 6)
							elapsedTime_16777216[j] = end;
					}
					else{
						System.out.println("No data found");
					}
				}// Nr of tests 
			}//Nr of test cases
			
			BigInteger Sum_16 = BigInteger.valueOf(0);
			BigInteger Sum_256 = BigInteger.valueOf(0); 
			BigInteger Sum_4096 = BigInteger.valueOf(0); 
			BigInteger Sum_65536 = BigInteger.valueOf(0); 
			BigInteger Sum_1048576 = BigInteger.valueOf(0); 
			BigInteger Sum_4194304 = BigInteger.valueOf(0); 
			BigInteger Sum_16777216 = BigInteger.valueOf(0); 
			
			for(int Sum = nrOffalseMessages; Sum<nrOfTests; Sum++){
				Sum_16 			= Sum_16.add(BigInteger.valueOf(elapsedTime_16[Sum]));
				Sum_256 		= Sum_256.add(BigInteger.valueOf(elapsedTime_256[Sum]));
				Sum_4096 		= Sum_4096.add(BigInteger.valueOf(elapsedTime_4096[Sum]));
				Sum_65536 		= Sum_65536.add(BigInteger.valueOf(elapsedTime_65536[Sum]));
				Sum_1048576 	= Sum_1048576.add(BigInteger.valueOf(elapsedTime_1048576[Sum]));
				Sum_4194304 	= Sum_4194304.add(BigInteger.valueOf(elapsedTime_4194304[Sum]));
				Sum_16777216 	= Sum_16777216.add(BigInteger.valueOf(elapsedTime_16777216[Sum]));
			}
			
			BigInteger divide = BigInteger.valueOf(nrOfTests-nrOffalseMessages);
			long Average_16 		= Sum_16.divide(divide).longValue();
			long Average_256 		= Sum_256.divide(divide).longValue();
			long Average_4096 		= Sum_4096.divide(divide).longValue();
			long Average_65536 		= Sum_65536.divide(divide).longValue();
			long Average_1048576  	= Sum_1048576.divide(divide).longValue();
			long Average_4194304  	= Sum_4194304.divide(divide).longValue();
			long Average_16777216 	= Sum_16777216.divide(divide).longValue();
			
			System.out.println("Average 16 		 element - Parallelism level " + p + " - Took " + Average_16 + " nano seconds");
			System.out.println("Average 256 	 element - Parallelism level " + p + " - Took " + Average_256 + " nano seconds");
			System.out.println("Average 4096 	 element - Parallelism level " + p + " - Took " + Average_4096 + " nano seconds");
			System.out.println("Average 65536 	 element - Parallelism level " + p + " - Took " + Average_65536 + " nano seconds");
			System.out.println("Average 1048576  element - Parallelism level " + p + " - Took " + Average_1048576 + " nano seconds");
			System.out.println("Average 4194304  element - Parallelism level " + p + " - Took " + Average_4194304 + " nano seconds");
			System.out.println("Average 16777216 element - Parallelism level " + p + " - Took " + Average_16777216 + " nano seconds");
			

			System.out.println("---------------------------");
			System.out.println("Writing result to text file");
			System.out.println("---------------------------");
		
			System.out.println("Writing result of 16 elements");
			writeDatatoTextfile_tests(elapsedTime_16, paths[path], "16", p, nrOffalseMessages);
			
			System.out.println("Writing result of 256 elements");
			writeDatatoTextfile_tests(elapsedTime_256, paths[path], "256", p, nrOffalseMessages);
			
			System.out.println("Writing result of 4096 elements");
			writeDatatoTextfile_tests(elapsedTime_4096, paths[path], "4096", p, nrOffalseMessages);
			
			System.out.println("Writing result of 65536 elements");
			writeDatatoTextfile_tests(elapsedTime_65536, paths[path], "65536", p, nrOffalseMessages);
			
			System.out.println("Writing result of 1048576 elements");
			writeDatatoTextfile_tests(elapsedTime_1048576, paths[path], "1048576", p, nrOffalseMessages);
			
			System.out.println("Writing result of 4194304 elements");
			writeDatatoTextfile_tests(elapsedTime_4194304, paths[path], "4194304", p, nrOffalseMessages);
			
			System.out.println("Writing result of 16777216 elements");
			writeDatatoTextfile_tests(elapsedTime_16777216, paths[path], "16777216", p, nrOffalseMessages);
			
			System.out.println("Writing Average result of all cases");
			long[] Average = new long[]{Average_16, Average_256, Average_4096, Average_65536, Average_1048576, Average_4194304, Average_16777216};
			writeDatatoTextfile_result(Average, paths[path], "Average result", p);
			}//Paths
		}//Nr of parallelism levels
		
	}
	public static int[] readData(int[] data, String path){
		Scanner scanner;
		try {
			scanner = new Scanner(new File(path+data.length+".txt"));
			int read = 0;
			while(scanner.hasNextInt()){
			   data[read++] = scanner.nextInt();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	public static void writeDatatoTextfile_tests(long[] data, String path, String name, int parallelismLevel, int nrOfFakeMessages){
		try {
			System.out.println(data.length);
			PrintWriter out = new PrintWriter(path +"/Parallelism Level " + parallelismLevel+ "/"+name+".txt");
			for(int i = 0; i<data.length; i++){
				if(i<nrOfFakeMessages){
					if(i < 10){
						out.write("Testcase "+ i+ "  | took " + Long.toString(data[i]) + "\t\t\t nano seconds to sort "+ name + " elements <- Fake message" +"\n");
					}
					else{
						out.write("Testcase "+ i+ " | took " + Long.toString(data[i]) + "\t\t\t nano seconds to sort "+ name + " elements <- Fake message" +"\n");
					}
					if(i == nrOfFakeMessages-1){
						out.write("-----------------------------------------------------------------------------\n");
						out.write("-----------------------------<¤¤{VALID TEST}¤¤>------------------------------\n");
						out.write("-----------------------------------------------------------------------------\n");
					}
				}
				else{
					out.write("Testcase "+ i+ "| took " + Long.toString(data[i]) + "\t\t\t nano seconds to sort "+ name + " elements <- Valid test" +"\n");
				}
			}
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void writeDatatoTextfile_result(long[] data, String path, String name, int parallelismLevel){
		try {
			System.out.println(data.length);
			PrintWriter out = new PrintWriter(path +"/Parallelism Level " + parallelismLevel+ "/"+name+".txt");
			for(int i = 0; i<data.length; i++){
				if(i == 0)
					out.write("The Average time for 16 \t\t elements to sort, took " + Long.toString(data[i]) + " nano seconds \n");
				if(i == 1)
					out.write("The Average time for 256 \t\t elements to sort, took " + Long.toString(data[i]) + " nano seconds \n");
				if(i == 2)
					out.write("The Average time for 4096 \t\t elements to sort, took " + Long.toString(data[i]) + " nano seconds \n");
				if(i == 3)
					out.write("The Average time for 65536 \t\t elements to sort, took " + Long.toString(data[i]) + " nano seconds \n");
				if(i == 4)
					out.write("The Average time for 1048576 \t elements to sort, took " + Long.toString(data[i]) + " nano seconds \n");
				if(i == 5)
					out.write("The Average time for 4194304 \t elements to sort, took " + Long.toString(data[i]) + " nano seconds \n");
				if(i == 6)
					out.write("The Average time for 16777216 \t elements to sort, took " + Long.toString(data[i]) + " nano seconds \n");
			}
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}