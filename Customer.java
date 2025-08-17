public class Customer {
    private String name;
    private String password;
    private String selectedFloor;
    private String startTime;
    private String expiryTime;

    public Customer(String name, String password, String selectedFloor, String startTime, String expiryTime) {
        this.name = name;
        this.password = password;
        this.selectedFloor = selectedFloor;
        this.startTime = startTime;
        this.expiryTime = expiryTime;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getSelectedFloor() {
        return selectedFloor;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getExpiryTime() {
        return expiryTime;
    }


}