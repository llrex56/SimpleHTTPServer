package design.patterns.chain;

public abstract class Handler<T, R> {

    protected Handler<T, R> next;

    private void setNext(Handler<T, R> handler) {
        this.next = handler;
    }

    public abstract R exec(T t);

    public static class Builder<T, R> {
        private Handler<T, R> head;
        private Handler<T, R> tail;

        public Builder<T, R> addHandler(Handler<T, R> handler) {
            if (this.head == null) {
                this.head = this.tail = handler;
                return this;
            }
            this.tail.setNext(handler);
            this.tail = handler;

            return this;
        }

        public Handler<T, R> build() {
            return this.head;
        }
    }
}