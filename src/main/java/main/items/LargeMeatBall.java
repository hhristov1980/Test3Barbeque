package main.items;

public class LargeMeatBall extends Meat{
    @Override
    protected String validateName() {
        return "LargeMeatBall";
    }

    @Override
    protected Double validatePrice() {
        return 2.0;
    }

    @Override
    protected int validateTime() {
        return 3;
    }

    @Override
    protected int validateId() {
        return 2;
    }
}
