package CreationalPatterns.AbstractFactoryPattern;

interface Shape {
    void draw();
}

class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square draw() method");
    }
}

class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Rectangle draw() method");
    }
}

class RoundedRectangle implements  Shape {

    @Override
    public void draw() {
        System.out.println("Inside RoundedRectangle draw() method");
    }
}

class RoundedSquare implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside RoundedSquare draw() method");
    }
}

abstract class AbstractFactory {
    abstract Shape getShape(String shapeType);
}

class ShapeFactory extends AbstractFactory {

    @Override
    Shape getShape(String shapeType) {
        if(shapeType.equalsIgnoreCase("Rectangle")){
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("Square")) {
            return new Square();
        }
        return null;
    }
}

class RoundedShapeFactory extends AbstractFactory {

    @Override
    Shape getShape(String shapeType) {
        if(shapeType.equalsIgnoreCase("Rectangle")) {
            return new RoundedRectangle();
        } else if(shapeType.equalsIgnoreCase("square")) {
            return new RoundedSquare();
        }
        return null;
    }
}

class FactoryProducer {
    public static AbstractFactory getFactory(boolean rounded) {
        if(rounded) {
            return new RoundedShapeFactory();
        } else {
            return new ShapeFactory();
        }
    }
}

public class AbstractFactoryPattern {

    public static void main(String[] args) {
        AbstractFactory factory = FactoryProducer.getFactory(false);
        Shape shape = factory.getShape("rectangle");
        shape.draw();

        AbstractFactory factory1 = FactoryProducer.getFactory(true);
        Shape shape1 = factory1.getShape("square");
        shape1.draw();
    }
}
