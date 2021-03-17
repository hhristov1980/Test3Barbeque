package main.items;

public class TomatosCucumbers extends Garnitura{
    @Override
    protected String validateName() {
        return "TomatoesCucumber";
    }

    @Override
    protected Double validatePrice() {
        return 1.3;
    }

    @Override
    protected int validateTime() {
        return 3;
    }

    @Override
    protected int validateId() {
        return 5;
    }
}
