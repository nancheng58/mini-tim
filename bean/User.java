package bean;
import java.math.BigDecimal;

public class User {
    private String userId = "-1";
    private String targetId = "-1";
    private boolean isClass = false;

    private boolean isAdmin = false;

    public boolean isAdmin() {return isAdmin; }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    private int token =-1;

    public void setToken(int token) {
        this.token = token;
    }

    public int getToken() {return token; }
    public void setUserId(String userId) {
        this.userId = userId;

    }

    public String getUserId() {
        return userId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetId() { return targetId; }
    public boolean isClass() {
        return isClass;
    }

    public void setClass(boolean aClass) {
        isClass = aClass;
    }

}