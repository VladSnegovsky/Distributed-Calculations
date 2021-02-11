import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class create_window extends JFrame {
    static JFrame frame;
    static JSlider slider;

    static JLabel labelThread1;
    static JLabel labelThread2;

    static GridLayout mainLayout;
    static GridLayout bottomLayout;
    static GridLayout topLayout;
    static JPanel leftPanel;
    static JPanel rightPanel;
    static JPanel topPanel;
    static JPanel bottomPanel;
    static JPanel mainPanel;

    static JButton startThread1;
    static JButton stopThread1;
    static JButton startThread2;
    static JButton stopThread2;

    static Threads thread1;
    static Threads thread2;

    static ActionListener actionListener;

    static volatile int is_busy_semaphore = 0;

    static String red_alert(){
        return (char) 27 + "[31m" + "Another Thread is working!!!" + (char) 27 + "[0m\n";
    }

    public static void main(String[] args) {
        // ================== Slider
        slider = new JSlider();
        slider.setMinimum(0);
        slider.setMaximum(100);
        slider.setValue(50);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        // =========================

        // ================== Labels
        labelThread1 = new JLabel("First Thread", SwingConstants.CENTER);
        labelThread2 = new JLabel("Second Thread", SwingConstants.CENTER);
        // =========================

        // ================= Buttons
        startThread1 = new JButton("Begin");
        stopThread1 = new JButton("Stop");
        startThread2 = new JButton("Begin");
        stopThread2 = new JButton("Stop");

        startThread1.setEnabled(true);
        stopThread1.setEnabled(false);
        startThread2.setEnabled(true);
        stopThread2.setEnabled(false);
        // =========================

        topLayout = new GridLayout(1, 1);
        mainLayout = new GridLayout(3, 1);
        bottomLayout = new GridLayout(1, 2);

        // ================ Elements
        topPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();
        mainPanel = new JPanel();

        topPanel.setLayout(topLayout);
        leftPanel.setLayout(mainLayout);
        rightPanel.setLayout(mainLayout);
        bottomPanel.setLayout(bottomLayout);
        mainPanel.setLayout(mainLayout);
        leftPanel.add(labelThread1);
        leftPanel.add(startThread1);
        leftPanel.add(stopThread1);
        rightPanel.add(labelThread2);
        rightPanel.add(startThread2);
        rightPanel.add(stopThread2);
        topPanel.add(slider);
        bottomPanel.add(leftPanel);
        bottomPanel.add(rightPanel);
        mainPanel.add(topPanel);
        mainPanel.add(bottomPanel);
        // =========================

        listener();

        // =================== Frame
        frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.setSize(700, 300);
        frame.setResizable(false);
        frame.setVisible(true);
        // =========================
    }

    static void listener() {
        actionListener = e -> {
            if (e.getSource() == startThread1) {
                if (is_busy_semaphore > 0){ System.out.print(red_alert()); }
                else {
                    is_busy_semaphore = 1;
                    thread1 = new Threads(10, slider);
                    thread1.min_priority();

                    startThread1.setEnabled(false);
                    stopThread1.setEnabled(true);
                }
            } else if (e.getSource() == stopThread1) {
                is_busy_semaphore = 0;
                thread1.Run();
                startThread1.setEnabled(true);
                stopThread1.setEnabled(false);
            } else if (e.getSource() == startThread2) {
                if (is_busy_semaphore > 0){ System.out.print(red_alert()); }
                else {
                    is_busy_semaphore = 1;
                    thread2 = new Threads(90, slider);
                    thread2.max_priority();
                    startThread2.setEnabled(false);
                    stopThread2.setEnabled(true);
                }
            } else if (e.getSource() == stopThread2) {
                is_busy_semaphore = 0;
                thread2.Run();
                startThread2.setEnabled(true);
                stopThread2.setEnabled(false);
            }
        };
        startThread1.addActionListener(actionListener);
        stopThread1.addActionListener(actionListener);
        startThread2.addActionListener(actionListener);
        stopThread2.addActionListener(actionListener);
    }
}
