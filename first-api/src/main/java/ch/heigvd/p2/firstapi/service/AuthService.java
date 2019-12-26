package ch.heigvd.p2.firstapi.service;

import ch.heigvd.p2.firstapi.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {

    public String login(String email, String password);

    public void logout(HttpServletRequest request, HttpServletResponse response, User user);

}
