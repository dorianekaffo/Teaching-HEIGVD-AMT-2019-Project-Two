package ch.heigvd.p2.firstapi.service;

import ch.heigvd.p2.firstapi.exception.RessourceNotFoundException;
import ch.heigvd.p2.firstapi.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAuthService {

    public String login(String email, String password) throws RessourceNotFoundException;

    public void logout(HttpServletRequest request, HttpServletResponse response, User user);

}
