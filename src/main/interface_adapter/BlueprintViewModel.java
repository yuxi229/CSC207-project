package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BlueprintViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String currentBlueprint;

    public void setCurrentBlueprint(String blueprint) {
        String oldBlueprint = this.currentBlueprint;
        this.currentBlueprint = blueprint;
        support.firePropertyChange("blueprint", oldBlueprint, currentBlueprint);
    }

    public String getCurrentBlueprint() {
        return currentBlueprint;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
