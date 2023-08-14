package examples.ex1;

public class Printer {
    private final IService service;
    private final String name;

    public Printer(IService service, String name) {
        this.service = service;
        this.name = name;
    }

    public void print() {
        System.out.println(service);
        System.out.println(name);
    }
}
