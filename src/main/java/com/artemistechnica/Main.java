package com.artemistechnica;

import com.artemistechnica.elements.Element;
import com.artemistechnica.json.Serializer;
import com.artemistechnica.models.UIList;

import static com.artemistechnica.elements.Element.*;

public class Main {
    public static void main(String[] args) {
        App.AppContext context = new App.AppContext();
        UIList<App.AppContext> screenUI = App.makeLoginScreen(context);
        System.out.println(Serializer.prettyPrint(screenUI));
    }

    public static class App {

        @SuppressWarnings("unchecked")
        static <A extends CTX> UIList<A> makeLoginScreen(A context) {
            return (UIList<A>) screen(
                    Element.list("vertical",
                            string("Hello, thank you for your interest to log in!"),
                            string("Please sign in below:"),
                            list("vertical",
                                    string("Username:"),
                                    string("Password:"),
                                    list("horizontal", string("Sign In"), string("Cancel"))
                            ))).apply(context);
        }

        public static class AppContext extends Context {

            public AppContext() {
                setDepth(0);
            }
            public AppContext(int depth) {
                setDepth(depth);
            }
            @Override
            public AppContext pure(int depth) {
                return new AppContext(depth);
            }
        }
    }
}