package ch.heigvd.amt.p2.service;

import ch.heigvd.amt.p2.exception.PasswordMismatchException;
import ch.heigvd.amt.p2.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService<T, U> {

    public U create(U user);

    public void changePassword(T userId, String oldPassword, String newPassword) throws PasswordMismatchException, ResourceNotFoundException;

    public void toggleBlock(T userId, boolean willBlock) throws ResourceNotFoundException;

    public void sendCode(T userId) throws ResourceNotFoundException;

    public void checkCode(T userId, long code) throws ResourceNotFoundException;

}
