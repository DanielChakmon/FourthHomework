public class Property {
    private Address propertyLocation;
    private int howManyRooms;
    private int price;
    private int houseCategory;
    private boolean forRental;
    private int houseNumber;
    private int floorNumber;
    private User propertyOwner;

    public Property(Address propertyLocation, int howManyRooms, int price, int houseCategory, boolean forRental, int houseNumber, int floorNumber, User propertyOwner) {
        this.propertyLocation = propertyLocation;
        this.howManyRooms = howManyRooms;
        this.price = price;
        this.houseCategory = houseCategory;
        this.forRental = forRental;
        this.houseNumber = houseNumber;
        this.floorNumber = floorNumber;
        this.propertyOwner = propertyOwner;
    }

    public Address getPropertyLocation() {
        return propertyLocation;
    }

    public void setPropertyLocation(Address propertyLocation) {
        this.propertyLocation = propertyLocation;
    }

    public int getHowManyRooms() {
        return howManyRooms;
    }

    public void setHowManyRooms(int howManyRooms) {
        this.howManyRooms = howManyRooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getHouseCategory() {
        return houseCategory;
    }

    public void setHouseCategory(int houseCategory) {
        this.houseCategory = houseCategory;
    }

    public boolean isForRental() {
        return forRental;
    }

    public void setForRental(boolean forRental) {
        this.forRental = forRental;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public User getPropertyOwner() {
        return propertyOwner;
    }

    public void setPropertyOwner(User propertyOwner) {
        this.propertyOwner = propertyOwner;
    }

    public String toString() {
        final int REGULAR_APARTMENT_CASE = 1;
        final int PENTHOUSE_APARTMENT_CASE = 2;
        final int PRIVATE_HOUSE = 3;
        String rentalOrSaleStatus;
        if (forRental) {
            rentalOrSaleStatus = "-for rent";
        } else {
            rentalOrSaleStatus = "-for sale";
        }
        String houseCategoryTextPrint = new String();
        switch (houseCategory) {
            case REGULAR_APARTMENT_CASE: {
                houseCategoryTextPrint = "Regular apartment ";
                break;
            }
            case PENTHOUSE_APARTMENT_CASE: {
                houseCategoryTextPrint = "Penthouse apartment";
                break;
            }
            case PRIVATE_HOUSE: {
                houseCategoryTextPrint = "Private house";
            }
        }
String textAboutFloor=new String();
        if(houseCategory!=3){ textAboutFloor=", floor "+ floorNumber;}
        return houseCategoryTextPrint + rentalOrSaleStatus + ": " + howManyRooms + " rooms"+ textAboutFloor+ ".\n" +
                "Price: " + price + "$. \n" +
                "Contact info: " + propertyOwner;
    }
}
