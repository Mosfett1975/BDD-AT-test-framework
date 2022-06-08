#language: en

  @testEng
Feature: Google search
  Scenario: search and click to result

    When opened link "https://google.com/"
    And set current page "Main page"
    And in the field "Search line" entered value "start Selenide"
    And click on button "Search button"
    And display list of elements "Results"
    And clicked to text "Quick Start - Selenide" into list "Results"
    And URL current page equals "https://selenide.org/quick-start.html"
    And delayed by 10 seconds