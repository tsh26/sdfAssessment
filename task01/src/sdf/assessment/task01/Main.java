package sdf.assessment.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Scanner;

public class Main {

    // only take into account column 1,2,3 respectively.
    public static final int COL_APP = 0;
    public static final int COL_Category = 1;
    public static final int COL_Rating = 2;

    //standard reading file:
    public static void main(String[] args) throws IOException {

        System.out.println("Enter file name to be read (googleplaystore.csv)");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        //String filePath = "sdf-assessment-template/task01/googleplaystore.csv";

        try (FileReader fr = new FileReader(filePath)) {
            BufferedReader br = new BufferedReader(fr);
            br.readLine();

            Map<String, List<CategoryManager>> appCategory = br.lines()
                    .map(row -> row.trim().split(","))
                    .map(fields -> new CategoryManager(fields[COL_APP], fields[COL_Category], fields[COL_Rating]))
                    .collect(Collectors.groupingBy(CategoryManager::getCategory));

            // loop (every entry in appCategory map)        
            for (Map.Entry<String, List<CategoryManager>> entry : appCategory.entrySet()) {
                // get key from previous map
                String category = entry.getKey(); 
                // get value from current map
                List<CategoryManager> apps = entry.getValue();

                // Calculate average rating
                double averageRating = apps.stream()
                        .mapToDouble(app -> Double.parseDouble(app.getRating()))
                        .average();

                CategoryManager highestRatedApp = apps.stream()
                        .max(Comparator.comparingDouble(app -> Double.parseDouble(app.getRating())));

                CategoryManager lowestRatedApp = apps.stream()
                        .filter(app -> app.getRating())
                        .min(Comparator.comparingDouble(app -> Double.parseDouble(app.getRating())));
     
                float discardedRecords = apps.stream()
                        .filter(app -> app.getRating().equalsIgnoreCase("NaN"))
                        .count();

                System.out.println("Category: " + category);
                System.out.println("Highest rated application: " + (highestRatedApp.getApp()));
                System.out.println("Highest rating: " + (highestRatedApp.getRating()));
                System.out.println("Lowest rated application: " + (lowestRatedApp.getApp()));
                System.out.println("Lowest rating: " + (lowestRatedApp.getRating()));
                System.out.println("Average rating: " + averageRating);
                System.out.println("No. of applications in category:" + apps.size());
                System.out.println("No. of discarded records: " + discardedRecords);
                System.out.println("Total number of lines read from the CSV file: " + (apps.size()));
                System.out.println();
            }
        }
    }
}



/*
// This works for counting apps in each category:
public class Main {
    public static final int COL_APP = 0;
    public static final int COL_Category = 1;
    public static final int COL_Rating = 2;

    public static void main(String[] args) throws IOException {
        try (FileReader fr = new FileReader("assessment/sdf-assessment-template/task01/googleplaystore.csv")) {
            BufferedReader br = new BufferedReader(fr);
            // br.readLine()

            // Mapping total number of applications beloging to each category
            Map<String, List<CategoryManager>> appCategory = br.lines()
                    .skip(1)
                    .map(row -> row.trim().split(","))
                    .map(fields -> new CategoryManager(fields[COL_APP], fields[COL_Category], fields[COL_Rating]))
                    .collect(Collectors.groupingBy(app -> app.getCategory()));

            // Total number of applications beloging to each category
            for (String category : appCategory.keySet()) {
                List<CategoryManager> app = appCategory.get(category);
                System.out.printf("Category: " + category + "\n Number of apps: " + app.size() + " \n");
                System.out.println();

            }
            }
        }
    }


own notes to ignore:
Initial split into array by columns.
 * String csvFile = "df-assessment-template/task01/googleplaystore.csv";
 * File file = new File(csvFile);
 * 
 * List<String> lines = Files.readAllLines(file.toPath(),
 * StandardCharsets.UTF_8);
 * for(String line:lines) {
 * String[] array = line.split(",");
 * String App = array[0];
 * String Category = array[1];
 * String Rating = array[2];
 * 
 * System.out.println("(App: " + App + ") (Category: " + Category +
 * ") (Rating: " + Rating + ")" );
 */
