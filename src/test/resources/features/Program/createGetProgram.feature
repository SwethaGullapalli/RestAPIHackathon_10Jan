
Feature: Create and Verify Program
  I want to create a new program and verify it by using get request

 
  Scenario: Create new Program
    Given A Post Service <url>
    When Request POST with sequence number <seqNo>
    Then Check status code <statusCode>
    
    Examples: 
      | url          | statusCode | seqNo |
      | saveprogram |        201 | 001 |
      | saveprogram |        201 | 002 |
      
  Scenario: Get Program
    Given A Get Service <url>
    When Request GET with sequence number <seqNo>
    Then Check status code <statusCode>
    
    Examples: 
      | url          | statusCode | seqNo |
      | programs/{programId} |        200 | 001 |
      | programs/{programId} |        200 | 002 |

  
