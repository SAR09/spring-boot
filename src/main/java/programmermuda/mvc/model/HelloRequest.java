package programmermuda.mvc.model;

public class HelloRequest {

    private String name;

    public HelloRequest(String name) {
        this.name = name;
    }

    public HelloRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
