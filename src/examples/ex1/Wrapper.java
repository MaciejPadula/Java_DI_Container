package examples.ex1;

public class Wrapper {
    private final IService service;

    public Wrapper(IService service) {
        this.service = service;
    }

    public void sayService() {
        System.out.println(service);
    }
}
