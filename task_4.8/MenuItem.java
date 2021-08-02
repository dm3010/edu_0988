import java.util.function.BiConsumer;
import java.util.function.Consumer;

class MenuItem {
    String title;
    String command;
    Object params;
    Consumer consumer;
    BiConsumer biConsumer;

    public MenuItem(Consumer<Main> consumer, String command, String title) {
        this.title = title;
        this.command = command;
        this.consumer = consumer;
    }

    public <T> MenuItem(BiConsumer<Main, T> biConsumer, T params, String command, String title) {
        this.title = title;
        this.command = command;
        this.biConsumer = biConsumer;
        this.params = params;
    }

    public void run(Object object) {
        if (consumer == null)
            biConsumer.accept(object, params);
        else
            consumer.accept(object);
    }
}
