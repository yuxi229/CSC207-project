package data_access;

import use_case.favourites.FavouritesDataAccessInterface;

import java.io.*;
import java.util.*;

public class FavouritesDataAccess implements FavouritesDataAccessInterface {

    private static final String FILE_PATH = "favourites.txt";  // Path to the file

    @Override
    public List<String> loadFavourites() {
        List<String> favourites = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                favourites.add(line);  // Add each line (room code) to the list
            }
        } catch (IOException e) {
            e.printStackTrace();  // Handle errors (e.g., if file doesn't exist)
        }
        return favourites;
    }

    @Override
    public void saveFavourites(String roomCode) {
        // First, append the room code to the file
        appendToFile(roomCode);

        // Then, check if the file has more than 3 lines
        trimFile();
    }

    // Helper method to append a room code to the file
    private void appendToFile(String roomCode) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(roomCode);  // Write the room code to the file
            writer.newLine();  // Add a new line after the room code
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to trim the file if there are more than 3 lines
    private void trimFile() {
        try {
            // Read all lines from the file
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }

            // If there are more than 3 lines, remove the first line
            if (lines.size() > 3) {
                lines.remove(0);  // Remove the first line
            }

            // Write the remaining lines back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (String line : lines) {
                    writer.write(line);  // Write each remaining line
                    writer.newLine();  // Add a new line after each room code
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
