package com.compiladores;

import com.compiladores.UI.UI;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        FlatArcDarkOrangeIJTheme.setup();
        UIManager.put("Button.arc", 999);
        UIManager.put("Component.arc", 5);
        UIManager.put("CheckBox.arc", 4);
        UIManager.put("ProgressBar.arc", 4);
        UIManager.put("TextComponent.arc", 5);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new UI();
            frame.setVisible(true);
        });

    }
}