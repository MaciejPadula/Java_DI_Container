package dependencyinjection.services.fixtures;

public class MainTestClass {
    public final WrapperForTests wrapper;
    public final WrapperForTests2 wrapper2;

    public MainTestClass(WrapperForTests wrapper, WrapperForTests2 wrapper2) {
        this.wrapper = wrapper;
        this.wrapper2 = wrapper2;
    }
}
