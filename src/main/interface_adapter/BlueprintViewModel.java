package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Manages the state of the current blueprint and notifies listeners of change.
 */

public class BlueprintViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String currentBlueprint;

    /**
     * Sets the current blueprint and notifies listeners if it changes.
     * @param blueprint the name of the blueprint to set.
     */
    public void setCurrentBlueprint(String blueprint) {
        final String oldBlueprint = this.currentBlueprint;
        this.currentBlueprint = blueprint;
        support.firePropertyChange("blueprint", oldBlueprint, currentBlueprint);
    }

    /**
     * Gets the name of the current blueprint.
     * @return the name of the current blueprint.
     */
    public String getCurrentBlueprint() {
        return currentBlueprint;
    }

    /**
     * Adds a listener to be notified of property changes.
     * @param listener the listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a listener from being notified of property changes.
     * @param listener the listener to remove.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
