Feature: Bookcart application


  Scenario Outline: Add a product to the cart
    Given User should navigate to the book cart application
    And User should login as "<username>" and "<password>" with validate credential
    And User should search a "<book>" with validate credential
    When User add the book to the cart
    Then The cart badge should be update

    @Test 
    Examples: 
      | username | password | book                                    |
      | ortoni   | pass1234 | Harry Potter and the Chamber of Secrets |

    @Dev
    Examples: 
      | username | password | book       |
      | ortoni   | pass1234 | Rot & Ruin |
