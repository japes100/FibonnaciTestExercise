"# FibonnaciTestExercise" 

In my setup the API was running on port as in: http://localhost:7003/fib

There are 3 endpoints: fib, index and range.

I am not sure about the interfaces referred to in the exercise notes.

The answer to the test question: "When does the application STOP returning Fibonacci numbers in their correct sequence?" is 30, i.e. the 30th number in the Fibonacci sequence (if you start the count at 1) is incorrect. It is <514228> rather than <514229> which is the expected sum of the preceding two numbers in the sequence. The last test was used to find this answer.

For the range test I observe that while the start index is included the end index is not so if you want numbers 3 to 4 inclusive you actually have to send 3 and 5.
