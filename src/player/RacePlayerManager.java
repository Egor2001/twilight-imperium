package player;

import java.util.ArrayList;

public class RacePlayerManager {

    private ArrayList<Player> players;
    private ArrayList<Race> races;

    public RacePlayerManager() {
        this.players = new ArrayList<>();
        this.races = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
        races.add(player.getRace());
    }

    public Player getPlayer(Race race) {
        for (int idx = 0; idx != races.size(); ++idx) {
            if (races.get(idx) == race) {
                return players.get(idx);
            }
        }

        return null;
    }
}
