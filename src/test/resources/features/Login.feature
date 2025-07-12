@regression @login
Feature: WebdriverUniversity.com - Login Page

  Background: Pre Conditions for Contact Us Scenarios
    Given I navigate to the webdriveruniversity homepage
    When I opened the login page

  @smoke @ignore
  Scenario Outline: Positive & Negative Login scenarios
    And I type username as "<username>"
    And I type password as "<password>"
    And I click on the login button
    Then I should be see the "<message>" alert message

    Examples:
      | username  | password     | message              |
      | webdriver | webdriver123 | validation succeeded |
      | webdriver | qwerty123    | validation failed    |


