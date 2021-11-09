Feature: coinmarketcap frontend testing

  @test1
  Scenario: Verify 100 results are displayed
    Given user is on landing page
    And user verify 100 results are displayed

  @test2
  Scenario: Verify options selected are added to the watchlist
    Given user is on landing page
    And user login successfully

  @test3
  Scenario: Verify data based on filters
    Given user is on landing page
    And I hover over on the "Cryptocurrencies" tab
    When I click on "Ranking" option
    And I record the data
    When I select filters button
    And I select "Store of Value" filter from "Category" dropdown
    And I select "PoW" filter from "Algorithm" dropdown
    And I select "Binance Chain" filter from "Platform" dropdown
    Then I record the data

    