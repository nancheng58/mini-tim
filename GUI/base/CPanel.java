package GUI.base;

import javax.swing.*;
import java.awt.*;

public class CPanel extends JPanel {

    public CPanel() {
        ((FlowLayout)getLayout()).setVgap(0);
        ((FlowLayout)getLayout()).setHgap(0);
    }

    public CPanel(LayoutManager layoutManager) {
        this();
        setLayout(layoutManager);
    }
}

