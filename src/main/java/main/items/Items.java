package main.items;

public abstract class Items {
    private String name;
    private double price;
    private int timeToPrepare;
    private int itemId;

    public Items(){
        this.name = validateName();
        this.price = validatePrice();
        this.timeToPrepare = validateTime();
        this.itemId = validateId();
    }

    protected abstract String validateName();
    protected abstract Double validatePrice();
    protected abstract int validateTime();
    protected abstract int validateId();


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getItemId() {
        return itemId;
    }

    public int getTimeToPrepare() {
        return timeToPrepare;
    }


}
