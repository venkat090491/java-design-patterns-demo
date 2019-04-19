package CreationalPatterns.PrototypePattern;

import java.util.HashMap;
import java.util.Map;

abstract class Shape implements Cloneable{
    private String id;
    protected String type;

    abstract void draw();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace();
        }
        return clone;
    }
}

class Rectangle extends Shape {

    public Rectangle() {
        type = "Rectangle";
    }

    @Override
    void draw() {
        System.out.println("Inside Rectangle draw() method");
    }
}

class Square extends Shape {

    public Square() {
        type = "Square";
    }

    @Override
    void draw() {
        System.out.println("Inside Square draw() method");
    }
}

class Circle extends Shape {

    public Circle() {
        type = "Circle";
    }

    @Override
    void draw() {
        System.out.println("Inside Circle draw() method");
    }
}

class ShapeCache {
    private static Map<String, Shape> cache =  new HashMap<>();

    public static Shape getShape(String shapeId) throws CloneNotSupportedException {
        Shape shape = cache.get(shapeId);
        return (Shape) shape.clone();
    }

    public static void  loadCache() {
        Circle circle = new Circle();
        circle.setId("1");
        cache.put(circle.getId(), circle);

        Square square = new Square();
        square.setId("2");
        cache.put(square.getId(), square);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        cache.put(rectangle.getId(), rectangle);
    }
}

public class PrototypePattern {

    public static void main(String[] args) throws CloneNotSupportedException {
        ShapeCache.loadCache();

        Shape clonedShape = ShapeCache.getShape("4");
        System.out.println("Shape:" + clonedShape.getType());
    }
}
