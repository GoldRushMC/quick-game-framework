package register.skeleton;

import framework.game.*;
import inspire.Progressable;
import org.bukkit.event.Event;
import skeleton.newbind.layer.Layerable;
import skeleton.newbind.layer.Localizable;
import skeleton.newbind.layer.comm.Message;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/**
 * Created by Lucas on 6/1/2014.
 */
public class GoalSkeleton<T extends Event> extends RuleSkeleton<T> implements Goalable<T> {

    private final Class<? extends Progressable> progressor;
    private Progressable resolvedProgress;

    public GoalSkeleton(String name, String description, Class<? extends Triggerable<T>> trigger, Class<? extends Progressable> progressor, Class<? extends Actionable>... actions) {
        super(name, description, trigger, actions);
        this.progressor = progressor;
    }

    @Override
    public Ruleable<T> resolveInstance() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        resolvedProgress = progressor.newInstance();
        return super.resolveInstance();
    }

    //Progressable delegation

    @Override
    public void registerParticipant(Participatable participant) {
        resolvedProgress.registerParticipant(participant);
    }

    @Override
    public void unregisterParticipant(Participatable participant) {
        resolvedProgress.unregisterParticipant(participant);
    }

    @Override
    public Progress getProgress(Participatable requestor) {
        return resolvedProgress.getProgress(requestor);
    }

    @Override
    public int getRawProgress(Participatable requestor) {
        return resolvedProgress.getRawProgress(requestor);
    }

    @Override
    public String getFormattedProgress(Participatable requestor) {
        return resolvedProgress.getFormattedProgress(requestor);
    }

    @Override
    public Collection<? extends Localizable> getAllLocals() {
        return null;
    }

    @Override
    public void registerLocal(Localizable local) {

    }

    @Override
    public void moveLocal(Localizable toMove, Layerable layerRequested) {

    }

    @Override
    public void sendMessageToLocals(Message message) {

    }

    @Override
    public boolean canDetect(Localizable local) {
        return false;
    }

    @Override
    public Layerable getCurrentLayer() {
        return null;
    }

    @Override
    public void setLocal(Layerable newLayer) {

    }

    @Override
    public void onMessageReceived(Message message) {

    }
}