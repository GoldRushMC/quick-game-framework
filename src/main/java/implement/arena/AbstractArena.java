package implement.arena;

import framework.arena.Arenable;
import framework.arena.Blueprintable;
import framework.arena.Environmentable;
import framework.game.Gameable;
import framework.game.Playable;
import implement.general.AbstractInformable;
import inspire.Datum;

import java.util.ArrayList;
import java.util.List;

/**
 * The Base implementation for Arenas.
 */
public abstract class AbstractArena extends AbstractInformable implements Arenable {

    protected Blueprintable blueprint;
    protected List<Gameable> supportedGames;
    protected List<Environmentable> environmentList;
    protected Playable currentGame;
    protected Environmentable currentEnvironment;
    protected List<Datum<?>> data;

    protected AbstractArena(String name, String description) {
        super(name, description);
    }

    @Override
    public List<String> getSupportedGameTypes() {
        List<String> gameList = new ArrayList<>();
        for(Gameable g : supportedGames) {
            gameList.add(g.getName());
        }
        return gameList;
    }

    @Override
    public List<Gameable> getSupportedGames() {
        return supportedGames;
    }

    @Override
    public List<Environmentable> getEnvironments() {
        return environmentList;
    }

    @Override
    public Environmentable getCurrentEnvironment() {
        return currentEnvironment;
    }

    @Override
    public boolean changeEnvironment(String environmentName) {
        for(Environmentable e : environmentList) {
            if(e.getName().equals(environmentName)) {
                return changeEnvironment(e);
            }
        }
        return false;
    }

    @Override
    public Playable getCurrentGame() {
        return currentGame;
    }

    @Override
    public Blueprintable getBlueprint() {
        return blueprint;
    }

    @Override
    public List<Datum<?>> getData() {
        return data;
    }
}