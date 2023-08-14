package examples.ex2;

public class Flow {
    private final UserAdder userAdder;
    private final UserGetter userGetter;

    public Flow(UserAdder userAdder, UserGetter userGetter) {
        this.userAdder = userAdder;
        this.userGetter = userGetter;
    }

    public void run() {
        System.out.println(userGetter.get());
        userAdder.add("Maciej");
        System.out.println(userGetter.get());
    }
}
