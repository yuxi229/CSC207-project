package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of User.
 */
class User extends Location {
    private ArrayList<Room> roomList = new ArrayList<>();
    private Map<String, String> nameToCodeMap = new HashMap<>();
    private Map<String, String> codeToNameMap = new HashMap<>();

    public User(String id) {
        super(id);
    }

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

//public class User {
//    private String name;
//    private String uofTEmail;
//    private String passwordHash;
//    private String username;
//
//    public User(String name, String email, String password, String username) {
//        this.name = name;
//        this.uofTEmail = email;
//        this.passwordHash = password;
//        this.username = username;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return uofTEmail;
//    }
//
//    public void setEmail(String email) {
//        this.uofTEmail = email;
//    }
//
//    public String getPasswordHash() {
//        return passwordHash;
//    }
//
//    public void setPasswordHash(String passwordHash) {
//        this.passwordHash = passwordHash;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//}
