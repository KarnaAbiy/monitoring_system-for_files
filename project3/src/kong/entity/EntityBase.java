package kong.entity;

import kong.entity.component.Component;
import kong.entity.component.ComponentLocation;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityBase {
    private final List<Component> lstComponents = new ArrayList<>();
    private ComponentLocation locationComponent = null;

    protected void addComponent(Component component) {
        if(component instanceof ComponentLocation) {
            this.locationComponent = (ComponentLocation) component;
        }
        lstComponents.add(component);
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> T findComponent(Class<T> type) {
        for (Component component : lstComponents) {
            if (type.isInstance(component)) {
                return (T) component;
            }
        }
        return null;
    }

    public ComponentLocation getLocationComponent() {
        return locationComponent;
    }

    public boolean hasComponent(Class<? extends Component> componentClass) {
        return findComponent(componentClass) != null;
    }
}
