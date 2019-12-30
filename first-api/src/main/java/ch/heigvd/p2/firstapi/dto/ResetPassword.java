package ch.heigvd.p2.firstapi.dto;

public class ResetPassword {

    private String token;
    private String newPassword;

    public ResetPassword() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
