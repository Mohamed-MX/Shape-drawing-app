/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package assignment_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author heiar
 */
public class MainFrame_O_O extends JFrame {

    // Canvas panel for drawing shapes
    private CanvasPanel canvasPanel;
    
    // Buttons for different actions
    private JButton drawCircleBtn, drawOvalBtn, drawSquareBtn, clearBtn, undoBtn;
    
    // Current drawing mode
    private String currentShape = "circle";
    
    // Lists to store shapes and history for undo
    private List<Shape> shapes = new ArrayList<>();
    private List<Shape> undoHistory = new ArrayList<>();
    
    // Colors and sizes
    private Color[] colors = {Color.RED, Color.BLUE, new Color(139, 69, 19), new Color(245, 245, 220), Color.BLACK};
    private String[] sizes = {"small", "medium", "large"};
    private int colorIndex = 0;
    private int sizeIndex = 0;

    /**
     * Creates new form MainFrame_O_O
     */
    public MainFrame_O_O() {
        setupFrame();
        setupComponents();
        setupLayout();
        setupEventHandlers();
    }

    private void setupFrame() {
        setTitle("Simple Drawing Application - Assignment 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }
    
    private void setupComponents() {
        // Create buttons
        drawCircleBtn = new JButton("Draw Circle");
        drawOvalBtn = new JButton("Draw Oval");
        drawSquareBtn = new JButton("Draw Square");
        clearBtn = new JButton("Clear All");
        undoBtn = new JButton("Undo");
        
        // Create canvas panel
        canvasPanel = new CanvasPanel();
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(drawCircleBtn);
        buttonPanel.add(drawOvalBtn);
        buttonPanel.add(drawSquareBtn);
        buttonPanel.add(clearBtn);
        buttonPanel.add(undoBtn);
        
        // Add components to frame
        add(buttonPanel, BorderLayout.NORTH);
        add(canvasPanel, BorderLayout.CENTER);
    }
    
    private void setupEventHandlers() {
        // Button event handlers
        drawCircleBtn.addActionListener(e -> {
            currentShape = "circle";
            Shape newShape = new Circle(50, 50, colors[colorIndex], sizes[sizeIndex]);
            shapes.add(newShape);
            canvasPanel.repaint();
            JOptionPane.showMessageDialog(this, "Circle added in top left corner!");
        });
        
        drawOvalBtn.addActionListener(e -> {
            currentShape = "oval";
            Shape newShape = new Oval(50, 50, colors[colorIndex], sizes[sizeIndex]);
            shapes.add(newShape);
            canvasPanel.repaint();
            JOptionPane.showMessageDialog(this, "Oval added in top left corner!");
        });
        
        drawSquareBtn.addActionListener(e -> {
            currentShape = "square";
            Shape newShape = new Square(50, 50, colors[colorIndex], sizes[sizeIndex]);
            shapes.add(newShape);
            canvasPanel.repaint();
            JOptionPane.showMessageDialog(this, "Square added in top left corner!");
        });
        
        clearBtn.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to clear all shapes?", 
                "Confirm Clear", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                shapes.clear();
                undoHistory.clear();
                canvasPanel.repaint();
                JOptionPane.showMessageDialog(this, "All shapes cleared!");
            }
        });
        
