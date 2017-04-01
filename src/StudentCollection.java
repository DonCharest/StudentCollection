import java.util.Scanner;

/**
 * *********************************************************************
 * 
 * @author Don Charest Date submitted: 02/09/17 Due: 02/07/17 CS342-B1 Spring
 *         2017, Homework Assignment 2, StudentCollection Class
 * 
 *         *********************************************************************
 */

public class StudentCollection implements Cloneable
{
	// Instance variables for the StudentCollection class
	private static String[] ids = new String[20]; // – Array to store student
													// id’s (declared at 20)
	private static String[] names = new String[20]; // – Array to store student
													// names (declared at 20)
	private static int size; // Number of students currently in the collection
	private static String id; // Unique string value entered when a student is
								// added.
	private static String name; // String value entered thru put() method - may
								// be replaced if same id is recalled.

	/**
	 * Main Method - to test methods contained in StudentCollection class
	 * 
	 * @param args
	 *            - RunMenu()
	 */
	public static void main(String[] args)
	{
		RunMenu();
	} // End Method: main()

	/**
	 * Main Method - to test methods contained in StudentCollection class
	 */
	public static void RunMenu()
	{
		boolean running = true;
		while (running)
		{
			Scanner keyboard = new Scanner(System.in);

			System.out.println("Choose an option:\n"

					+ " \n1. Get index -- by id" + " \n2. Get student count" + " \n3. IsEmpty --> collection"
					+ " \n4. Get student name --> by id" + " \n5. Add/Replace student name --> by id "
					+ " \n6. Remove student --> by id" + " \n7. Clone student colection" + " \n8. Display all students"
					+ " \n9. Exit" + " \n ");

			String input = keyboard.next();
			switch (input)
			{
			case "1": // Get index
				System.out.println("Enter Student ID: ");
				id = keyboard.next();

				// id found --> index returned.
				try
				{
					String index = Integer.toString(findIndex(id));
					System.out.println(index + "\n ");
				}
				// error caught --> "1" returned to indicate id not in array.
				catch (NullPointerException e)
				{
					System.out.println("-1" + "\n ");
				}
				break;

			case "2": // number of students in collection returned.
				String strSize = Integer.toString(size());
				System.out.println(strSize + "\n");
				break;

			case "3": // Is Empty
				System.out.println(isEmpty() + "\n");
				break;

			case "4": // Get student name
				System.out.println("Enter Student ID: ");
				id = keyboard.next();
				System.out.println(get(id) + "\n");
				break;

			case "5": // Put student
				System.out.println("Enter Student ID: ");
				id = keyboard.next();
				System.out.println("Enter Name: ");
				name = keyboard.next();
				put(id, name);
				break;

			case "6": // Remove student
				System.out.println("Enter Student ID: ");
				String id = keyboard.next();
				remove(id);
				break;

			case "7": // Clone studentCollection
				clone(ids, names, size);
				break;

			case "8": // Display all students in collection
				printStudents();
				break;

			case "9": // Exit program
				System.out.println("Bye\n");
				keyboard.close(); // Close Scanner class
				System.exit(0); // Exit program
				break;

			// Invalid user entry --> Valid responses are between "1" & "9".
			default:
				System.out.println("Please choose a valid menu option\n");
				break;
			}
		}
	} // End Method: RunMenu()

	/**
	 * Method to find the index of an array element - specified by student id.
	 * 
	 * @param id
	 *            - unique string value entered when a new student is added.
	 * @return - returns index when valid student id give, otherwise returns
	 *         "-1".
	 * @throws NullPointerException
	 *             - Error thrown when "-1" index value returned, error caught &
	 *             value returned.
	 * @postcondition - if user id found, then the index to that id is returned,
	 *                else -1 is returned.
	 */
	private static int findIndex(String id) throws NullPointerException
	{
		for (int i = 0; i < ids.length; i++)
		{
			// id found --> return index value
			if (ids[i] != null && ids[i].equalsIgnoreCase(id))
			{
				return i;
			}
		}
		// id not found --> return -1
		return -1;
	} // End Method: findIndex( String id )

