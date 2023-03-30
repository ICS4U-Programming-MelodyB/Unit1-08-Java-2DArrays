import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
/**
* This program uses 2D array to assign,
* mark to given student.
*
* @author  Melody Berhane
* @version 1.0
*
* @since   2023-03-28
*/

public final class ClassMarks {

    /**
     * This is a private constructor used to satisfy the
     * style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
     */
    private ClassMarks() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This is the main method.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        // Pass path to file as parameter.
        final File file = new File("assignments.txt");

        // Pass path to file as parameter.
        final File students = new File("students.txt");

        // Print to csv file.
        final File fileOut = new File("marks.csv");

        // Declare lists.
        final List<String> listOfStud =
            new ArrayList<String>();
        final List<String> listOfAssign =
                new ArrayList<String>();

        // Declare variable.
        final StringBuilder builder;

        try {
            // Create FileWriter object to write to file.
            final FileWriter fW = new FileWriter(fileOut);
            // Create Scanner object to read from file.
            final Scanner sc = new Scanner(file);
            // Create Scanner object to read from file.
            final Scanner scannerStu =
                new Scanner(students);
            // Create PrintWriter object to write to file.
            final PrintWriter write = new PrintWriter(fW);

            while (scannerStu.hasNextLine()) {
                // Read line as string.
                // Adding each string to list.
                listOfStud.add(scannerStu.nextLine());
            }
            while (sc.hasNextLine()) {
                // Adding each string to list.
                listOfAssign.add(sc.nextLine());
            }

            // Convert from list of strings to array.
            final String[] arrayOfStu =
                    listOfStud.toArray(new String[0]);
            final String[] arrayOfAssign =
                    listOfAssign.toArray(new String[0]);

            // Declare the 2D array.
            final String[][] markArrays;

            // Call the function.
            markArrays = GenMarks(arrayOfStu, arrayOfAssign);

            // Usage of loop to add 2D array into csv file.
            builder = new StringBuilder();
            for (int counter = 0;
                counter < markArrays.length; counter++) {
                // Write name in first column.
                builder.append(markArrays[counter][0]);
                builder.append(", ");

                // Write marks in remaining columns.
                for (int counter2 = 1; counter2
                    < markArrays[counter].length - 1; counter2++) {
                    if (counter2 != markArrays[counter].length - 2) {
                        builder.append(markArrays[counter][counter2]);
                        builder.append(", ");
                    } else {
                        builder.append(markArrays[counter][counter2]);
                        builder.append(" ");
                    }
                }
                builder.append("\n");
            }
            // Write to file.
            write.print(builder.toString());

            // Display to user in console.
            System.out.print(builder.toString());

            // Closes scanner & writer.
            write.close();
            sc.close();
            scannerStu.close();
        } catch (IOException error) {
            System.out.println("An error occurred: "
                    + error.getMessage());
        }
    }
    /**
    * This function generates a random mark,
    * and assign it to each name.
    *
    * @param studArray passed
    * @param assignArray passed.
    * @return array2DMarks.
    */

    public static String[][] GenMarks(String[] studArray, String[]assignArray) {

        // Declare 2D arrays of strings.
        final String[][] array2DMarks = new
        String[studArray.length + 1][assignArray.length + 2];

        // Declare variable.
        final Random random = new Random();

        // Assign name at index 0,0.
        array2DMarks[0][0] = "Name";

        // Initalize counter.
        int row = 1;

        // Usage of loop to copy each element into the 2D array.
        for (String assignName : assignArray) {
            array2DMarks[0][row] = assignName;
            row++;
        }
        // Usage of loop to copy each element into the 2D array.
        int column = 1;
        for (String student : studArray) {
            array2DMarks[column][0] = student;
            column++;
        }

        // Populate cell of marks into the 2D array.
        for (int markRow = 1; markRow
            < studArray.length + 1; markRow++) {
            for (int markCol = 1; markCol
                < assignArray.length + 1; markCol++) {
                // Generate random marks using standard dev.
                final double marks =
                    random.nextGaussian() * 10 + 75;
                // Set marks into positions.
                array2DMarks[markRow][markCol] =
                    Integer.toString((int) marks);
            }
        }
        // Return results back to main.
        return array2DMarks;
    }
}
