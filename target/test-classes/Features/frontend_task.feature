Feature: coinmarketcap frontend testing

  @test1
  Scenario: Verify 100 results are displayed
    Given user is on landing page
    And user verify 100 results are displayed

  @test3
  Scenario: Verify data based on filters
    Given user is on landing page
    And user hover over on the "Cryptocurrencies" tab
    When user click on "Ranking" option
    And user record the data
    When user select filters button
    And user select "Store of Value" filter from "Category" dropdown
    And user select "PoW" filter from "Algorithm" dropdown
    And user select "Binance Chain" filter from "Platform" dropdown
    Then user record the data

  @test2
  Scenario: Verify options selected are added to the watchlist
    Given user is on landing page
    And user login successfully
    And user select 5 random cryptocurrency
    And user click on watchlist button and navigate to new tab
    And user click on watchlist tab
    And user record the data
