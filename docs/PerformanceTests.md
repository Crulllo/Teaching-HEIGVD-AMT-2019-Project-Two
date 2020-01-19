## JMeter
We used JMeter to make performance tests on our api and to make sure it handles the heavy load that we throw at it.

Since our main model entities are more or less alike in size, we chose the do the tests with the UserEntiy, specifically the creation of users.

We created different test scenarios that exert more or less heavy load on the server. They can be found [here](https://github.com/Crulllo/Teaching-HEIGVD-AMT-2019-Project-Two/tree/master/jmeter) in the JMeter folder in the root of our project.

For this we implemented a special endpoint that received POST http requests  to `api/testUsers`with an empty body, and creates a user with a random username in our db. This is implemented as an admin operation so you have to have an admin token in the header of the request to be able to run the tests.

All tests are ran with a constant delay offset of 30 milliseconds in a Gaussian random timer.

## Test 1 with one virtual user
One virtual performing 100 POST requests of `testUsers` performed as expected, with 100 users created with no erros and a throughut of ~6.1.

## Test 2 with 20 virtual users
The second is one with a thread group of 20 virtual users, each performing 100 requests. This is when things get interesting because we get to see how our server performs under load for the first time. Out of the 2000 samples, there was an error of 0.050% that is 1 user entity that was not created due to heavy load. We received a higher throughtput of 19.3 of course due to the concurrent number of users as expected. Overall the results were better than we thought!
Min 135, Max 4291 std dev 305

## Test 3 with 40 virtual users
This is when we started getting worried our server might crash. The third test was to perform with a thread group of 40 virtual users each performing 100 requests. Our expectations were that the error rate would double proportionally to the thread group size, but it didn't, the error stayed the same at 0.05%. The throughput was even higher at 21.89 and the average time that is almost double of what was in test 2 (1784ms to 992ms).


# Conclusion
It seems for the three performance tests we performed, our server could handle the load it was given, accordingly of course. More interesting tests we could have done but for lack of time we didn't are concurrency tests such as concurrent update requests on entities.