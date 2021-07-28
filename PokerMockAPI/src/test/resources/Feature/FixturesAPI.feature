

Feature: API test to Retrieve all fixtures, single fixture, data validation in fixture result and Create and delete a new fixture

  @RetrieveAllFixtures
  Scenario: Retrieve all fixtures
    Given I want to Retrieve all fixtures
    And Assert that there are total three fixtures in return result
    And Assert that each of the three fixtures has a fixtureId value
    
    @StoreNewFixture
    Scenario: Store a new fixture
    Given I want to create new fixture
    And Get the new fixture
    And Assert within the teams array that the first object has a teamId
    
    @PerforamanceCheckonCreatingNewFixture
    Scenario: Bearing the delay in mind create a new fixture and then retrieve it as soon as its available
    Given I want to create new fixture for performance check
    And then retrieve it as soon as it is available
    
    @CreateAndDdeleteFixture
    Scenario: Create and delete a new fixture
    Given I want to create and then delete a new fixture 
    And Assert that the fixture no longer exists
    
    