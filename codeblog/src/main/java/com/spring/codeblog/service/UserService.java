package com.spring.codeblog.service;

import com.spring.codeblog.model.LoginDto;
import com.spring.codeblog.model.User;
import com.spring.codeblog.repository.UserRepository;
import com.spring.codeblog.utils.Authorization;
import com.spring.codeblog.utils.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private static final String INTERNAL_SERVER_ERROR_MSG = "Internal Server Error, please contact our support";

    public User criarUsuario(User user) throws Exception {
        try {

            User result = userRepository.findByEmail(user.getEmail());

            if (result != null) throw new Errors("E-mail já cadastrado", HttpStatus.BAD_REQUEST);

            result = new User(user.getNome(), user.getPerfil(), user.getEmail(), user.getPassword());

            return userRepository.save(result);

        } catch (Errors customError) {
            throw customError;
        } catch (Exception error) {
            throw new Errors(INTERNAL_SERVER_ERROR_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public User login(LoginDto loginDto) throws Exception {
        try {
            User result = userRepository.findByEmail(loginDto.getEmail());

            if (result == null) throw new Errors("E-mail não encontrado", HttpStatus.BAD_REQUEST);

            Boolean pwdMatch = Authorization.bcryptMatch(loginDto.getPassword(), result.getPassword());

            if (pwdMatch.equals(Boolean.FALSE)) throw new Errors("Senha inválida.", HttpStatus.FORBIDDEN);

            String token = Authorization.generateToken(loginDto);

            userRepository.updateToken(token, loginDto.getEmail());

            return result.getUserLogin(result, token);

        } catch (Errors customError) {
            throw customError;
        } catch (Exception error) {
            throw new Errors(INTERNAL_SERVER_ERROR_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
