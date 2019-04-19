package CreationalPatterns.FactoryPattern;

interface Shape {
    void draw();
}

class Circle  implements  Shape {

    @Override
    public void draw() {
        System.out.println("Inside CreationalPatterns.FactoryPattern.Circle draw() method");
    }
}

class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside CreationalPatterns.FactoryPattern.Rectangle draw() method");
    }
}

class Square implements  Shape {

    @Override
    public void draw() {
        System.out.println("Inside CreationalPatterns.FactoryPattern.Square draw() method");
    }
}

class ShapeFactory {
    public static Shape getShape(String shapeType) {
        if(shapeType == null) {
            return null;
        }
        if(shapeType.equalsIgnoreCase("circle")) {
            return new Circle();
        } else if(shapeType.equalsIgnoreCase("rectangle")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("square")) {
            return new Square();
        }
        return  null;
    }
}

public class FactoryPattern {

    public static void main(String[] args) {
        String shape = "CreationalPatterns.FactoryPattern.Square";
        Shape obj = ShapeFactory.getShape(shape);
        obj.draw();
    }
}
