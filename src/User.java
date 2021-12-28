public class User {
    private String userName;
    private String password;
    private String phoneNumber;
    private boolean Broker;

    public User(String userName, String password, String phoneNumber, boolean Broker){
        this.userName=userName;
        this.password=password;
        this.phoneNumber=phoneNumber;
        this.Broker=Broker;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String  getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    public boolean isBroker(){
        return Broker;
    }
    public void setBroker(boolean Broker){
        this.Broker=Broker;
    }
    public String toString(){
        String isBrokerStatementText;
        if(Broker){
            isBrokerStatementText=" (real estate broker)";
        }
        else {
            isBrokerStatementText=" (private person)";
        }
       return  userName+" "+phoneNumber+isBrokerStatementText;
    }
}