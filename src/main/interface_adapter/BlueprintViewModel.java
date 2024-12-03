package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the state of the current blueprint and notifies listeners of change.
 */
public class BlueprintViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private List<String> blueprintList = new ArrayList<>();
    private String currentBlueprint;

    /**
     * Sets the current blueprint and notifies listeners if it changes.
     * @param blueprints the name of the blueprint to set.
     */
    public void setBlueprintList(List<String> blueprints) {
        this.blueprintList = blueprints;
        support.firePropertyChange("blueprintList", null, blueprintList);
    }

    /**
     * Sets the current blueprint and notifies listeners if it changes.
     * @param blueprint the name of the blueprint to set.
     */
    public void setCurrentBlueprint(String blueprint) {
        final String oldBlueprint = this.currentBlueprint;
        this.currentBlueprint = blueprint;
        support.firePropertyChange("currentBlueprint", oldBlueprint, blueprint);
    }

    /**
     * Adds a listener to be notified of property changes.
     * @param listener the listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
