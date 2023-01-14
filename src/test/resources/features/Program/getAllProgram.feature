#/* Scenario Outline: Save Program (Create 2 programs)
#Given A Service with <url>
#AND
#Request body at "path"
#When "programName,creationTime,last,ModTime" are modified
#AND Post request is made
#Then Save "programId"
#AND
#Validate status code
#AND
#Validate "programName,programDescription,programStatus"
#
#Examples:
#| url | statusCode|
#| / | 201 |*/
Feature: Verify all the Get Posts in API

  Scenario Outline: Get all Posts from API
    Given A Service with <url>
    When Get request is made
    Then Validate <statusCode>

    Examples: 
      | url          | statusCode |
      | allPrograms |        200 |