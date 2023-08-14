package examples.ex1;

public class Flow {
    private final Wrapper wrapper;
    private final SecondWrapper secondWrapper;

    public Flow(Wrapper wrapper, SecondWrapper secondWrapper) {
        this.wrapper = wrapper;
        this.secondWrapper = secondWrapper;
    }

    public void print() {
        wrapper.sayService();
        secondWrapper.sayService();
    }
}
