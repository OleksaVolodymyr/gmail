Feature: CucumberJava

Scenario Outline: Login functionality exists

Given Login <login> and password <password>

When I logined on Gmail

Then Login should be <result>

 Examples:
  | login         | password  | result  |
 