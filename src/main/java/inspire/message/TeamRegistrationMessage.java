package inspire.message;

import framework.team.Teamable;
import skeleton.newbind.layer.Localizable;
import skeleton.newbind.layer.comm.StrongTypeMessage;

/**
 * Created by Lucas on 6/2/2014.
 */
public class TeamRegistrationMessage extends StrongTypeMessage<Teamable> {

    public TeamRegistrationMessage(Localizable sender, Teamable payload) {
        super(sender, payload);
    }
}
