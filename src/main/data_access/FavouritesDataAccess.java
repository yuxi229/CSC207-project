package data_access;

import use_case.favourites.FavouritesDataAccessInterface;

import java.io.*;
import java.util.*;

public class FavouritesDataAccess implements FavouritesDataAccessInterface {
    private static final String FILE_PATH = "favourites.txt";  // File path for saving data

    @Override
    public List<String> loadFavourites() {
        List<String> favourites = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                favourites.add(line);  // Add each line (room code) to the list
            }
        } catch (IOException e) {
            e.printStackTrace();  // Handle errors
        }
        return favourites;
    }

    @Override
    public void saveFavourites(List<String> favourites) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String roomCode : favourites) {
                writer.write(roomCode);  // Write each room code to the file
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();  // Handle errors
        }
    }
}
