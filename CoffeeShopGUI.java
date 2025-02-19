
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Base Beverage Class
abstract class Beverage {
    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double getCost();
}

// Concrete Beverages
class Espresso extends Beverage {
    public Espresso() {
        description = "Espresso";
    }

    public double getCost() {
        return 1.99;
    }
}

class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "House Blend Coffee";
    }

    public double getCost() {
        return 0.89;
    }
}

// Decorator Class
abstract class CondimentDecorator extends Beverage {
    public abstract String getDescription();
}

// Concrete Decorators
class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    public double getCost() {
        return 0.20 + beverage.getCost();
    }
}

class Whip extends CondimentDecorator {
    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }

    public double getCost() {
        return 0.10 + beverage.getCost();
    }
}

// GUI Class
public class CoffeeShopGUI extends JFrame {
    private JComboBox<String> coffeeComboBox;
    private JCheckBox mochaCheckBox, whipCheckBox;
    private JLabel resultLabel;

    public CoffeeShopGUI() {
        setTitle("Coffee Shop");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        coffeeComboBox = new JComboBox<>(new String[]{"Espresso", "House Blend"});
        mochaCheckBox = new JCheckBox("Add Mocha");
        whipCheckBox = new JCheckBox("Add Whip");

        JButton orderButton = new JButton("Order");
        resultLabel = new JLabel("Select coffee and condiments.");

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Beverage beverage = coffeeComboBox.getSelectedIndex() == 0 ? new Espresso() : new HouseBlend();
                if (mochaCheckBox.isSelected()) beverage = new Mocha(beverage);
                if (whipCheckBox.isSelected()) beverage = new Whip(beverage);
                resultLabel.setText(beverage.getDescription() + " - $" + beverage.getCost());
            }
        });

        add(new JLabel("Select Coffee Type:"));
        add(coffeeComboBox);
        add(mochaCheckBox);
        add(whipCheckBox);
        add(orderButton);
        add(resultLabel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CoffeeShopGUI().setVisible(true));
    }
}
