package main.items;

public class WholeGrain extends Bread{
    @Override
    protected String validateName() {
        return "WholeGrain";
    }

    @Override
    protected Double validatePrice() {
        return 1.4;
    }

    @Override
    protected int validateTime() {
        return 5;
    }

    @Override
    protected int validateId() {
        return 2;
    }
}
