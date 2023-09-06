package pe.bazan.luis.plugins.restarthttp.domain;

public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format(
                "{\"message\": \"%s\"}",
                message
        );
    }
}
