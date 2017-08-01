Feature: CucumberJava

Scenario Outline: Login functionality exists

Given Login <login> and password <password>

When I logined on Gmail

Then Login should be <result>

 Examples:
  | login         | password  | result  |
  | smtp.epam.gr3 | taepamgr3 | smtp.epam.gr3@gmail.com |