import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class Main extends JFrame {
    JFrame frame;
    JSlider slider;

    JLabel labelThread1;
    JLabel labelThread2;

    GridLayout mainLayout;
    GridLayout bottomLayout;
    GridLayout topLayout;
    JPanel leftPanel;
    JPanel rightPanel;
    JPanel topPanel;
    JPanel bottomPanel;
    JPanel mainPanel;

    JButton startThread1;
    JButton stopThread1;
    JButton startThread2;
    JButton stopThread2;

    Threads thread1;
    Threads thread2;

    ActionListener actionListener;

    volatile boolean is_busy = false;

    Logger log = Logger.getLogger(Main.class.getName());

    static String red(String str){
        return (char) 27 + "[31m" + str + (char) 27 + "[0m\n";
    }

    void initListener() {
        actionListener = e -> {
            if (e.getSource() == startThread1) {
                if (is_busy){
                    System.out.print(red("Another Thread is working!!!"));
                }
                else {
                    is_busy = true;
                    thread1 = new Threads(10, slider);
                    thread1.lower_priority();

                    startThread1.setEnabled(false);
                    stopThread1.setEnabled(true);
                }
            } else if (e.getSource() == stopThread1) {
                is_busy = false;
                thread1.Run();
                startThread1.setEnabled(true);
                stopThread1.setEnabled(false);
            } else if (e.getSource() == startThread2) {
                if (is_busy){
                    System.out.print(red("Another Thread is working!!!"));
                }
                else {
                    is_busy = true;
                    thread2 = new Threads(90, slider);
                    thread2.increase_priority();
                    startThread2.setEnabled(false);
                    stopThread2.setEnabled(true);
                }
            } else if (e.getSource() == stopThread2) {
                is_busy = false;
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

    public Main() {
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

        initListener();

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

    public static void main(String[] args) {
        Main main = new Main();
    }
}
