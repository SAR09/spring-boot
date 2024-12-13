package programmermuda.validation.data;

import programmermuda.validation.validation.Palindrome;

public class Foo {

    @Palindrome
    private String foo;

    public Foo(String foo) {
        this.foo = foo;
    }

    public Foo() {
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

}
