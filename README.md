
# Simple Drawing Application Documentation

## Project Overview
A Java Swing application that demonstrates OOP principles and event handling through an interactive drawing canvas.

## ✅ Completed Objectives

### 1. Design Requirements ✅
- **Canvas Panel**: Customized JPanel where shapes are drawn
- **Menu Bar with Buttons**: 
  - Draw Circle button
  - Draw Oval button  
  - Draw Square button
  - Clear All button
  - Undo button
- **Shape Spawning**: Shapes spawn in the top left corner (50, 50) when buttons are clicked

### 2. OOP Concepts ✅
- **Abstract Class Shape**:
  - Fields: position (x, y), color, size
  - Methods: draw(Graphics g), contains(Point p)
  - Encapsulation with getters and setters
- **Subclasses**:
  - Circle class (extends Shape)
  - Oval class (extends Shape)
  - Square class (extends Shape)
- **Inheritance**: All shape classes inherit from abstract Shape class
- **Polymorphism**: Each shape implements draw() and contains() differently
- **Encapsulation**: Private fields with public accessor methods

### 3. Mouse Event Handling ✅
- **mouseEntered**: 
  - Changes cursor to crosshair when mouse enters canvas
  - Changes background color to light gray (240, 240, 240)
- **mouseExited**: 
  - Returns cursor to default when mouse leaves canvas
  - Returns background to white
  - Clears shape selection
- **mousePressed**: 
  - Detects shape clicks and selects them
  - Makes selected shapes darker (shade darker to indicate selection)
  - Shows warning dialog when clicking empty space
  - Requests focus for keyboard events
- **mouseClicked**: 
  - Currently does nothing (as requested)
- **mouseDragged**: 
  - Allows dragging shapes around the canvas
  - Shapes follow mouse movement smoothly

### 4. Keyboard Event Handling ✅
- **X Key**: Changes shape color when a shape is selected
- **Z Key**: Changes shape size when a shape is selected
- **I Key**: Shows shape information dialog when a shape is selected
- **Focus Management**: Canvas requests focus when clicked

### 5. Dialog Boxes ✅
- **JOptionPane.showMessageDialog** used for:
  - Shape addition confirmation (when buttons are clicked)
  - Shape information display (position, color, size) on 'I' key
  - Warning when clicking empty space
  - Color change confirmation
  - Size change confirmation
  - "No shape selected" warning for keyboard events

### 6. Color and Size System ✅
- **Colors Available**:
  - Red (Color.RED)
  - Blue (Color.BLUE) 
  - Brown (new Color(139, 69, 19))
  - Beige (new Color(245, 245, 220))
  - Black (Color.BLACK)
- **Sizes Available**:
  - Small (30x30 pixels)
  - Medium (50x50 pixels)
  - Large (80x80 pixels)

### 7. Additional Features ✅
- **Shape Selection**: Visual feedback with darker color
- **Shape Movement**: Drag and drop functionality
- **Undo Functionality**: Removes last added shape
- **Clear All**: Removes all shapes with confirmation dialog
- **Visual Feedback**: Selected shapes appear darker
- **Console Debugging**: Key press events logged to console

## Technical Implementation Details

### Class Structure
```
MainFrame_O_O (extends JFrame)
├── CanvasPanel (extends JPanel)
│   ├── Mouse event handling
│   ├── Keyboard event handling
│   └── Painting logic
├── Shape (abstract class)
│   ├── Circle (extends Shape)
│   ├── Oval (extends Shape)
│   └── Square (extends Shape)
└── Event handlers for buttons
```

### Key Methods
- `setupFrame()`: Initializes the main window
- `setupComponents()`: Creates UI components
- `setupLayout()`: Arranges components using BorderLayout
- `setupEventHandlers()`: Connects buttons to actions
- `paintComponent()`: Renders all shapes
- `draw()`: Abstract method implemented by each shape
- `contains()`: Abstract method for hit detection

### Event Flow
1. **Button Click** → Creates shape in top-left corner
2. **Mouse Enter** → Changes cursor and background
3. **Mouse Press** → Selects shape or shows warning
4. **Mouse Drag** → Moves selected shape
5. **Key Press** → Modifies selected shape (X/Z/I keys)

## How to Use the Application

### Adding Shapes
1. Click "Draw Circle", "Draw Oval", or "Draw Square" buttons
2. Shape appears in top-left corner (50, 50)

### Selecting Shapes
1. Click on any shape to select it
2. Selected shape becomes darker
3. Click on empty space to deselect

### Modifying Shapes
1. Select a shape first
2. Press 'X' to change color
3. Press 'Z' to change size
4. Press 'I' to see shape information

### Moving Shapes
1. Click and drag any shape
2. Shape follows mouse movement

### Managing Shapes
1. Click "Undo" to remove last shape
2. Click "Clear All" to remove all shapes

## Assignment Requirements Verification

| Requirement | Status | Implementation |
|-------------|--------|----------------|
| OOP Principles | ✅ Complete | Abstract Shape class, inheritance, polymorphism, encapsulation |
| Java Swing | ✅ Complete | JFrame, JPanel, JButton, event handling |
| Mouse Events | ✅ Complete | All required mouse events implemented |
| Dialog Boxes | ✅ Complete | JOptionPane used throughout |
| Shape Drawing | ✅ Complete | Circle, Oval, Square with different colors/sizes |
| User Interaction | ✅ Complete | Buttons, keyboard shortcuts, drag & drop |

## Code Quality Features
- Clean, readable code structure
- Proper encapsulation and abstraction
- Comprehensive error handling
- User-friendly interface
- Consistent naming conventions
- Appropriate comments and documentation

## Testing Completed
- Compilation successful
- All buttons functional
- Mouse events working
- Keyboard shortcuts working
- Shape creation and modification working
- Dialog boxes displaying correctly
- Drag and drop functionality working

---
**Total Objectives Completed: 100%** ✅
**All Assignment Requirements Met** ✅ 
