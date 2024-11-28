package infrastructure;

import entity.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility to parse raw API data into entity objects.
 */
public class EntityParser {

    public static List<Room> parseRooms(Map<String, Object> rawRooms) {
        List<Room> rooms = new ArrayList<>();
        for (Map.Entry<String, Object> entry : rawRooms.entrySet()) {
            String id = entry.getKey();
            Map<String, Object> data = (Map<String, Object>) entry.getValue();
            Room room = new Room(
                    id,
                    (int) data.get("size"),
                    (int) data.get("floor"),
                    (int) data.get("imgXpos"),
                    (int) data.get("imgYpos"),
                    "Y".equals(data.get("restricted")),
                    (List<String>) data.get("connected")
            );
            rooms.add(room);
        }
        return rooms;
    }

    public static List<Corridor> parseCorridors(Map<String, Object> rawCorridors) {
        List<Corridor> corridors = new ArrayList<>();
        for (Map.Entry<String, Object> entry : rawCorridors.entrySet()) {
            String id = entry.getKey();
            Map<String, Object> data = (Map<String, Object>) entry.getValue();
            Corridor corridor = new Corridor(
                    id,
                    (int) data.get("size"),
                    (int) data.get("floor"),
                    (int) data.get("imgXpos"),
                    (int) data.get("imgYpos"),
                    (List<String>) data.get("connected")
            );
            corridors.add(corridor);
        }
        return corridors;
    }

    public static List<Washroom> parseWashrooms(Map<String, Object> rawWashrooms) {
        List<Washroom> washrooms = new ArrayList<>();
        for (Map.Entry<String, Object> entry : rawWashrooms.entrySet()) {
            String id = entry.getKey();
            Map<String, Object> data = (Map<String, Object>) entry.getValue();
            Washroom washroom = new Washroom(
                    id,
                    (int) data.get("size"),
                    (int) data.get("floor"),
                    (int) data.get("imgXpos"),
                    (int) data.get("imgYpos"),
                    (String) data.get("gender"),
                    (List<String>) data.get("connected")
            );
            washrooms.add(washroom);
        }
        return washrooms;
    }

    public static List<Valve> parseValves(Map<String, Object> rawValves) {
        List<Valve> valves = new ArrayList<>();
        for (Map.Entry<String, Object> entry : rawValves.entrySet()) {
            String id = entry.getKey();
            Map<String, Object> data = (Map<String, Object>) entry.getValue();
            Valve valve = new Valve(
                    id,
                    (int) data.get("size"),
                    (int) data.get("floor"),
                    (int) data.get("imgXpos"),
                    (int) data.get("imgYpos"),
                    (List<String>) data.get("connected")
            );
            valves.add(valve);
        }
        return valves;
    }

    public static List<Stair> parseStairs(Map<String, Object> rawStairs) {
        List<Stair> stairs = new ArrayList<>();
        for (Map.Entry<String, Object> entry : rawStairs.entrySet()) {
            String id = entry.getKey();
            Map<String, Object> data = (Map<String, Object>) entry.getValue();
            Stair stair = new Stair(
                    id,
                    (int) data.get("size"),
                    (int) data.get("floorstart"),
                    (int) data.get("floorend"),
                    (int) data.get("f1x"),
                    (int) data.get("f1y"),
                    (int) data.get("f2x"),
                    (int) data.get("f2y"),
                    (List<String>) data.get("connected")
            );
            stairs.add(stair);
        }
        return stairs;
    }

    public static List<Elevator> parseElevators(Map<String, Object> rawElevators) {
        List<Elevator> elevators = new ArrayList<>();
        for (Map.Entry<String, Object> entry : rawElevators.entrySet()) {
            String id = entry.getKey();
            Map<String, Object> data = (Map<String, Object>) entry.getValue();
            Elevator elevator = new Elevator(
                    id,
                    (int) data.get("size"),
                    (int) data.get("floorstart"),
                    (int) data.get("floorend"),
                    (int) data.get("f1x"),
                    (int) data.get("f1y"),
                    (int) data.get("f2x"),
                    (int) data.get("f2y"),
                    (List<String>) data.get("connected")
            );
            elevators.add(elevator);
        }
        return elevators;
    }
}
