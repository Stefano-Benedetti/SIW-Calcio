package it.progettosiw.siwcalcio.dto;

import jakarta.validation.constraints.*;

public class RegistrationForm {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Sono ammessi solo lettere, trattini bassi e numeri")
    @Size(min=3, max=20, message="La lunghezza del nome utente deve essere tra i 3 e i 20 caratteri")
    private String username;

    @NotNull
    @Size(min=6, message="La password deve contenere almeno 6 caratteri")
    private String password;

    public RegistrationForm() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
