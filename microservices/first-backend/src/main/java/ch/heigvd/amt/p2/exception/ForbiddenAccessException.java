package ch.heigvd.amt.p2.exception;

public class ForbiddenAccessException extends Exception {

    private String ressource;
    private Object id;

    public ForbiddenAccessException(String ressource, Object id) {
        super();
        this.ressource = ressource;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Vous n'êtes pas authorisé à accéder à la ressource " + this.ressource + " d'identifiant " + this.id;
    }
}