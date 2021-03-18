package main.items;

public class CabbageCarrots extends Garnish {
    @Override
    protected String validateName() {
        return "CabbageCarrots";
    }

    @Override
    protected Double validatePrice() {
        return 0.8;
    }

    @Override
    protected int validateTime() {
        return 2;
    }

    @Override
    protected int validateId() {
        return 4;
    }
}
