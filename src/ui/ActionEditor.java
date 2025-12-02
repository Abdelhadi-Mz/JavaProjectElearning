/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;



import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellEditor;

public class ActionEditor extends AbstractCellEditor implements TableCellEditor {

    private JPanel panel = new JPanel();
    private JButton editBtn = new JButton();
    private JButton deleteBtn = new JButton();

    private JTable table;
    private Main parent; // reference to main window

    public ActionEditor(Main parent, JTable table) {
        this.parent = parent;
        this.table = table;

        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        panel.setBackground(Color.WHITE);

        
        editBtn.setIcon(new ImageIcon(getClass().getResource("edit.png")));
        editBtn.setBackground(new Color(25, 118, 210));
        editBtn.setOpaque(true);
        editBtn.setBorderPainted(false);
        editBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteBtn.setIcon(new ImageIcon(getClass().getResource("delete.png")));
        deleteBtn.setBackground(new Color(211, 47, 47));
        deleteBtn.setOpaque(true);
        deleteBtn.setBorderPainted(false);
        deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        String tableName = table.getName();
        if("students".equals(tableName)){
            editBtn.addActionListener(e -> {
                int row = table.getSelectedRow();
                int id = (int) table.getValueAt(row, 0);
                UpdateStudentFrame editForm = new UpdateStudentFrame(id,parent);
                editForm.setVisible(true);
                editForm.setLocationRelativeTo(null);
                stopCellEditing();

            });

            deleteBtn.addActionListener(e -> {
                int row = table.getSelectedRow();
                int id = (int) table.getValueAt(row, 0);
                stopCellEditing();
                parent.onDeleteStudent(id);

            });
        } 
        else if ("courses".equals(tableName)) {
            editBtn.addActionListener(e -> {
                int row = table.getSelectedRow();
                int id = (int) table.getValueAt(row, 0);
                UpdateCoursFrame editForm = new UpdateCoursFrame(id,parent);
                editForm.setVisible(true);
                editForm.setLocationRelativeTo(null);
                stopCellEditing();
                
            });
            
            deleteBtn.addActionListener(e -> {
                int row = table.getSelectedRow();
                int id = (int) table.getValueAt(row, 0);
                stopCellEditing();
                parent.onDeleteCours(id);

            });
        }
        else if("StudentCourses".equals(tableName)){
            
            editBtn.addActionListener(e -> {
            stopCellEditing();
            int row = editingRow;     
            if (row < 0) return;

            int id = (int) table.getValueAt(row, 0);
            UpdateScore editForm = new UpdateScore(id, parent);
            editForm.setVisible(true);
            editForm.setLocationRelativeTo(null);
            });
            
            
            deleteBtn.addActionListener(e -> {
            stopCellEditing();
            int row = editingRow;
            if (row < 0) return;

            int id = (int) table.getValueAt(row, 0);
            parent.onDeleteSc(id);
            });
        }
        panel.add(editBtn);
        panel.add(deleteBtn);
    }

private int editingRow = -1;

@Override
public Component getTableCellEditorComponent(JTable table, Object value,
                                             boolean isSelected, int row, int column) {
    editingRow = row;
    return panel;
}

    @Override
    public Object getCellEditorValue() {
        return null;
    }
}
