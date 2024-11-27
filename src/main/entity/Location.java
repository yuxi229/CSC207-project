package entity;

/**
 * Base class for all location types (Room, Corridor, etc.).
 */
public abstract class Location {
    private String id;         // Unique identifier
    private int size;          // Size of the location
    private int floor;         // Floor number
    private int imgXpos;       // X-coordinate of the center pixel
    private int imgYpos;       // Y-coordinate of the center pixel

    public Location(String id, int size, int floor, int imgXpos, int imgYpos) {
        this.id = id;
        this.size = size;
        this.floor = floor;
        this.imgXpos = imgXpos;
        this.imgYpos = imgYpos;
    }

    // Getters
    public String getId() { return id; }
    public int getSize() { return size; }
    public int getFloor() { return floor; }
    public int getImgXpos() { return imgXpos; }
    public int getImgYpos() { return imgYpos; }

    // Common toString for debugging
    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", size=" + size +
                ", floor=" + floor +
                ", imgXpos=" + imgXpos +
                ", imgYpos=" + imgYpos +
                '}';
    }
}