        undoBtn.addActionListener(e -> {
            if (!shapes.isEmpty()) {
                Shape lastShape = shapes.remove(shapes.size() - 1);
                undoHistory.add(lastShape);
                canvasPanel.repaint();
                JOptionPane.showMessageDialog(this, "Last shape undone!");
            } else {
                JOptionPane.showMessageDialog(this, "No shapes to undo!");
            }
        });
    }
    
    // Abstract Shape class demonstrating inheritance and encapsulation
    abstract class Shape {
        protected int x, y;
        protected Color color;
        protected String size;
        protected boolean isSelected = false;
        
        public Shape(int x, int y, Color color, String size) {
            this.x = x;
            this.y = y;
            this.color = color;
            this.size = size;
        }
        
        // Abstract method - polymorphism
        public abstract void draw(Graphics g);
        public abstract boolean contains(Point p);
        
        // Getters and setters for encapsulation
        public int getX() { return x; }
        public int getY() { return y; }
        public Color getColor() { return color; }
        public String getSize() { return size; }
        public boolean isSelected() { return isSelected; }
        
        public void setX(int x) { this.x = x; }
        public void setY(int y) { this.y = y; }
        public void setColor(Color color) { this.color = color; }
        public void setSize(String size) { this.size = size; }
        public void setSelected(boolean selected) { this.isSelected = selected; }
        
        public int getWidth() {
            switch (size) {
                case "small": return 30;
                case "medium": return 50;
                case "large": return 80;
                default: return 50;
            }
        }
        
        public int getHeight() {
            switch (size) {
                case "small": return 30;
                case "medium": return 50;
                case "large": return 80;
                default: return 50;
            }
        }
    }
    
    // Circle class - inheritance from Shape
    class Circle extends Shape {
        public Circle(int x, int y, Color color, String size) {
            super(x, y, color, size);
        }
        
        @Override
        public void draw(Graphics g) {
            Color drawColor = isSelected ? color.darker() : color;
            g.setColor(drawColor);
            g.fillOval(x, y, getWidth(), getHeight());
            g.setColor(Color.BLACK);
            g.drawOval(x, y, getWidth(), getHeight());
        }
        
        @Override
        public boolean contains(Point p) {
            int centerX = x + getWidth() / 2;
            int centerY = y + getHeight() / 2;
            int radius = getWidth() / 2;
            return Math.sqrt(Math.pow(p.x - centerX, 2) + Math.pow(p.y - centerY, 2)) <= radius;
        }
    }
    
    // Oval class - inheritance from Shape
    class Oval extends Shape {
        public Oval(int x, int y, Color color, String size) {
            super(x, y, color, size);
        }
        
        @Override
        public void draw(Graphics g) {
            Color drawColor = isSelected ? color.darker() : color;
            g.setColor(drawColor);
            g.fillOval(x, y, getWidth(), getHeight() + 20);
            g.setColor(Color.BLACK);
            g.drawOval(x, y, getWidth(), getHeight() + 20);
        }
        
        @Override
        public boolean contains(Point p) {
            return p.x >= x && p.x <= x + getWidth() && 
                   p.y >= y && p.y <= y + getHeight() + 20;
        }
    }
    
    // Square class - inheritance from Shape
    class Square extends Shape {
        public Square(int x, int y, Color color, String size) {
            super(x, y, color, size);
        }
        
        @Override
        public void draw(Graphics g) {
            Color drawColor = isSelected ? color.darker() : color;
            g.setColor(drawColor);
            g.fillRect(x, y, getWidth(), getHeight());
            g.setColor(Color.BLACK);
            g.drawRect(x, y, getWidth(), getHeight());
        }
        
        @Override
        public boolean contains(Point p) {
            return p.x >= x && p.x <= x + getWidth() && 
                   p.y >= y && p.y <= y + getHeight();
        }
    }
    
    // Custom canvas panel with mouse event handling
    class CanvasPanel extends JPanel {
        private Shape selectedShape = null;
        
        public CanvasPanel() {
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            // Mouse event handlers
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                    setBackground(new Color(240, 240, 240));
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    setCursor(Cursor.getDefaultCursor());
                    setBackground(Color.WHITE);
                    // Clear selection when mouse leaves
                    if (selectedShape != null) {
                        selectedShape.setSelected(false);
                        selectedShape = null;
                        repaint();
                    }
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    // Request focus so keyboard events work
                    requestFocusInWindow();
                    
                    Point p = e.getPoint();
                    selectedShape = null;
                    
                    // Check if any shape was clicked
                    for (Shape shape : shapes) {
                        if (shape.contains(p)) {
                            selectedShape = shape;
                            shape.setSelected(true);
                            repaint();
                            break;
                        }
                    }
                    
                    if (selectedShape == null) {
                        // Clear any previous selection
                        for (Shape shape : shapes) {
                            shape.setSelected(false);
                        }
                        repaint();
                        JOptionPane.showMessageDialog(CanvasPanel.this, "No shape found at this location!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Mouse click now only for selecting shapes, not creating them
                }
            });
            
            // Mouse motion listener for dragging
            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (selectedShape != null) {
                        selectedShape.setX(e.getX() - selectedShape.getWidth() / 2);
                        selectedShape.setY(e.getY() - selectedShape.getHeight() / 2);
                        repaint();
                    }
                }
            });
            
            // Key listener for changing color and size
            setFocusable(true);
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    System.out.println("Key pressed: " + e.getKeyChar() + ", Selected shape: " + (selectedShape != null));
                    
                    if (selectedShape != null) {
                        if (e.getKeyChar() == 'x' || e.getKeyChar() == 'X') {
                            colorIndex = (colorIndex + 1) % colors.length;
                            selectedShape.setColor(colors[colorIndex]);
                            repaint();
                            JOptionPane.showMessageDialog(CanvasPanel.this, 
                                "Shape color changed to " + getColorName(colors[colorIndex]));
                        } else if (e.getKeyChar() == 'z' || e.getKeyChar() == 'Z') {
                            sizeIndex = (sizeIndex + 1) % sizes.length;
                            selectedShape.setSize(sizes[sizeIndex]);
                            repaint();
                            JOptionPane.showMessageDialog(CanvasPanel.this, 
                                "Shape size changed to " + sizes[sizeIndex]);
                        } else if (e.getKeyChar() == 'i' || e.getKeyChar() == 'I') {
                            // Show shape info dialog
                            String info = "Shape Info:\n" +
                                         "Type: " + selectedShape.getClass().getSimpleName() + "\n" +
                                         "Position: (" + selectedShape.getX() + ", " + selectedShape.getY() + ")\n" +
                                         "Color: " + getColorName(selectedShape.getColor()) + "\n" +
                                         "Size: " + selectedShape.getSize();
                            JOptionPane.showMessageDialog(CanvasPanel.this, info, "Shape Information", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(CanvasPanel.this, "No shape selected! Click on a shape first.", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            // Draw all shapes
            for (Shape shape : shapes) {
                shape.draw(g);
            }
        }
        
        private Shape createShape(int x, int y) {
            switch (currentShape) {
                case "circle":
                    return new Circle(x, y, colors[colorIndex], sizes[sizeIndex]);
                case "oval":
                    return new Oval(x, y, colors[colorIndex], sizes[sizeIndex]);
                case "square":
                    return new Square(x, y, colors[colorIndex], sizes[sizeIndex]);
                default:
                    return new Circle(x, y, colors[colorIndex], sizes[sizeIndex]);
            }
        }
        
        private String getColorName(Color color) {
            if (color.equals(Color.RED)) return "Red";
            if (color.equals(Color.BLUE)) return "Blue";
            if (color.equals(new Color(139, 69, 19))) return "Brown";
            if (color.equals(new Color(245, 245, 220))) return "Beige";
            if (color.equals(Color.BLACK)) return "Black";
            return "Unknown";
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame_O_O().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
