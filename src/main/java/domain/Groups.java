package domain;

import java.util.ArrayList;
import java.util.List;

public class Groups {
    List<Integer> groups = new ArrayList<>();

    public Groups() {
        this.groups.add(1);
        this.groups.add(934);
        this.groups.add(Integer.MAX_VALUE);
    }

    public boolean exists(int group) {
        return groups.contains(group);
    }
}
