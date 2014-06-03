package inspire.message;

import framework.arena.Blueprintable;
import skeleton.newbind.layer.Localizable;
import skeleton.newbind.layer.comm.StrongTypeMessage;

/**
 * Created by Lucas on 6/2/2014.
 */
public class BlueprintMessage extends StrongTypeMessage<Blueprintable> {

    public BlueprintMessage(Localizable sender, Blueprintable env) {
        super(sender, env);
    }
}
