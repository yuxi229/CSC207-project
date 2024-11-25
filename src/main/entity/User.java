package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of User.
 */
class User {
    private ArrayList<Room> roomList = new ArrayList<>();
    private Map<String, String> nameToCodeMap = new HashMap<>();
    private Map<String, String> codeToNameMap = new HashMap<>();

    public void addRoom(Room room) {
        roomList.add(room);
        nameToCodeMap.put(room.getRoomCode(), room.getRoomCode());
        codeToNameMap.put(room.getRoomCode(), room.getRoomCode());
    }

    public void changeRoomName(Room room, String newName) {
        String oldCode = room.getRoomCode();
        room.setRoomCode(newName);
        nameToCodeMap.remove(oldCode);
        codeToNameMap.remove(oldCode);
        nameToCodeMap.put(newName, newName);
        codeToNameMap.put(newName, newName);
    }

    public ArrayList<Room> getList() {
        return roomList;
    }

    public String nameToCode(String name) {
        return nameToCodeMap.getOrDefault(name, "Room code not found");
    }

    public String codeToName(String name) {
        return codeToNameMap.getOrDefault(name, "Room name not found");
    }
}
