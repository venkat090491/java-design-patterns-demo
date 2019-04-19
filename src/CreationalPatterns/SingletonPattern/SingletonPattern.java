package CreationalPatterns.SingletonPattern;

class Singleton {
    private static Singleton obj = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return obj;
    }

    public void showMessage() {
        System.out.println("Hello World!");
    }
}

public class SingletonPattern {

    public static void main(String[] args) {
        Singleton obj = Singleton.getInstance();
        System.out.println(obj.hashCode());

        Singleton obj2 = Singleton.getInstance();
        System.out.println(obj2.hashCode());
    }
}
