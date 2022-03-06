# zircon-taf-groovy

### Description

This is test automation framework for app called **zircon** written in Groovy and using Gradle, Junit 5, Selenium, REST Assured. It tests both UI and API.

- Zircon UI you can find here: [dev](https://zircon-front-dev.herokuapp.com)
  , [qa](https://zircon-front-qa.herokuapp.com), [uat](https://zircon-front-uat.herokuapp.com)
  , [stg](https://zircon-front-stg.herokuapp.com), [prod](https://zircon-front-prod.herokuapp.com).
- Zircon API you can find here: [dev](https://zircon-back-dev.herokuapp.com)
  , [qa](https://zircon-back-qa.herokuapp.com), [uat](https://zircon-back-uat.herokuapp.com)
  , [stg](https://zircon-back-stg.herokuapp.com), [prod](https://zircon-back-prod.herokuapp.com).

### Develop

1. Export environment variables:

- _pd_ (needed to encrypt sensitive data, ask me the value)
- _chromedriver_ (path to the chromedriver)
- _geckodriver_ (path to the geckodriver)

2. Run this in command line to launch tests e.g.:

```shell
gradle test regression -Penv=dev -Dbrowser=chrome
```

- env - zircon application environment (e.g.: local, dev, qa, uat, stg, prod)
- browser - browser for UI tests (e.g.: chrome, firefox)
