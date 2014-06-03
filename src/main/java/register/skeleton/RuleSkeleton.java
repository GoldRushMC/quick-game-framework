package register.skeleton;

import framework.game.Actionable;
import framework.game.Ruleable;
import framework.game.Triggerable;
import framework.general.Informable;
import org.bukkit.event.Event;
import register.Resolvable;
import skeleton.newbind.layer.Layerable;
import skeleton.newbind.layer.Localizable;
import skeleton.newbind.layer.comm.Message;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lucas on 6/1/2014.
 */
public class RuleSkeleton<T extends Event> implements Resolvable<Ruleable<T>>, Ruleable<T> {

    private Informable informer;
    private final Collection<Class<? extends Actionable>> actions;
    private final Class<? extends Triggerable<T>> trigger;
    private List<Actionable> resolvedActions;
    Triggerable<T> resolvedTrigger;

    @SafeVarargs
    protected RuleSkeleton(String name, String description, Class<? extends Triggerable<T>> trigger, Class<? extends Actionable>... actions) {
        this.actions = Arrays.asList(actions);
        resolvedActions = new LinkedList<>();
        this.trigger = trigger;
        informer = new Informer(name, description);
    }

    @Override
    public Ruleable<T> resolveInstance() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        for(Class<? extends Actionable> a : actions) {
            resolvedActions.add(a.newInstance());
        }
        resolvedTrigger = trigger.newInstance();
        return this;
    }

    //Ruleable Implementation

    @Override
    public Collection<Actionable> getActions() {
        return resolvedActions;
    }

    //Triggerable Delegation

    @Override
    public boolean matchCondition(T t) {
        return resolvedTrigger.matchCondition(t);
    }

    @Override
    public void resetTrigger() {
        resolvedTrigger.resetTrigger();
    }

    //Informable Delegation

    @Override
    public String getDescription() {
        return informer.getDescription();
    }

    @Override
    public String getName() {
        return informer.getName();
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
