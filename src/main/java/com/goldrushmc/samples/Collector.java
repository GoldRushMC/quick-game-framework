package com.goldrushmc.samples;

import com.goldrushmc.samples.goal.Collect;
import com.goldrushmc.samples.rule.TeamKilling;
import framework.game.Playable;
import implement.game.AbstractGame;
import org.bukkit.Material;

/**
 * User: InspiredIdealist
 * Date: 4/20/2014
 */
public class Collector extends AbstractGame {

    public Collector(Playable playable) {
        super(null, "Collect the emeralds!", true, playable);

        rules.add(new TeamKilling(this));
        goals.add(new Collect(Material.EMERALD, 10, this, playable.getScoreboard()));
    }

    public Collector() {
        this(null);
    }
}
