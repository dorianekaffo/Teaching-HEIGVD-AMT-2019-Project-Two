package ch.heigvd.p2.firstapi.exception;

public class RessourceNotFoundException extends Exception {

    private String ressource;
    private String fieldName;
    private Object fieldValue;

    public RessourceNotFoundException(String ressource, String fieldName, Object fieldValue) {
        super();
        this.ressource = ressource;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    @Override
    public String getMessage() {
        return "La ressource " + this.ressource + " ayant pour attribut " + this.fieldName + " de valeur " + this.fieldValue;
    }
}
