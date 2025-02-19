
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Interface for fly behavior
interface FlyBehavior {
    String fly();
}

class FlyWithWings implements FlyBehavior {
    public String fly() {
        return "I'm flying!";
    }
}

class FlyNoWay implements FlyBehavior {
    public String fly() {
        return "I can't fly!";
    }
}

// Interface for quack behavior
interface QuackBehavior {
    String quack();
}

class Quack implements QuackBehavior {
    public String quack() {
        return "Quack!";
    }
}

class MuteQuack implements QuackBehavior {
    public String quack() {
        return "<< Silence >>";
    }
}

// Duck Class
class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        this.flyBehavior = flyBehavior;
        this.quackBehavior = quackBehavior;
    }

    public String performFly() {
        return flyBehavior.fly();
    }

    public String performQuack() {
        return quackBehavior.quack();
    }
}

// GUI Class
public class DuckSimulatorGUI extends JFrame {
    private JComboBox<String> flyComboBox;
    private JComboBox<String> quackComboBox;
    private JLabel resultLabel;

    public DuckSimulatorGUI() {
        setTitle("Duck Simulator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        flyComboBox = new JComboBox<>(new String[]{"Can Fly", "Cannot Fly"});
        quackComboBox = new JComboBox<>(new String[]{"Quack", "Silent"});

        JButton simulateButton = new JButton("Simulate");
        resultLabel = new JLabel("Select behaviors and click simulate.");

        simulateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlyBehavior flyBehavior = flyComboBox.getSelectedIndex() == 0 ? new FlyWithWings() : new FlyNoWay();
                QuackBehavior quackBehavior = quackComboBox.getSelectedIndex() == 0 ? new Quack() : new MuteQuack();
                Duck duck = new Duck(flyBehavior, quackBehavior);
                resultLabel.setText("Fly: " + duck.performFly() + " | Quack: " + duck.performQuack());
            }
        });

        add(new JLabel("Select Fly Behavior:"));
        add(flyComboBox);
        add(new JLabel("Select Quack Behavior:"));
        add(quackComboBox);
        add(simulateButton);
        add(resultLabel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DuckSimulatorGUI().setVisible(true));
    }
}
