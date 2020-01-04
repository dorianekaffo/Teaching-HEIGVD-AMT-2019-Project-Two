package ch.heigvd.amt.p2.exception;

public class ResourceNotFoundException extends Exception {

    private String ressource;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException(String ressource, String fieldName, Object fieldValue) {
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