	/**
	 * Method to count the number of students currently in the array.
	 * 
	 * @return - integer value for number of students.
	 * @postcondition - the number of student id's contained in the ids[] array
	 *                is displayed.
	 */
	public static int size()
	{
		int count = 0;
		for (int i = 0; i < ids.length; i++)
		{
			// id found --> increment counter
			if (ids[i] != null)
			{
				count++;
			}
		}
		// return number of students in array
		return count;
	} // End Method: size( )

	/**
	 * Method to check if array contains students
	 * 
	 * @return - true if empty; false if not.
	 */
	public static boolean isEmpty()
	{
		for (int i = 0; i < ids.length; i++)
		{
			// Students exist in collection
			if (ids[i] != null)
			{
				return false;
			}
		}
		// No students are in collection
		return true;
	} // End Method: isEmpty( )

	/**
	 * Method to get student name --> from id
	 * 
	 * @param id
	 *            - unique string value entered when a new student is added.
	 * @return - if id found returns student name; if id not found returns null.
	 */
	public static String get(String id)
	{
		for (int i = 0; i < ids.length; i++)
		{
			if (ids[i] != null && ids[i].equalsIgnoreCase(id))
			{
				// Id found --> return student name
				return names[i];
			}
		}
		// Id not found --> return null
		return null;
	} // End Method: get( String id )

	/**
	 * Method to Add/Replace students in the arrays
	 * 
	 * @param id
	 *            - unique string value entered when a new student is added.
	 * @param name
	 *            - string value entered thru put() method - may be replaced if
	 *            same id is recalled.
	 * @return - if id not currently in array --> id and name vales entered into
	 *         respective arrays at next open slots. - if id already in array
	 *         --> name value gets updated and previous name entry gets
	 *         returned.
	 */
	public static String put(String id, String name)
	{
		for (int i = 0; i < ids.length; i++)
		{
			// Id not found in collection
			if (ids[i] == null)
			{
				size = size();
				ids[size] = id;
				names[size] = name;
				// id not in array --> id & name entered into next open slots in
				// arrays.
				return ids[size];
			}
			// Id found in collection
			else
				if (ids[i] != null && ids[i].equalsIgnoreCase(id))
				{
					// Name of previous student returned.
					System.out.println("\nOld name: " + names[i] + "\n");

					ids[i] = id;
					names[i] = name;
					// id already in array --> student name replaced with new
					// entry.
					return ids[i];
				}
		}
		return ids[size];
	} // End Method: put( String id, String name )

	/**
	 * Method to remove student collection from arrays
	 * 
	 * @param id
	 *            - unique string value entered when a new student is added.
	 * @return - null to array indexes for specified student id.
	 */
	public static String remove(String id)
	{
		for (int i = 0; i < ids.length; i++)
		{
			// Id found --> replace id & name values with null
			if (ids[i] != null && ids[i].equalsIgnoreCase(id))
			{
				ids[i] = null;
				names[i] = null;
			}
		}
		return null;
	} // End Method: remove( String id )

	/**
	 * Method to create an independent copy of the existing student collection.
	 * 
	 * @param ids
	 *            - String array containing student id's.
	 * @param names
	 *            - String array containing student names.
	 * @param size
	 *            - Method to count the number of students in the collection.
	 * @return - independent copies of each array in the collection.
	 */
	public static StudentCollection clone(String[] ids, String[] names, int size)
	{
		// Copy existing collection arrays
		String[] idsClone = ids.clone();
		String[] namesClone = names.clone();

		// Header for cloned collection display
		System.out.println("Clone of StudentCollection:\n");

		for (int i = 0; i < idsClone.length; i++)
		{
			System.out.println("Id: " + idsClone[i] + ", " + "Names: " + namesClone[i] + "\n");
		}
		return null;
	} // End Method: clone( String[] ids, String[]names, int size )

	/**
	 * Method to display the current student collection.
	 */
	public static void printStudents()
	{
		for (int i = 0; i < ids.length; i++)
		{
			System.out.println("Id: " + ids[i] + ", " + "Names: " + names[i] + "\n ");
		}
	} // End Method: printStudents( )

} // End of class: StudentCollection