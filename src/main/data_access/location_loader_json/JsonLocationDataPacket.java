package data_access.location_loader_json;

/**
 * A class that represents the data packet that is received from the API.
 */
public class JsonLocationDataPacket {
    private final String roomsJson;
    private final String valvesJson;
    private final String corridorsJson;
    private final String stairsJson;
    private final String elevatorsJson;

    public JsonLocationDataPacket(String roomsJson, String valvesJson, String corridorsJson, String stairsJson,
                                  String elevatorsJson) {
        this.roomsJson = roomsJson;
        this.valvesJson = valvesJson;
        this.corridorsJson = corridorsJson;
        this.stairsJson = stairsJson;
        this.elevatorsJson = elevatorsJson;
    }

    public String getRoomsJson() {
        return roomsJson;
    }

    public String getValvesJson() {
        return valvesJson;
    }

    public String getCorridorsJson() {
        return corridorsJson;
    }

    public String getStairsJson() {
        return stairsJson;
    }

    public String getElevatorsJson() {
        return elevatorsJson;
    }
}
