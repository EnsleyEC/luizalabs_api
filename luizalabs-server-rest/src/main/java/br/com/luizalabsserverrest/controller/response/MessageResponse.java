package br.com.luizalabsserverrest.controller.response;

public class MessageResponse {

    String message;

    public MessageResponse(String msg)
    {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
