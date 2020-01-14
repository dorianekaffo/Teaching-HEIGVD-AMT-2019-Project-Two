package ch.heigvd.amt.p2.service;

import ch.heigvd.amt.p2.exception.ResourceNotFoundException;
import ch.heigvd.amt.p2.exception.WrongCredentialsException;
import ch.heigvd.amt.p2.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAuthService {

    public String login(String email, String password) throws ResourceNotFoundException, WrongCredentialsException;

}
