import java.io.PrintWriter;
import java.util.Scanner;

public class App {

	/**
	 * Print out help menu.
	 */
	public static void printHelp() {
		System.out.println("Available commands:\n" + "   Add - EN - Eg: EN PT1 1\n" + "   Remove - DE - Eg: DE\n"
				+ "  Find - FP -Eg: FP PT1 \n" + "   Remove IndividulProcess - RP - Eg: RP PT1 \n"
				+ "    PreceedingTime - PT - Eg: PT PT1 \n" + "" + "   PrintElements - print\n"
				+ "    SuceedingTime - ST - Eg:ST PT1 \n" + "    help\n" + "    quit|end\n");
	}

	/**
	 * Get next command/operation from file.
	 *
	 * @param array Reference to MyArray array which the commands will be executed
	 *              on.
	 */
	public static void processOperations(Runqueue array) {
		Scanner is = new Scanner(System.in);

		// keep looping until we receive a quit or end command
		while (is.hasNext()) {
			String command = is.next();
			if (command.compareTo("DE") == 0) {
				try {
					array.dequeue();
				} catch (IndexOutOfBoundsException e) {
					System.err.println(e.getMessage());
				}
			} else if (command.compareTo("EN") == 0) {
				if (is.hasNext()) {
					String procLabel = is.next();
					if (is.hasNextInt()) {
						int vtrun = is.nextInt();
						try {
							array.enqueue(procLabel, vtrun);
						} catch (IndexOutOfBoundsException e) {
							System.err.println(e.getMessage());
						}
					}
				}
			} else if (command.compareTo("PA") == 0) {
				PrintWriter os = new PrintWriter(System.out);
				array.printAllProcesses(os);
			} else if (command.compareTo("RP") == 0) {
				if (is.hasNext()) {
					String procLabel = is.next();
					try {

						array.removeProcess(procLabel);
					} catch (IndexOutOfBoundsException e) {
						System.err.println(e.getMessage());

					}
				}
			} else if (command.compareTo("FP") == 0) {
				if (is.hasNext()) {
					String procLabel = is.next();
					try {

						System.out.println(array.findProcess(procLabel));
					} catch (IndexOutOfBoundsException e) {
						System.err.println(e.getMessage());

					}
				}
			} else if (command.compareTo("PT") == 0) {
				if (is.hasNext()) {
					String procLabel = is.next();
					try {

						System.out.println(array.precedingProcessTime(procLabel));
					} catch (IndexOutOfBoundsException e) {
						System.err.println(e.getMessage());

					}
				}
			} else if (command.compareTo("ST") == 0) {
				if (is.hasNext()) {
					String procLabel = is.next();
					try {

						System.out.println(array.succeedingProcessTime(procLabel));
					} catch (IndexOutOfBoundsException e) {
						System.err.println(e.getMessage());

					}
				}
			} else if (command.compareTo("quit") == 0 || command.compareTo("end") == 0) {
				break;
			} else if (command.compareTo("help") == 0) {
				printHelp();
			} else {
				System.err.println("Did not recognize command. Enter valid command.");
				printHelp();
			}
			/*
			 * else if( command.compareTo("FP") == 0 ) { if (is.hasNextInt()) { int value =
			 * is.nextInt(); try { array.add(value); } catch(IndexOutOfBoundsException e) {
			 * System.err.println(e.getMessage()); } } } else if( command.compareTo("RP") ==
			 * 0 ) { if (is.hasNextInt()) { int index = is.nextInt(); if (is.hasNextInt()) {
			 * int value = is.nextInt(); try { array.insert(index, value); }
			 * catch(IndexOutOfBoundsException e) { System.err.println(e.getMessage()); } }
			 * } } else if( command.compareTo("PT") == 0 ) { if (is.hasNextInt()) { int
			 * value = is.nextInt(); int searchIndex = array.search(value); if (searchIndex
			 * != array.NOT_IN_ARRAY) { System.out.println("Search key found at index " +
			 * searchIndex + "."); } else { System.out.println("Key not found."); } } } else
			 * if( command.compareTo("ST") == 0 ) { if (is.hasNextInt()) { int value =
			 * is.nextInt(); int searchIndex = array.search(value); if (searchIndex !=
			 * array.NOT_IN_ARRAY) { System.out.println("Search key found at index " +
			 * searchIndex + "."); } else { System.out.println("Key not found."); } } }
			 * 
			 * else if( command.compareTo("PA") == 0 ) { array.printAllProcesses(); } else
			 * if( command.compareTo("quit") == 0 || command.compareTo("end") == 0 ) {
			 * break; } else if ( command.compareTo("help") == 0) { printHelp(); } else {
			 * System.err.println("Did not recognize command. Enter valid command.");
			 * printHelp(); }
			 */
		}
	} // end of processOperations()

	public static void main(String[] args) {

		if (args.length != 1) {
			printHelp();
			System.exit(1);
		}

		// determine which list to construct
		Runqueue adt = null;
		if (args[0].compareTo("array") == 0) {
			adt = new OrderedArrayRQ();
		} else if (args[0].compareTo("list") == 0) {
			adt = new OrderedLinkedListRQ();
		} else if (args[0].compareTo("tree") == 0) {
			adt = new BinarySearchTreeRQ();
		}

		/*
		 * else if (args[0].compareTo("double") == 0) { adt = new DynamicArrayDouble();
		 * }
		 */
		else {
			printHelp();
			System.exit(1);
		}

		// process the commands
		System.out.println("Enter command:");
		processOperations(adt);
	}

}
