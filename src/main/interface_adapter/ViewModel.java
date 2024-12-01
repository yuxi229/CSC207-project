package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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

    public void setState(T state) {
        T oldState = this.state;
        this.state = state;
        firePropertyChange("state", oldState, state);
    }

    public String getName() {
        return name;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    // Added method to allow external classes to fire property changes
    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        pcs.firePropertyChange(propertyName, oldValue, newValue);
    }
}