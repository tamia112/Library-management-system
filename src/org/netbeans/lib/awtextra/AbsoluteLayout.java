package org.netbeans.lib.awtextra;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class AbsoluteLayout implements LayoutManager2, java.io.Serializable {
    private final Map<Component, AbsoluteConstraints> constraints = new HashMap<>();

    public void addLayoutComponent(String name, Component comp) {
        throw new IllegalArgumentException("Use add(comp, AbsoluteConstraints)");
    }
    public void addLayoutComponent(Component comp, Object c) {
        if (!(c instanceof AbsoluteConstraints))
            throw new IllegalArgumentException("Constraint must be AbsoluteConstraints");
        constraints.put(comp, (AbsoluteConstraints) c);
    }
    public void removeLayoutComponent(Component comp) { constraints.remove(comp); }

    private Dimension size(Container parent, boolean preferred) {
        int w = 0, h = 0;
        for (Component c : parent.getComponents()) {
            AbsoluteConstraints ac = constraints.get(c);
            if (ac == null) continue;
            Dimension d = preferred ? c.getPreferredSize() : c.getMinimumSize();
            int cw = ac.width == -1 ? d.width : ac.width;
            int ch = ac.height == -1 ? d.height : ac.height;
            w = Math.max(w, ac.x + cw);
            h = Math.max(h, ac.y + ch);
        }
        return new Dimension(w, h);
    }
    public Dimension preferredLayoutSize(Container parent) { return size(parent, true); }
    public Dimension minimumLayoutSize(Container parent) { return size(parent, false); }
    public Dimension maximumLayoutSize(Container target) { return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE); }
    public float getLayoutAlignmentX(Container target) { return 0f; }
    public float getLayoutAlignmentY(Container target) { return 0f; }
    public void invalidateLayout(Container target) { }

    public void layoutContainer(Container parent) {
        for (Component c : parent.getComponents()) {
            AbsoluteConstraints ac = constraints.get(c);
            if (ac == null) continue;
            Dimension d = c.getPreferredSize();
            c.setBounds(ac.x, ac.y,
                ac.width == -1 ? d.width : ac.width,
                ac.height == -1 ? d.height : ac.height);
        }
    }
}
