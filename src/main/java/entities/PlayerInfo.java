package entities;

import javafx.geometry.Pos;

import java.util.ArrayList;

public class PlayerInfo {
    private String playerId;
    private Position position;
    private ArrayList<PlayerInfo> others;

    public PlayerInfo(String playerId, Position startingPosition, ArrayList<PlayerInfo> others) {
        this.playerId = playerId;
        this.position = startingPosition;
        this.others = others;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public ArrayList<PlayerInfo> getOthers() {
        return others;
    }

    public void setOthers(ArrayList<PlayerInfo> others) {
        this.others = others;
    }
}
