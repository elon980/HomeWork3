public class User {
    private String userName;
    private String password;
    private String phone;
    private String userType;

    public User(String userName, String password, String phoneNumber, String userType){
        this.userName = userName;
        this.password = password;
        this.phone = phoneNumber;
        this.userType = userType;
    }
//O(1)

    public boolean equals(User user){
        boolean equals = false;
        if (this.userName.equals(user.getUserName()))
            equals = true;
        return equals;
    }
//O(1)

    public String getUserName(){
        return this.userName;
    }
//O(1)

    public String getPassword(){
        return this.password;
    }
//O(1)

    public String getPhone() {
        return phone;
    }

    //O(1)
    public String getUserType(){
        return this.userType;
    }
}
