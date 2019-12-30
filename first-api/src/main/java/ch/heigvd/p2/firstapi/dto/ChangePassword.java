package ch.heigvd.p2.firstapi.dto;

public class ChangePassword {

    private String newPassword;
    private String oldPassword;

    public ChangePassword() { }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
