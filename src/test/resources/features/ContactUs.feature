@regression @contact-us
Feature: WebdriverUniversity.com - Contact Us Page

  Background: Pre Conditions for Contact Us Scenarios
    Given I navigate to the webdriveruniversity homepage
    When I click on the contact us button

  @smoke
  Scenario: Valid Contact Us Form Submission
    And I type a first name
    And I type a last name
    And I enter an email address
    And I type a comment
    And I click on the submit button
    Then I should be presented with a successful contact us submission message

  @negativeScenario
  Scenario: Invalid Contact Us Form Submission
    And I type a first name
    And I type a last name
#    And I enter an email address
    And I type a comment
    And I click on the submit button
    Then I should be presented with an unsuccessful "Error: all fields are required" message
    Then I should be presented with an unsuccessful "Error: Invalid email address" message

  @randomData
  Scenario: Valid Contact Us Form Submission - Using random data
    And I type a random first name
    And I type a random last name
    And I enter an random email address
    And I type a random comment
    And I click on the submit button
    Then I should be presented with a successful contact us submission message

  @scenarioOutline
  Scenario Outline: Valid Contact Us Form Submission - Using different data
    And I type a first name as "<name>"
    And I type a last name as "<surname>"
    And I enter an email address as "<email>"
    And I type a comment as "<comment_text>" and <comment_number> as numbers
    And I click on the submit button
    Then I should be presented with a successful contact us submission message

    Examples:
      | name   | surname | email              | comment_text                  | comment_number |
      | Kaan   | Denizay | abc@gmail.com      | Im test automation specialist | 6              |
      | Lionel | Messi   | leomessi@gmail.com | He is goat of football        | 28             |
