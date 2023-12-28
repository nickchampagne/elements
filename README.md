# elements

Develop re-usable UI components.

#### Example

```java
public class Main {
    
    public static void main(String[] args) {
        App.AppContext context  = new App.AppContext();
        UIList screenUI         = App.makeLoginScreen(context);
        
        System.out.println(Serializer.prettyPrint(screenUI));
    }

    public static class App {

        static UIList makeLoginScreen(AppContext context) {
            return screen(
                    list("vertical", List.of(
                            string("Hello, thank you for your interest to log in!"),
                            string("Please sign in below:"),
                            list("vertical", List.of(
                                    string("Username:"),
                                    string("Password:"))),
                            list("horizontal", List.of(string("Sign In"), string("Cancel")))
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
```

#### Output

```json
{
  "context": {
    "depth": 1
  },
  "orientation": "vertical",
  "value": [
    {
      "context": {
        "depth": 2
      },
      "value": "Hello, thank you for your interest to log in!",
      "type": "UIString"
    },
    {
      "context": {
        "depth": 2
      },
      "value": "Please sign in below:",
      "type": "UIString"
    },
    {
      "context": {
        "depth": 2
      },
      "orientation": "vertical",
      "value": [
        {
          "context": {
            "depth": 3
          },
          "value": "Username:",
          "type": "UIString"
        },
        {
          "context": {
            "depth": 3
          },
          "value": "Password:",
          "type": "UIString"
        }
      ],
      "type": "UIList"
    },
    {
      "context": {
        "depth": 2
      },
      "orientation": "horizontal",
      "value": [
        {
          "context": {
            "depth": 3
          },
          "value": "Sign In",
          "type": "UIString"
        },
        {
          "context": {
            "depth": 3
          },
          "value": "Cancel",
          "type": "UIString"
        }
      ],
      "type": "UIList"
    }
  ],
  "type": "UIList"
}
```