package use_case.navigation;

import java.util.List;

/**
 * Output Data for the Navigation Use Case.
 */
public class NavigationOutputData {
    private List<String> path;

    public void setPath(List<String> path) {
        // Copy all elements from the input list to the path list.
        this.path = List.copyOf(path);
    }

    public List<String> getPath() {
        return path;
    }
}
