import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    JFrame frame;

    JSlider slider;

    JPanel contentTop;
    GridLayout topLayout;

    JLabel labelThread1;
    JLabel labelThread2;

    JPanel contentLeft;
    JPanel contentRight;
    JPanel contentBottom;
    JPanel contentMain;

    GridLayout mainLayout;
    GridLayout bottomLayout;

    JButton buttonStart;
    JButton buttonStop;

    JButton btnPriorityThread1_UP;
    JButton btnPriorityThread1_DOWN;

    JButton btnPriorityThread2_UP;
    JButton btnPriorityThread2_DOWN;

    Threads thread1;
    Threads thread2;

    ActionListener listener;

    void initializePanel() {
        contentTop = new JPanel();
        contentLeft = new JPanel();
        contentRight = new JPanel();
        contentBottom = new JPanel();

        contentMain = new JPanel();

        contentTop.setLayout(topLayout);
        contentLeft.setLayout(mainLayout);
        contentRight.setLayout(mainLayout);
        contentBottom.setLayout(bottomLayout);

        contentMain.setLayout(new GridLayout(2, 1));

        contentLeft.add(labelThread1);
        contentLeft.add(btnPriorityThread1_UP);
        contentLeft.add(btnPriorityThread1_DOWN);
        contentLeft.add(buttonStart);
        contentRight.add(labelThread2);
        contentRight.add(btnPriorityThread2_UP);
        contentRight.add(btnPriorityThread2_DOWN);
        contentRight.add(buttonStop);
        contentTop.add(slider);
        contentBottom.add(contentLeft);
        contentBottom.add(contentRight);
        contentMain.add(contentTop);
        contentMain.add(contentBottom);
    }

    void startThreads() {
        thread1 = new Threads(10, slider);
        thread2 = new Threads(90, slider);
    }

    void initListener() {
        listener = e -> {
            if (e.getSource() == buttonStart) {

                startThreads();
                buttonStart.setEnabled(false);
                buttonStop.setEnabled(true);
                btnPriorityThread1_UP.setEnabled(true);
                btnPriorityThread1_DOWN.setEnabled(true);
                btnPriorityThread2_UP.setEnabled(true);
                btnPriorityThread2_DOWN.setEnabled(true);

            } else if (e.getSource() == buttonStop) {

                thread1.Run();
                thread2.Run();
                buttonStart.setEnabled(true);
                buttonStop.setEnabled(false);
                btnPriorityThread1_UP.setEnabled(false);
                btnPriorityThread1_DOWN.setEnabled(false);
                btnPriorityThread2_UP.setEnabled(false);
                btnPriorityThread2_DOWN.setEnabled(false);

            } else if (e.getSource() == btnPriorityThread1_UP) {
                thread1.increase_priority();
            } else if (e.getSource() == btnPriorityThread1_DOWN) {
                thread1.decrease_priority();
            } else if (e.getSource() == btnPriorityThread2_UP) {
                thread2.increase_priority();
            } else if (e.getSource() == btnPriorityThread2_DOWN) {
                thread2.decrease_priority();
            }
        };

        buttonStart.addActionListener(listener);
        buttonStop.addActionListener(listener);
        btnPriorityThread1_UP.addActionListener(listener);
        btnPriorityThread1_DOWN.addActionListener(listener);
        btnPriorityThread2_UP.addActionListener(listener);
        btnPriorityThread2_DOWN.addActionListener(listener);
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

        // ================= Buttons
        buttonStart = new JButton("Start");
        buttonStop = new JButton("Stop");
        btnPriorityThread1_UP = new JButton("Up");
        btnPriorityThread1_DOWN = new JButton("Down");
        btnPriorityThread2_UP = new JButton("Up");
        btnPriorityThread2_DOWN = new JButton("Down");

        buttonStart.setEnabled(true);
        buttonStop.setEnabled(false);
        btnPriorityThread1_UP.setEnabled(false);
        btnPriorityThread1_DOWN.setEnabled(false);
        btnPriorityThread2_UP.setEnabled(false);
        btnPriorityThread2_DOWN.setEnabled(false);
        // =========================

        // ================== Labels
        labelThread1 = new JLabel("First Thread", SwingConstants.CENTER);
        labelThread2 = new JLabel("Second Thread", SwingConstants.CENTER);
        // =========================

        // ================= Layouts
        topLayout = new GridLayout(1, 1);
        mainLayout = new GridLayout(4, 1);
        bottomLayout = new GridLayout(1, 2);
        // =========================

        initializePanel();

        initListener();

        // =================== Frame
        frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(contentMain);
        frame.setSize(1000, 500);
        frame.setResizable(false);
        frame.setVisible(true);
        // =========================
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}
