Feature: User
  As an consumer
  I want to create user via api
  So that i can login to systems

  Scenario: Valid User
    When I execute post request on endpoint "/register" with payload "user"
    Then I should see status code as "200"
    Then I should see response key "message.nn.hhh.hhh.hhh.jjj" set to value "user is succesufully"




  https://api.whitbread.co.uk/snowdrop/search
  /hotels/availabilities?bookingChannel=WEB&location
  =ChIJdd4hrwug2EcRmSrV3Vo6llI&locationFormat=
  PLACEID&arrival=2019-04-27&departure=2019-04-28&adults
  =1&children=0&cot=false&type=DB&rooms=1&cellCodes=&size=
  40&page=1&country=gb&language=en&radiusUnit=MILES