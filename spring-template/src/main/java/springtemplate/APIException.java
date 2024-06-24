package springtemplate;

public class APIException extends RuntimeException {

    public APIException(String msg) {
        super(msg);
    }

    public APIException(String msg, Throwable ex) {
        super(msg, ex);
    }

}
