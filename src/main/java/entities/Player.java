package entities;

public class Player {
    private String name;
    private String id;
    private String address;
    private int port;
    private Position position;

    // No-argument constructor
    public Player() {
    }

    // Constructor with arguments if needed for other purposes
    public Player(String name, String id, String address, int port) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.port = port;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", port=" + port +
                ", position=" + position +
                '}';
    }
}
