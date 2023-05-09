package de.gothaer.dependency.service;


import de.gothaer.dependency.Dependency;

public class MyServiceUsingDependency {

    private final Dependency dependency;

    public MyServiceUsingDependency(final Dependency dependency) {
        this.dependency = dependency;
    }

    public void methode1(String text) {
        // IrgendeinKram
        // Erzeugen nicht nötig wegen Dependency Injection
        dependency.foo(text.toUpperCase());

    }

    public int methode2() {

        int value = dependency.bar();
        return value * value;

    }

    public int methode3() {

        int result = 0;
        for (int i = 0; i < 3; i++) {
            result += dependency.foobar("Hallo");
        }


        return result;

    }
}
