
public class User {
    private String userId;
    private String password;
    private String type;
    
    public User(){
        
    }
    public User(String userId,String password,String type){
        this.setUserId(userId);
        this.setPassword(password);
        this.setType(type);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
