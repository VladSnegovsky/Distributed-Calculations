package com.univ.labs;

import javax.swing.*;
import java.awt.*;

class MainFrame extends JFrame {
    private JPanel fieldPanel;
    private JButton	startBtn;
    private JButton	stopBtn;
    private int scrollPane = 1;

    MainFrame() {
        super("LIFE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        add(toolBar, BorderLayout.SOUTH);

        fieldPanel = new FieldPanel(50, 50, 11);
        add(fieldPanel);
        startBtn = new JButton("Start");
        toolBar.add(startBtn);
        stopBtn = new JButton("Stop");
        stopBtn.setEnabled(true);
        toolBar.add(stopBtn);


        final DefaultComboBoxModel<String> civilAmountModel = new DefaultComboBoxModel<>();

        civilAmountModel.addElement("1");
        civilAmountModel.addElement("2");
        civilAmountModel.addElement("3");
        //civilAmountModel.addElement("Peer");

        final JComboBox<String> civilCombo = new JComboBox<>(civilAmountModel);
        civilCombo.setSelectedIndex(0);

        JScrollPane civilScrollPane = new JScrollPane(civilCombo);

        toolBar.add(civilScrollPane);

        startBtn.addActionListener(e -> {
            if (civilCombo.getSelectedIndex() != -1) {
                scrollPane = Integer.parseInt(civilCombo.getItemAt(civilCombo.getSelectedIndex()));
                System.out.println(scrollPane);
            }
            ((FieldPanel)fieldPanel).startSimulation(scrollPane, 0.2f);
            startBtn.setEnabled(false);
            stopBtn.setEnabled(true);
        });

        stopBtn.addActionListener(e -> {
            ((FieldPanel)fieldPanel).stopSimulation(startBtn);
            stopBtn.setEnabled(false);
        });
        pack();
        setVisible(true);
    }
}
