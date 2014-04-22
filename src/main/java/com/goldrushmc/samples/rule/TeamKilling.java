package com.goldrushmc.samples.rule;

import com.goldrushmc.samples.action.Cancel;
import com.goldrushmc.samples.action.SendMessage;
import com.goldrushmc.samples.trigger.IsTeammate;
import framework.game.Gameable;
import framework.game.Triggerable;
import implement.game.rules.AbstractRule;

/**
 * Prevents killing of teammates by other teammates. However, does not guard against touching of the hair or face...
 */
public class TeamKilling extends AbstractRule {

    public TeamKilling(Gameable game) {
        super(null, "Prevents players from killing or harming teammates", game);
        Triggerable t = new IsTeammate(this);
        mapTriggerAndAction(this, t, new SendMessage("Hey, that's your teammate!", this));
        mapTriggerAndAction(this, t, new Cancel(this));
    }
}
