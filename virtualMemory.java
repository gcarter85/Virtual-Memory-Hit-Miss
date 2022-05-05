/**
 * Virtual Memory Program
 * By: Carter E Gamary
 */

// Import statements
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class virtualMemory {
	
	// Four different processes creation.
	private static Process p1 = new Process();
	private static Process p2 = new Process();
	private static Process p3 = new Process();
	private static Process p4 = new Process();
	
	// Class for a Process. This class will hold how many hits and misses it has as well as it's queue.
	public static class Process {
		
		int frames = 0;
		int hits = 0;
		int miss = 0;
		ArrayList<Integer> queue = new ArrayList<Integer>();
	}
	
	// Gets the average for how many hits there were for a given process.
	public static double getAvg(Process p) {
		
		return ((p.hits + 0.0) /((p.hits + 0.0) + (p.miss + 0.0)));
	}
	
	// Gets the string of ratios for all processes as well as the final average of all processes.
	public static String getRatios(double one, double two, double three, double four) {
		
		// Create the Format for the doubles.
		DecimalFormat df = new DecimalFormat("0.00");
		
		// Get total Average
		double average = (one + two + three + four) / 4;
		
		// return value
		return ("" + df.format(one) + "% " + df.format(two) + "% " + df.format(three) + "% " + df.format(four) + "% AVG=" + df.format(average) + "%");
	}
	
	// This will shift the value at a given index to the front of the queue since it was hit.
	public static void shiftEnd(Process p, int index) {
		
		p.queue.add(0, p.queue.get(index));
		p.queue.remove(index + 1);
	}
	
	// This function is used on a miss and will remove from the back of the queue and add to the front.
	public static void pushPop(Process p, int newNum) {
		
		// If the queue is at max capacity, remove the last before adding.
		if (p.queue.size() == p.frames) {
			p.queue.remove(p.frames - 1);
		}
		p.queue.add(0, newNum);
	}
	
	// Main function for program.
	public static void main(String[] args) {
		
		// Ensures the correct number of arguments in command line.
		if (args.length == 5) {
			try {
				
				// Create file and scanner to read input.
				File curFile = new File(args[0]);
				Scanner in = new Scanner(curFile);
				
				// Create the frames for each process.
				p1.frames = Integer.parseInt(args[1]);
				p2.frames = Integer.parseInt(args[2]);
				p3.frames = Integer.parseInt(args[3]);
				p4.frames = Integer.parseInt(args[4]);
				
				// While there is still input perform the algorithm.
				while (in.hasNext()) {
					
					// read input from file.
					int proc = in.nextInt();
					int pageNum = in.nextInt();
					int curHit = 0;
					
					// Check which process is required
					if (proc == 1) {
						
						// Check if the value is already in queue for hit, or if it will be a miss.
						for (int i = 0; i < p1.queue.size(); i++) {
							if (pageNum == p1.queue.get(i)) {
								shiftEnd(p1, i);
								curHit++;
								p1.hits++;
							}
						}
						if (curHit == 0) {
							pushPop(p1, pageNum);
							p1.miss++;
						}
					} else if (proc == 2) {
						
						// Check if the value is already in queue for hit, or if it will be a miss.
						for (int i = 0; i < p2.queue.size(); i++) {
							if (pageNum == p2.queue.get(i)) {
								shiftEnd(p2, i);
								curHit++;
								p2.hits++;
							}
						}
						if (curHit == 0) {
							pushPop(p2, pageNum);
							p2.miss++;
						}
					} else if (proc == 3) {
						
						// Check if the value is already in queue for hit, or if it will be a miss.
						for (int i = 0; i < p3.queue.size(); i++) {
							if (pageNum == p3.queue.get(i)) {
								shiftEnd(p3, i);
								curHit++;
								p3.hits++;
							}
						}
						if (curHit == 0) {
							pushPop(p3, pageNum);
							p3.miss++;
						}
					} else if (proc == 4) {
						
						// Check if the value is already in queue for hit, or if it will be a miss.
						for (int i = 0; i < p4.queue.size(); i++) {
							if (pageNum == p4.queue.get(i)) {
								shiftEnd(p4, i);
								curHit++;
								p4.hits++;
							}
						}
						if (curHit == 0) {
							pushPop(p4, pageNum);
							p4.miss++;
						}
					}
				}
				
				// Close the Scanner as it is no longer used.
				in.close();
				
				// Find the averages for each process.
				double avg1 = getAvg(p1) * 100;
				double avg2 = getAvg(p2) * 100;
				double avg3 = getAvg(p3) * 100;
				double avg4 = getAvg(p4) * 100;
				
				// Print the result.
				System.out.println(getRatios(avg1, avg2, avg3, avg4));
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Must have 5 arguments in the following pattern: ");
			System.out.println("'TitleOfInputFile.txt' Int1 Int2 Int3 Int4");
			System.out.println("Int1 - Int4 are all integers for the number of frames for the respective process");
		}
	}
}