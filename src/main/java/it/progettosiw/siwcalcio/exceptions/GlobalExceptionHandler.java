package it.progettosiw.siwcalcio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CommentoNonTrovatoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCommentoNonTrovato(CommentoNonTrovatoException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/404";
    }

    @ExceptionHandler(CommentoDiUnAltroException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleCommentoDiUnAltro(CommentoDiUnAltroException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/403";
    }

    @ExceptionHandler(GiocatoreNonTrovatoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleGiocatoreNonTrovato(GiocatoreNonTrovatoException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/404";
    }

    @ExceptionHandler(PartitaNonTrovataException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlePartitaNonTrovata(PartitaNonTrovataException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/404";
    }

    @ExceptionHandler(PartitaConSquadraNonIscrittaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlePartitaConSquadraNonIscritta(PartitaConSquadraNonIscrittaException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/400";
    }

    @ExceptionHandler(SquadraNonTrovataException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleSquadraNonTrovata(SquadraNonTrovataException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/404";
    }

    @ExceptionHandler(ArbitroNonTrovatoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleArbitroNonTrovato(ArbitroNonTrovatoException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/404";
    }

    @ExceptionHandler(TorneoNonTrovatoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTorneoNonTrovato(TorneoNonTrovatoException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/404";
    }

    @ExceptionHandler(UtenteNonTrovatoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUtenteNonTrovato(GiocatoreNonTrovatoException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/error/500";
    }
}

