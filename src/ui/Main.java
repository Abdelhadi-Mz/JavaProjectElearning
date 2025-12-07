/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entities.Cours;
import entities.Student;
import entities.StudentCours;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import services.CoursService;
import services.StudentCoursService;
import services.StudentService;



/**
 *
 * @author Destockafric
 */
public class Main extends javax.swing.JFrame {
    StudentService ss=null;
    CoursService cs=null;
    //StudentCours sc=null;
    StudentCoursService scs=null;
    DefaultTableModel model=null;
    DefaultTableModel model1=null;
    DefaultTableModel model2=null;


    /**
     * Creates new form DashboradFrame
     */
    public Main() {
        initComponents();
        this.setTitle("E-leraning ");
        
        ss =new StudentService();
        cs=new CoursService();
        scs=new StudentCoursService();
        //sc=new StudentCours();
        menu.setVisible(true);
        Dashborad.setVisible(true);
        Learners.setVisible(false);
        cours.setVisible(false);
        progress.setVisible(false);
        model = (DefaultTableModel) StudentsList.getModel();
        loadStd();
        model1 = (DefaultTableModel) CoursesList.getModel();
        loadCrs();
        loadStdDrop();
        loadCrsDrop();
        model2 = (DefaultTableModel) StudentcoursList.getModel();
        loadProg();
        loadAverageScoreChart();
        setLocationRelativeTo(null);
        setResizable(false);
        loadDashStats();
        setIconImage();

    }
    public void loadStd(){
        model.setRowCount(0);
        for (Student s : ss.findAll()){
            model.addRow(new Object[]{
                s.getId(),
                s.getName(),
                s.getEmail(),
                s.getDate()
            });
        }
    }
    
    public void loadCrs(){
        model1.setRowCount(0);
        for(Cours c : cs.findAll()){
            model1.addRow(new Object[]{
                c.getId(),
                c.getCategorie(),
                c.getTitle(),
                c.getNb_H()
            });
        }
    }
    
    
    public void onDeleteStudent(int id) {
            int confirm = JOptionPane.showConfirmDialog(this, "Delete this student?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    ss.delete(ss.findById(id));
                    loadStd();
                }
    }
    public void onDeleteCours(int id) {
            int confirm = JOptionPane.showConfirmDialog(this, "Delete this Cours?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    cs.delete(cs.findById(id));
                    loadCrs();
                }
    }
    public void onDeleteSc(int student_id,int cours_id) {
            int confirm = JOptionPane.showConfirmDialog(this, "remove Student?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    scs.delete(scs.findBy2Id(student_id,cours_id));
                    loadProg();
                }
    }
    public void loadStdDrop() {
        DefaultComboBoxModel<Student> model =(DefaultComboBoxModel<Student>) stdDropDown.getModel();
        model.removeAllElements(); 
        for (Student s : ss.findAll()) {
            model.addElement(s);   
        }
    }
    public void loadCrsDrop() {
        DefaultComboBoxModel<Cours> model =(DefaultComboBoxModel<Cours>) crsDropDown.getModel();
        model.removeAllElements(); 
        for (Cours c : cs.findAll()) {
            model.addElement(c);   
        }
    }
    
    public void loadProg(){
        model2.setRowCount(0);
        for(StudentCours sc : scs.findAll()){
            model2.addRow(new Object[]{
                sc.getStudent().getId(),
                sc.getCours().getId(),
                sc.getStudent().getName(),
                sc.getCours().getTitle(),
                sc.getDate(),
                sc.getScore()
            });
        
        }
    }
    public void loadAverageScoreChart() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<String, Double> data = scs.getAverageScorePerCourse();

        for (String courseName : data.keySet()) {
            dataset.addValue(data.get(courseName), "Score Moyen", courseName);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Moyenne des Scores par Cours",
                "Cours",
                "Score Moyen",
                dataset
        );

        ChartPanel cp = new ChartPanel(chart);
        cp.setPreferredSize(chartP.getSize());

