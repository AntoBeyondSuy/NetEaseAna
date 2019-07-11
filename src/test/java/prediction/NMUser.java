package prediction;

public class NMUser {
    int userId;
    String userName;
    String[] labels;

    public NMUser() {
    }

    public NMUser(int userId, String userName, String[] labels) {
        this.userId = userId;
        this.userName = userName;
        this.labels = labels;
    }

    public int getUserId() {

        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }
}
