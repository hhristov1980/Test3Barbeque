package main.items;

public class White extends Bread{
    @Override
    protected String validateName() {
        return "White";
    }

    @Override
    protected Double validatePrice() {
        return 0.9;
    }

    @Override
    protected int validateTime() {
        return 2;
    }

    @Override
    protected int validateId() {
        return 1;
    }
}
