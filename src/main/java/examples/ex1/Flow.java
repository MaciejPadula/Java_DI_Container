package examples.ex1;

public class Flow {
    private final Wrapper wrapper;
    private final SecondWrapper secondWrapper;
    private final Printer printer;

    public Flow(Wrapper wrapper, SecondWrapper secondWrapper, Printer printer) {
        this.wrapper = wrapper;
        this.secondWrapper = secondWrapper;
        this.printer = printer;
    }

    public void print() {
        wrapper.sayService();
        secondWrapper.sayService();
        printer.print();
    }
}
