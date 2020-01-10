package ch.heigvd.amt.p2.exception;

public class UnauthorizedAccessException extends Exception {

    private String ressource;
    private Object id;

    public UnauthorizedAccessException(String ressource, Object id) {
        super();
        this.ressource = ressource;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Vous n'êtes pas authorisé à accéder à la ressource " + this.ressource + " d'identifiant " + this.id;
    }
}