

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Destockafric
 */
package ui;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;

public class ActionRenderer extends JPanel implements TableCellRenderer {

    private final JButton editBtn = new JButton();
    private final JButton deleteBtn = new JButton();

    public ActionRenderer() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        editBtn.setIcon(new ImageIcon(getClass().getResource("edit.png")));
        editBtn.setBorder(null);
        editBtn.setContentAreaFilled(false);

        deleteBtn.setIcon(new ImageIcon(getClass().getResource("delete.png")));
        deleteBtn.setBorder(null);
        deleteBtn.setContentAreaFilled(false);

        add(editBtn);
        add(deleteBtn);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}

