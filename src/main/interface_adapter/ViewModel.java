package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A generic ViewModel class to manage state and notify listeners about changes.
 * This class is designed to handle a specific type of state and can be used in various UI contexts.
 *
 * @param <T> The type of the state managed by this ViewModel.
 */
public class ViewModel<T> {
    private T state;
    private final String name;
    private final PropertyChangeSupport pcs;

    public ViewModel(String name) {
        this.name = name;
        this.pcs = new PropertyChangeSupport(this);
    }

    public T getState() {
        return state;
    }

    /**
     * Sets a new state for the ViewModel and notifies listeners if the state changes.
     * @param state The new state to set.
     */
    public void setState(T state) {
        final T oldState = this.state;
        this.state = state;
        firePropertyChange("state", oldState, state);
    }

    public String getName() {
        return name;
    }

    /**
     * Add a property change listener.
     * @param listener The listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * Remove a property change listener.
     * @param listener The listener to remove.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    /**
     * Fire a property change.
     * @param propertyName The name of the property.
     * @param oldValue The old value.
     * @param newValue The new value.
     */
    private void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        pcs.firePropertyChange(propertyName, oldValue, newValue);
    }
}
