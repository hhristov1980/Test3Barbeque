package main.items;

public class Russian extends Garnish {
    @Override
    protected String validateName() {
        return "Russian";
    }

    @Override
    protected Double validatePrice() {
        return 1.5;
    }

    @Override
    protected int validateTime() {
        return 10;
    }

    @Override
    protected int validateId() {
        return 1;
    }
}
