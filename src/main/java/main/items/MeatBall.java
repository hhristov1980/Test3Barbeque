package main.items;

public class MeatBall extends Meat{
    @Override
    protected String validateName() {
        return "MeatBall";
    }

    @Override
    protected Double validatePrice() {
        return 1.0;
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
