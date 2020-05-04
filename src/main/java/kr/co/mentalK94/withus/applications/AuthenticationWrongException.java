package kr.co.mentalK94.withus.applications;

public class AuthenticationWrongException extends RuntimeException {

    AuthenticationWrongException() {
        super("Email or Password is wrong!");
    }
}
