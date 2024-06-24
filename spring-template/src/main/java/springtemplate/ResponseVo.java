package springtemplate;

import java.util.List;

public class ResponseVo {

    private Object data;
    private List<String> errors;

    public ResponseVo() {
    }

    public ResponseVo(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
