public class Property {
   private String cityName;
   private String street;
   private int roomsCount;
   private int price;
   private int type;
   private boolean ifRenting;
   private int houseNumber;
   private int floorNumber;
   private User publisher;
//O(1)

   public Property(String cityName, String street, int houseNumber, int roomsCount, int price, int type, boolean ifRenting, int floorNumber, User publisher)
   {
      this.cityName = cityName;
      this.street = street;
      this.roomsCount = roomsCount;
      this.houseNumber = houseNumber;
      this.price = price;
      this.type = type;
      this.ifRenting = ifRenting;
      if (floorNumber > 0)
         this.floorNumber = floorNumber;
      this.publisher = publisher;
   }
   //O(1)

   public boolean equals(Property property){
      boolean equal = false;
      if (property != null) {
         if (this.cityName.equals(property.getCityName()) &&
                 this.street.equals(property.getStreet()) &&
                 this.roomsCount== property.getRoomsCount() &&
                 this.price == property.getPrice() &&
                 this.type == property.getType() &&
                 this.ifRenting == property.isIfRenting() &&
                 this.floorNumber == property.getFloorNumber() &&
                 this.publisher.equals(property.getPublisher())) {
            equal = true;
         }
      }
      return equal;
   }
//O(1)

   @Override
   public String toString() {
      String output;
      output =  this.cityName + " - " + this.street + " " + this.houseNumber + "\n";
      switch (type) {
         case 1 : output += "Regular apartment - ";
         break;
         case 2 : output += "Penthouse apartment - ";
         break;
         case 3 : output += "Private house - ";
         break;
      }
      if (ifRenting)
         output+= "for rent: ";
      else
         output +="for sale: ";
      output += this.roomsCount +" rooms, ";
      if (this.floorNumber > 0)
         output += "The floor number is: " + this.floorNumber + "\n";
      else
         output += "\n";
      output += "Price: " + this.price + "$" + "\n";
      output += "Contact info: " + this.publisher.getUserName() + " " + this.publisher.getPhone() + " " + this.publisher.getUserType();
      return output;
   }
//O(1)

   public User getPublisher() {
      return publisher;
   }
//O(1)

   public String getCityName() {
      return cityName;
   }
//O(1)

   public String getStreet() {
      return street;
   }
//O(1)

   public int getRoomsCount() {
      return roomsCount;
   }
//O(1)

   public int getPrice() {
      return price;
   }
//O(1)

   public int getType() {
      return type;
   }

   public boolean isIfRenting() {
      return ifRenting;
   }

   public int getHouseNumber() {
      return houseNumber;
   }

   public int getFloorNumber() {
      return floorNumber;
   }
}