        chartP.removeAll();
        chartP.setLayout(new java.awt.BorderLayout());
        chartP.add(cp, BorderLayout.CENTER);
        chartP.validate();
    
    }
    public void loadDashStats() {
        int countStudents = ss.findAll().size();
        int countCourses = cs.findAll().size();
        int countAssigned = scs.findAll().size();

        lblStudents.setText(String.valueOf(countStudents));
        lblCourses.setText(String.valueOf(countCourses));
        lblAssigned.setText(String.valueOf(countAssigned));
    }
    public void setIconImage() {
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8-e-learning-35.png"));
        this.setIconImage(icon);
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnDashborad = new javax.swing.JButton();
        btnCourses = new javax.swing.JButton();
        btnLearners = new javax.swing.JButton();
        btnProgress = new javax.swing.JButton();
        rightSide = new javax.swing.JPanel();
        Learners = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        nameTxt = new javax.swing.JTextField();
        emailTxt = new javax.swing.JTextField();
        addbtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        dateField1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        StudentsList = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Dashborad = new javax.swing.JPanel();
        chartP = new javax.swing.JPanel();
        statsPanel = new javax.swing.JPanel();
        panelStudents = new javax.swing.JPanel();
        lblStudents = new javax.swing.JLabel();
        panelCourses = new javax.swing.JPanel();
        lblCourses = new javax.swing.JLabel();
        panelAssigned = new javax.swing.JPanel();
        lblAssigned = new javax.swing.JLabel();
        lblStudentsTitle = new javax.swing.JLabel();
        lblCoursesTitle = new javax.swing.JLabel();
        lblAssignedTitle = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cours = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        categorieTxt = new javax.swing.JTextField();
        titleTxt = new javax.swing.JTextField();
        addbtn1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        nbhSpin = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        CoursesList = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        filterTxt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        progress = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        addbtn2 = new javax.swing.JButton();
        dateSC = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        stdDropDown = new javax.swing.JComboBox<Student>();
        crsDropDown = new javax.swing.JComboBox<Cours>();
        scoreSpinner = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        StudentcoursList = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 400));
        setPreferredSize(new java.awt.Dimension(1270, 877));
        setSize(new java.awt.Dimension(1290, 872));

        menu.setBackground(new java.awt.Color(14, 71, 140));
        menu.setPreferredSize(new java.awt.Dimension(350, 800));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("E-LEARNING APP");
        jLabel1.setAlignmentX(0.5F);
        jLabel1.setAlignmentY(0.0F);
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 20, 0));
        jLabel1.setMaximumSize(new java.awt.Dimension(200, 40));
        jLabel1.setMinimumSize(new java.awt.Dimension(200, 40));
        jLabel1.setPreferredSize(new java.awt.Dimension(200, 40));

        btnDashborad.setBackground(new java.awt.Color(7, 59, 122));
        btnDashborad.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnDashborad.setForeground(new java.awt.Color(255, 255, 255));
        btnDashborad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/icons8-dashboard-24.png"))); // NOI18N
        btnDashborad.setText("Dashborad");
        btnDashborad.setContentAreaFilled(false);
        btnDashborad.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnDashborad.setMaximumSize(new java.awt.Dimension(93, 43));
        btnDashborad.setMinimumSize(new java.awt.Dimension(93, 43));
        btnDashborad.setOpaque(true);
        btnDashborad.setPreferredSize(new java.awt.Dimension(93, 43));
        btnDashborad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboradActionPerformed(evt);
            }
        });

        btnCourses.setBackground(new java.awt.Color(7, 59, 122));
        btnCourses.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnCourses.setForeground(new java.awt.Color(255, 255, 255));
        btnCourses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/courses.png"))); // NOI18N
        btnCourses.setText(" Courses");
        btnCourses.setContentAreaFilled(false);
        btnCourses.setMaximumSize(new java.awt.Dimension(93, 43));
        btnCourses.setMinimumSize(new java.awt.Dimension(93, 43));
        btnCourses.setOpaque(true);
        btnCourses.setPreferredSize(new java.awt.Dimension(93, 43));
        btnCourses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCoursesActionPerformed(evt);
            }
        });

        btnLearners.setBackground(new java.awt.Color(7, 59, 122));
        btnLearners.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnLearners.setForeground(new java.awt.Color(255, 255, 255));
        btnLearners.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/learners(1).png"))); // NOI18N
        btnLearners.setText(" Learners");
        btnLearners.setContentAreaFilled(false);
        btnLearners.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLearners.setMaximumSize(new java.awt.Dimension(93, 43));
        btnLearners.setMinimumSize(new java.awt.Dimension(93, 43));
        btnLearners.setOpaque(true);
        btnLearners.setPreferredSize(new java.awt.Dimension(93, 43));
        btnLearners.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLearnersActionPerformed(evt);
            }
        });

        btnProgress.setBackground(new java.awt.Color(7, 59, 122));
        btnProgress.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnProgress.setForeground(new java.awt.Color(255, 255, 255));
        btnProgress.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/icons8-progress-24.png"))); // NOI18N
        btnProgress.setText("Progress");
        btnProgress.setContentAreaFilled(false);
        btnProgress.setMaximumSize(new java.awt.Dimension(93, 43));
        btnProgress.setMinimumSize(new java.awt.Dimension(93, 43));
        btnProgress.setOpaque(true);
        btnProgress.setPreferredSize(new java.awt.Dimension(93, 43));
        btnProgress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProgressActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnProgress, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(btnCourses, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(btnLearners, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDashborad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(btnDashborad, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(btnLearners, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCourses, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );

        rightSide.setPreferredSize(new java.awt.Dimension(919, 800));
        rightSide.setLayout(new java.awt.CardLayout());

        Learners.setBackground(new java.awt.Color(238, 243, 251));
        Learners.setAlignmentX(0.0F);
        Learners.setAlignmentY(0.0F);
        Learners.setPreferredSize(new java.awt.Dimension(919, 800));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Add new Student", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 20), new java.awt.Color(7, 59, 122))); // NOI18N

        nameTxt.setPreferredSize(new java.awt.Dimension(200, 36));
        nameTxt.setBackground(new java.awt.Color(255, 255, 255));
        nameTxt.setForeground(new java.awt.Color(30, 30, 30));
        nameTxt.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));

        // Normal border (light gray)
        javax.swing.border.Border normalBorder =
        javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(211, 216, 224), // same dark blue as your menu
                2,                              // thickness
                true
            ),
            javax.swing.BorderFactory.createEmptyBorder(8, 10, 8, 10)
        );

        // Hover border (darker gray)
        javax.swing.border.Border hoverBorder =
        javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(211, 216, 224), 2, true // inner blue border
            ),
            javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(
                    new java.awt.Color(211, 216, 224), 2, true // outer glow
                ),
                javax.swing.BorderFactory.createEmptyBorder(3, 5, 3, 5)
            )
        );

        // Apply normal border first
        nameTxt.setBorder(normalBorder);

        // Hover effect
        nameTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nameTxt.setBorder(hoverBorder);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nameTxt.setBorder(normalBorder);
            }
        });

        // Placeholder text
        nameTxt.setText("Enter name");
        nameTxt.setForeground(new java.awt.Color(150, 150, 150)); // placeholder gray

        nameTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (nameTxt.getText().equals("Enter name")) {
                    nameTxt.setText("");
                    nameTxt.setForeground(new java.awt.Color(30, 30, 30)); // normal text
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (nameTxt.getText().trim().isEmpty()) {
                    nameTxt.setText("Enter name");
                    nameTxt.setForeground(new java.awt.Color(150, 150, 150)); // placeholder
                }
            }
        });
        nameTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTxtActionPerformed(evt);
            }
        });

        emailTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTxtActionPerformed(evt);
            }
        });

        addbtn.setBackground(new java.awt.Color(14, 71, 140));
        addbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addbtn.setForeground(new java.awt.Color(255, 255, 255));
        addbtn.setText("Add");
        addbtn.setContentAreaFilled(false);
        addbtn.setOpaque(true);
        addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtnActionPerformed(evt);
            }
        });

        jLabel2.setText("Name:");

        jLabel3.setText("email:");

        jLabel4.setText("Inscription date:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(emailTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                .addComponent(nameTxt)))
                        .addGap(103, 103, 103)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(dateField1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(addbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(addbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        emailTxt.setBackground(new java.awt.Color(255, 255, 255));
        emailTxt.setForeground(new java.awt.Color(30, 30, 30));
        emailTxt.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));

        // Apply the normal border initially
        emailTxt.setBorder(normalBorder);

        emailTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                emailTxt.setBorder(hoverBorder);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                emailTxt.setBorder(normalBorder);
            }
        });

        // Placeholder text
        emailTxt.setText("Enter email");
        emailTxt.setForeground(new java.awt.Color(150, 150, 150)); // placeholder color

        emailTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (emailTxt.getText().equals("Enter email")) {
                    emailTxt.setText("");
                    emailTxt.setForeground(new java.awt.Color(30, 30, 30)); // normal text
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (emailTxt.getText().trim().isEmpty()) {
                    emailTxt.setText("Enter email");
                    emailTxt.setForeground(new java.awt.Color(150, 150, 150)); // placeholder color
                }
            }
        });
        emailTxt.setPreferredSize(new java.awt.Dimension(200, 36));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        StudentsList.setName("students");
        StudentsList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Name", "Email", "Iscription Date", "Action"
            }
        ));
        StudentsList.setRowHeight(40);
        StudentsList.setRowHeight(40);

        StudentsList.getColumn("Action").setCellRenderer(new ActionRenderer());
        StudentsList.getColumn("Action").setCellEditor(new ActionEditor(this, StudentsList));
        jScrollPane1.setViewportView(StudentsList);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(7, 59, 122));
        jLabel6.setText("Student list:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(10, 42, 67));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/StudentMan.png"))); // NOI18N
        jLabel5.setText("  Students Managment");

        javax.swing.GroupLayout LearnersLayout = new javax.swing.GroupLayout(Learners);
        Learners.setLayout(LearnersLayout);
        LearnersLayout.setHorizontalGroup(
            LearnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LearnersLayout.createSequentialGroup()
                .addGap(276, 276, 276)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LearnersLayout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addGroup(LearnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(108, 108, 108))
        );
        LearnersLayout.setVerticalGroup(
            LearnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LearnersLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        rightSide.add(Learners, "card2");

        Dashborad.setBackground(new java.awt.Color(230, 241, 250));
        Dashborad.setAlignmentX(0.0F);
        Dashborad.setAlignmentY(0.0F);
        Dashborad.setPreferredSize(new java.awt.Dimension(919, 800));

        chartP.setBackground(new java.awt.Color(230, 241, 250));

        javax.swing.GroupLayout chartPLayout = new javax.swing.GroupLayout(chartP);
        chartP.setLayout(chartPLayout);
        chartPLayout.setHorizontalGroup(
            chartPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 919, Short.MAX_VALUE)
        );
        chartPLayout.setVerticalGroup(
            chartPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );

        statsPanel.setLayout(new GridLayout(1, 3, 20, 0));
        statsPanel.setBackground(new java.awt.Color(230, 241, 250));

        panelStudents.setBackground(new Color(66, 135, 245));
        panelCourses.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblStudents = new JLabel("0", SwingConstants.CENTER);
        lblStudents.setFont(new Font("Segoe UI", Font.BOLD, 55));
        lblStudents.setForeground(Color.WHITE);
        panelStudents.add(lblStudents, BorderLayout.CENTER);

        javax.swing.GroupLayout panelStudentsLayout = new javax.swing.GroupLayout(panelStudents);
        panelStudents.setLayout(panelStudentsLayout);
        panelStudentsLayout.setHorizontalGroup(
            panelStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStudentsLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lblStudents, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        panelStudentsLayout.setVerticalGroup(
            panelStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelStudentsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblStudents, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        panelCourses.setBackground(new Color(76, 175, 80)); // green
        panelCourses.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblCourses = new JLabel("0", SwingConstants.CENTER);
        lblCourses.setFont(new Font("Segoe UI", Font.BOLD, 55));
        lblCourses.setForeground(Color.WHITE);
        panelCourses.add(lblCourses, BorderLayout.CENTER);

        javax.swing.GroupLayout panelCoursesLayout = new javax.swing.GroupLayout(panelCourses);
        panelCourses.setLayout(panelCoursesLayout);
        panelCoursesLayout.setHorizontalGroup(
            panelCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCoursesLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblCourses, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        panelCoursesLayout.setVerticalGroup(
            panelCoursesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCoursesLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblCourses, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        panelAssigned.setBackground(new Color(255, 152, 0)); // orange
        panelAssigned.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblAssigned = new JLabel("0", SwingConstants.CENTER);
        lblAssigned.setFont(new Font("Segoe UI", Font.BOLD, 55));
        lblAssigned.setForeground(Color.WHITE);

        javax.swing.GroupLayout panelAssignedLayout = new javax.swing.GroupLayout(panelAssigned);
        panelAssigned.setLayout(panelAssignedLayout);
        panelAssignedLayout.setHorizontalGroup(
            panelAssignedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAssignedLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(lblAssigned, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        panelAssignedLayout.setVerticalGroup(
            panelAssignedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAssignedLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblAssigned, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblStudentsTitle.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        lblStudentsTitle.setForeground(new Color(70, 90, 120));
        lblStudentsTitle.setText("Students");

        lblCoursesTitle.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        lblCoursesTitle.setForeground(new Color(70, 90, 120));
        lblCoursesTitle.setText("Courses");

        lblAssignedTitle.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        lblAssignedTitle.setForeground(new Color(70, 90, 120));
        lblAssignedTitle.setText("Students Assigned");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(10, 42, 67));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/icons8-combo-chart-35.png"))); // NOI18N
        jLabel16.setText(" Stats");

        javax.swing.GroupLayout statsPanelLayout = new javax.swing.GroupLayout(statsPanel);
        statsPanel.setLayout(statsPanelLayout);
        statsPanelLayout.setHorizontalGroup(
            statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statsPanelLayout.createSequentialGroup()
                .addGroup(statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statsPanelLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(panelStudents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(statsPanelLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(lblStudentsTitle)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statsPanelLayout.createSequentialGroup()
                        .addComponent(panelCourses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statsPanelLayout.createSequentialGroup()
                        .addComponent(lblCoursesTitle)
                        .addGap(143, 143, 143)))
                .addGroup(statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelAssigned, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAssignedTitle))
                .addGap(61, 61, 61))
            .addGroup(statsPanelLayout.createSequentialGroup()
                .addGap(375, 375, 375)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        statsPanelLayout.setVerticalGroup(
            statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statsPanelLayout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelStudents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCourses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelAssigned, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAssignedTitle)
                    .addComponent(lblStudentsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCoursesTitle))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout DashboradLayout = new javax.swing.GroupLayout(Dashborad);
        Dashborad.setLayout(DashboradLayout);
        DashboradLayout.setHorizontalGroup(
            DashboradLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chartP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(DashboradLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        DashboradLayout.setVerticalGroup(
            DashboradLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DashboradLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(statsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chartP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        rightSide.add(Dashborad, "card3");

        cours.setBackground(new java.awt.Color(238, 243, 251));
        cours.setAlignmentX(0.0F);
        cours.setAlignmentY(0.0F);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Add new Cours", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 20), new java.awt.Color(7, 59, 122))); // NOI18N

        categorieTxt.setPreferredSize(new java.awt.Dimension(200, 36));
        categorieTxt.setBackground(new java.awt.Color(255, 255, 255));
        categorieTxt.setForeground(new java.awt.Color(30, 30, 30));
        categorieTxt.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));

        // Apply normal border first
        categorieTxt.setBorder(normalBorder);

        // Hover effect
        categorieTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                categorieTxt.setBorder(hoverBorder);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                categorieTxt.setBorder(normalBorder);
            }
        });

        // Placeholder text
        categorieTxt.setText("Enter the categorie");
        categorieTxt.setForeground(new java.awt.Color(150, 150, 150)); // placeholder gray

        categorieTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (categorieTxt.getText().equals("Enter the categorie")) {
                    categorieTxt.setText("");
                    categorieTxt.setForeground(new java.awt.Color(30, 30, 30)); // normal text
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (categorieTxt.getText().trim().isEmpty()) {
                    categorieTxt.setText("Enter the categorie");
                    categorieTxt.setForeground(new java.awt.Color(150, 150, 150)); // placeholder
                }
            }
        });
        categorieTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categorieTxtActionPerformed(evt);
            }
        });

        titleTxt.setPreferredSize(new java.awt.Dimension(200, 36));
        titleTxt.setBackground(new java.awt.Color(255, 255, 255));
        titleTxt.setForeground(new java.awt.Color(30, 30, 30));
        titleTxt.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));

        // Apply normal border first
        titleTxt.setBorder(normalBorder);

        // Hover effect
        titleTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                categorieTxt.setBorder(hoverBorder);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                categorieTxt.setBorder(normalBorder);
            }
        });

        // Placeholder text
        titleTxt.setText("Enter the title");
        titleTxt.setForeground(new java.awt.Color(150, 150, 150)); // placeholder gray

        titleTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (titleTxt.getText().equals("Enter the title")) {
                    titleTxt.setText("");
                    titleTxt.setForeground(new java.awt.Color(30, 30, 30)); // normal text
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (titleTxt.getText().trim().isEmpty()) {
                    titleTxt.setText("Enter the title");
                    titleTxt.setForeground(new java.awt.Color(150, 150, 150)); // placeholder
                }
            }
        });
        titleTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleTxtActionPerformed(evt);
            }
        });

        addbtn1.setBackground(new java.awt.Color(14, 71, 140));
        addbtn1.setForeground(new java.awt.Color(255, 255, 255));
        addbtn1.setText("Add");
        addbtn1.setContentAreaFilled(false);
        addbtn1.setOpaque(true);
        addbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtn1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Categorie:");

        jLabel8.setText("Title:");

        jLabel9.setText("Number of Hours:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(titleTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                .addComponent(categorieTxt)))
                        .addGap(103, 103, 103)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(nbhSpin, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addComponent(addbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(categorieTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(0, 35, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(addbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nbhSpin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        // Apply the normal border initially

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        CoursesList.setName("courses");
        CoursesList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Categorie", "Title", "number of hours", "Action"
            }
        ));
        CoursesList.setRowHeight(40);
        CoursesList.getColumn("Action").setCellRenderer(new ActionRenderer());
        CoursesList.getColumn("Action").setCellEditor(new ActionEditor(this, CoursesList));
        jScrollPane2.setViewportView(CoursesList);
        DefaultTableModel model1 = (DefaultTableModel) CoursesList.getModel();
        TableRowSorter<DefaultTableModel> sorterCours = new TableRowSorter<>(model1);
        CoursesList.setRowSorter(sorterCours);

        jTextField2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(7, 59, 122));
        jTextField2.setText("Courses List");
        jTextField2.setBorder(null);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        filterTxt.setText("Sort by categorie");
        filterTxt.setForeground(new java.awt.Color(150, 150, 150)); // placeholder color

        filterTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (filterTxt.getText().equals("Sort by categorie")) {
                    filterTxt.setText("");
                    filterTxt.setForeground(new java.awt.Color(30, 30, 30)); // normal text
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (filterTxt.getText().trim().isEmpty()) {
                    filterTxt.setText("Sort by categorie");
                    filterTxt.setForeground(new java.awt.Color(150, 150, 150)); // placeholder color
                }
            }
        });
        filterTxt.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { applyFilter(); }
            @Override
            public void removeUpdate(DocumentEvent e) { applyFilter(); }
            @Override
            public void changedUpdate(DocumentEvent e) { applyFilter(); }

            private void applyFilter() {
                String text = filterTxt.getText().trim();

                if (text.isEmpty()) {
                    sorterCours.setRowFilter(null); // **always use null**, not empty regex
                } else {
                    sorterCours.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1));
                }
            }
        });
        filterTxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                loadCrs();
                sorterCours.setRowFilter(null);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filterTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filterTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(10, 42, 67));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/CourseMang.png"))); // NOI18N
        jLabel14.setText("  Cours Managment");

        javax.swing.GroupLayout coursLayout = new javax.swing.GroupLayout(cours);
        cours.setLayout(coursLayout);
        coursLayout.setHorizontalGroup(
            coursLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, coursLayout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addGroup(coursLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(108, 108, 108))
            .addGroup(coursLayout.createSequentialGroup()
                .addGap(305, 305, 305)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        coursLayout.setVerticalGroup(
            coursLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coursLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rightSide.add(cours, "card4");

        progress.setBackground(new java.awt.Color(238, 243, 251));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Assign Student to Course", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 20), new java.awt.Color(7, 59, 122))); // NOI18N

        addbtn2.setBackground(new java.awt.Color(14, 71, 140));
        addbtn2.setForeground(new java.awt.Color(255, 255, 255));
        addbtn2.setText("Add");
        addbtn2.setContentAreaFilled(false);
        addbtn2.setOpaque(true);
        addbtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtn2ActionPerformed(evt);
            }
        });

        jLabel12.setText("Inscription date:");

        stdDropDown.setModel(new javax.swing.DefaultComboBoxModel<>());

        crsDropDown.setModel(new javax.swing.DefaultComboBoxModel<>());

        jLabel10.setText("Student:");

        jLabel11.setText("Cours:");

        jLabel13.setText("Score:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scoreSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stdDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                .addGap(153, 153, 153)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(crsDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateSC, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(173, 173, 173)
                .addComponent(addbtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stdDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(crsDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateSC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(scoreSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(addbtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        StudentcoursList.setName("StudentCourses");
        StudentcoursList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "student_id", "cours_id", "Sutdent", "Cours", "Inscription Date", "Score", "Action"
            }
        ));
        StudentcoursList.setRowHeight(40);
        StudentcoursList.getColumn("Action").setCellRenderer(new ActionRenderer());
        StudentcoursList.getColumn("Action").setCellEditor(new ActionEditor(this, StudentcoursList));
        jScrollPane3.setViewportView(StudentcoursList);
        // Hide the first column (student_id)
        StudentcoursList.getColumnModel().getColumn(0).setMinWidth(0);
        StudentcoursList.getColumnModel().getColumn(0).setMaxWidth(0);
        StudentcoursList.getColumnModel().getColumn(0).setPreferredWidth(0);
        StudentcoursList.getColumnModel().getColumn(0).setResizable(false);

        // Hide the second column (cours_id)
        StudentcoursList.getColumnModel().getColumn(1).setMinWidth(0);
        StudentcoursList.getColumnModel().getColumn(1).setMaxWidth(0);
        StudentcoursList.getColumnModel().getColumn(1).setPreferredWidth(0);
        StudentcoursList.getColumnModel().getColumn(1).setResizable(false);
        if (StudentcoursList.getColumnModel().getColumnCount() > 0) {
            StudentcoursList.getColumnModel().getColumn(0).setResizable(false);
            StudentcoursList.getColumnModel().getColumn(1).setResizable(false);
            StudentcoursList.getColumnModel().getColumn(4).setResizable(false);
            StudentcoursList.getColumnModel().getColumn(5).setResizable(false);
        }

        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(7, 59, 122));
        jTextField1.setText("Assigned Students");
        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        jLabel15.setFont(new java.awt.Font("Arial", 1, 27)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(10, 42, 67));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/Progress.png"))); // NOI18N
        jLabel15.setText("  Progress");

        javax.swing.GroupLayout progressLayout = new javax.swing.GroupLayout(progress);
        progress.setLayout(progressLayout);
        progressLayout.setHorizontalGroup(
            progressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(progressLayout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .addGroup(progressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, progressLayout.createSequentialGroup()
                        .addGroup(progressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, progressLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(346, 346, 346))))
        );
        progressLayout.setVerticalGroup(
            progressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(progressLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        rightSide.add(progress, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(rightSide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rightSide, javax.swing.GroupLayout.DEFAULT_SIZE, 885, Short.MAX_VALUE)
                    .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 885, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboradActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboradActionPerformed
        cours.setVisible(false);
        Learners.setVisible(false);
        Dashborad.setVisible(true);
        progress.setVisible(false);
        loadAverageScoreChart();

    }//GEN-LAST:event_btnDashboradActionPerformed

    private void btnCoursesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCoursesActionPerformed
        cours.setVisible(true);
        Dashborad.setVisible(false);
        Learners.setVisible(false);
        progress.setVisible(false);

        
    }//GEN-LAST:event_btnCoursesActionPerformed

    private void btnLearnersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLearnersActionPerformed
        Dashborad.setVisible(false);
        cours.setVisible(false);
        Learners.setVisible(true);
        progress.setVisible(false);
    }//GEN-LAST:event_btnLearnersActionPerformed

    private void btnProgressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProgressActionPerformed
        Dashborad.setVisible(false);
        cours.setVisible(false);
        Learners.setVisible(false);
        progress.setVisible(true);
    }//GEN-LAST:event_btnProgressActionPerformed

    private void nameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTxtActionPerformed

    private void emailTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTxtActionPerformed

    private void addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtnActionPerformed
        java.util.Date utilDate = dateField1.getDate();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        ss.create(new Student(nameTxt.getText(),emailTxt.getText(),sqlDate));
        nameTxt.setText("");
        emailTxt.setText("");
        dateField1.setDate(null);
        loadStd();
        loadStdDrop();
        loadDashStats();

    }//GEN-LAST:event_addbtnActionPerformed

    private void categorieTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categorieTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categorieTxtActionPerformed

    private void titleTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_titleTxtActionPerformed

    private void addbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtn1ActionPerformed
        int nbh = (int) nbhSpin.getValue();
        cs.create(new Cours(categorieTxt.getText(),titleTxt.getText(),nbh));
        categorieTxt.setText("");
        titleTxt.setText("");
        nbhSpin.setValue(0);
        loadCrs();
        loadCrsDrop();
        loadDashStats();

    }//GEN-LAST:event_addbtn1ActionPerformed

    private void addbtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtn2ActionPerformed
        Student s = (Student) stdDropDown.getSelectedItem();
        Cours c = (Cours) crsDropDown.getSelectedItem();
        java.sql.Date sqlDate = new java.sql.Date(dateSC.getDate().getTime());
        int score = (int) scoreSpinner.getValue();

        StudentCours sc = new StudentCours(s, c, sqlDate, score);
        if(scs.findBy2Id(sc.getStudent().getId(),sc.getCours().getId())!=null){
            JOptionPane.showMessageDialog(this, "student already assigned to the cours");
        }else{
            scs.create(sc);
            loadProg();
        }
        loadDashStats();

    }//GEN-LAST:event_addbtn2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CoursesList;
    private javax.swing.JPanel Dashborad;
    private javax.swing.JPanel Learners;
    private javax.swing.JTable StudentcoursList;
    private javax.swing.JTable StudentsList;
    private javax.swing.JButton addbtn;
    private javax.swing.JButton addbtn1;
    private javax.swing.JButton addbtn2;
    private javax.swing.JButton btnCourses;
    private javax.swing.JButton btnDashborad;
    private javax.swing.JButton btnLearners;
    private javax.swing.JButton btnProgress;
    private javax.swing.JTextField categorieTxt;
    private javax.swing.JPanel chartP;
    private javax.swing.JPanel cours;
    private javax.swing.JComboBox crsDropDown;
    private com.toedter.calendar.JDateChooser dateField1;
    private com.toedter.calendar.JDateChooser dateSC;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JTextField filterTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblAssigned;
    private javax.swing.JLabel lblAssignedTitle;
    private javax.swing.JLabel lblCourses;
    private javax.swing.JLabel lblCoursesTitle;
    private javax.swing.JLabel lblStudents;
    private javax.swing.JLabel lblStudentsTitle;
    private javax.swing.JPanel menu;
    private javax.swing.JTextField nameTxt;
    private javax.swing.JSpinner nbhSpin;
    private javax.swing.JPanel panelAssigned;
    private javax.swing.JPanel panelCourses;
    private javax.swing.JPanel panelStudents;
    private javax.swing.JPanel progress;
    private javax.swing.JPanel rightSide;
    private javax.swing.JSpinner scoreSpinner;
    private javax.swing.JPanel statsPanel;
    private javax.swing.JComboBox stdDropDown;
    private javax.swing.JTextField titleTxt;
    // End of variables declaration//GEN-END:variables
}
