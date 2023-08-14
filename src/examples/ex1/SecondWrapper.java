package examples.ex1;

public class SecondWrapper {
    private final IService service;

    public SecondWrapper(IService service) {
        this.service = service;
    }

    public void sayService() {
        System.out.println(service);
    }
}
