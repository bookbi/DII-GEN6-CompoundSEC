
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeDrawer4 extends JFrame {
    private JPanel controlPanel;
    private DrawingPanel drawingPanel;
    private JComboBox<String> shapeSelector;
    private JButton drawButton;
    private Color selectedColor = Color.BLACK;

    public ShapeDrawer4() {
        setTitle("Shape Drawer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ส่วนควบคุม (Control Panel)
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        // ตัวเลือกประเภทของรูปทรง
        shapeSelector = new JComboBox<>(new String[]{"Rectangle", "Circle", "Triangle"});
        drawButton = new JButton("Draw Shape");
        JButton colorButton = new JButton("Choose Color");

        controlPanel.add(new JLabel("Select Shape:"));
        controlPanel.add(shapeSelector);
        controlPanel.add(colorButton);
        controlPanel.add(drawButton);

        // พื้นที่สำหรับวาดรูปทรง
        drawingPanel = new DrawingPanel();

        // ใส่ Panels เข้าสู่ Frame
        add(controlPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);

        // กดปุ่มเพื่อเลือกสี
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedColor = JColorChooser.showDialog(null, "Choose a Color", selectedColor);
                if (selectedColor == null) {
                    selectedColor = Color.BLACK;
                }
            }
        });

        // กดปุ่มเพื่อวาดรูปทรง
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedShape = (String) shapeSelector.getSelectedItem();
                drawingPanel.setShapeToDraw(selectedShape, selectedColor);
            }
        });
    }

    // ส่วนสำหรับวาดรูป
    private class DrawingPanel extends JPanel {
        private String shapeToDraw = "Rectangle";
        private Color shapeColor = Color.BLACK;

        public void setShapeToDraw(String shape, Color color) {
            this.shapeToDraw = shape;
            this.shapeColor = color;
            repaint(); // วาดใหม่
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // ตั้งค่าสีของ Graphics
            g.setColor(shapeColor);

            // วาดรูปทรงตามที่เลือก
            switch (shapeToDraw) {
                case "Rectangle" -> g.fillRect(100, 100, 200, 100); // สี่เหลี่ยม
                case "Circle" -> g.fillOval(150, 100, 100, 100); // วงกลม
                case "Triangle" -> {
                    int[] xPoints = {200, 150, 250};
                    int[] yPoints = {100, 200, 200};
                    g.fillPolygon(xPoints, yPoints, 3); // สามเหลี่ยม
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ShapeDrawer4 shapeDrawer = new ShapeDrawer4();
            shapeDrawer.setVisible(true);
        });
    }
}
