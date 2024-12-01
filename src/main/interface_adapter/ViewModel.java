package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * TODO: Add javadoc.
 * @param <T> The type of the state.
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
     * TODO: Add javadoc.
     * @param state The new state.
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