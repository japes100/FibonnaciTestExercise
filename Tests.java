package tests;

import fibonacci.fibonaccitest.APICalls;
import fibonacci.fibonaccitest.SuperApiCalls;
import tests.SuperForTests.*;
import org.junit.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Tests extends SuperForTests {

	 private static APICalls apiCalls;

	    //The following members should be parameterised.
	    private static String url;
	    private static final String[] fibonocciNumbers = new String[] {"0", "1", "1", "2", "3", "5", "8", "13", "21", "34"};
	    private static final int startIndex = 3;
	    private static final int finishIndex = 4;
	    private static final int selectedIndex = 5;

	    @BeforeClass
	    public static void setUp() {
	        apiCalls = new APICalls();
	        url = System.getProperty("url", "http://localhost:7003/fib");	
	    }

	    /**
	     * Testing API /fib
	     */
	    @Test
	    public void fibPathTest() {
	        ArrayList<String> actual = apiCalls.customGetCall(url);
	        ArrayList<String> expected = new ArrayList<String>( Arrays.asList( fibonocciNumbers ));
	        Assert.assertTrue(
	                        "The fibonacci numbers returned by the API are incorrect when compared to the local ",
	                        actual.equals(expected)
	        );
	    }

	    /**
	     * Testing API with range /fib/range startIndex, finishIndex
	     */
	    @Test
	    public void rangeTest() {
	        ArrayList<String> actualFibonacciNumbers = apiCalls.rangeGetCall(url+"/range", startIndex, finishIndex);
	        Assert.assertTrue("rangeGetCall - The fibonacci numbers returned by the API are incorrect",
	                actualFibonacciNumbers.size() == (finishIndex - startIndex));
	        verifyFibonacciSeries(actualFibonacciNumbers);
	    }

	    /**
	     * Testing API with path parameter
	     * /fib/{index}
	     */
	    @Test
	    public void singleNumberTest() {
	        String actual = apiCalls.singleNumberCall(url, selectedIndex);
	        ArrayList<String> fibonacciNumbers = apiCalls.rangeGetCall(url+"/range", 0, selectedIndex);
	        //Check the value returned by the path param call should be equal to sum of the last two numbers of the fibonacci numbers
	        Assert.assertTrue(
	                "The fibonacci numbers returned by the API are incorrect",
	                Integer.parseInt(actual) == (Integer.parseInt(fibonacciNumbers.get(selectedIndex-1)) + Integer.parseInt(fibonacciNumbers.get(selectedIndex-2)))
	                );
	    }
	    
	    /**
	     * This test is designed to find the defect indicated in the API notes, i.e. 
	     * "When does the application STOP returning Fibonacci numbers in their correct sequence?"
	     * The logic is to iterate along the sequence checking the sum of the previous two numbers is equal to the 
	     * next number in the sequence. This is testing the API against itself but I think in this context this is OK.
	     * This uses the /range and /fib endpoints. 
	     */
	    @Test
	    public void checkEachFibNumberInSequence() {
		    int	startIndex = 0; //start of range
		    int	endIndex = 2; //to get a range the endIndex needs to be 1 greater than the actual end of the range  
		    int	testIndex = 2;
	   	    
		    for(int i=0; i<100; i++) {
		    	startIndex++;
		    	endIndex++;
	    	    testIndex++;
	    	    System.out.println("Indexs are:  " + startIndex + " " + endIndex + " " + testIndex);
		    	ArrayList<String> actualIndex1and2 = apiCalls.rangeGetCall(url+"/range", startIndex, endIndex);
		    	String actualIndex3Value = apiCalls.singleNumberCall(url, testIndex);
		        System.out.println("Actual values are: Index1 = " + actualIndex1and2.get(0) + " and Index2 = "  + actualIndex1and2.get(1)); 
		        System.out.println("actualIndex3Value = " + actualIndex3Value);
		        int sumIndex1and2 = Integer.parseInt(actualIndex1and2.get(0))+Integer.parseInt(actualIndex1and2.get(1));
		        System.out.println("sum = " + sumIndex1and2);
		        
		        if(!(sumIndex1and2 == Integer.parseInt(actualIndex3Value))){
		        	System.out.println("Index  " + testIndex + "  does not equal the sum of Index1 and Index2. testIndex has a value of:  "  
			        		 + actualIndex3Value + "  when it should be:  " + sumIndex1and2);
		        	break;
			    }    
		    }
	    }
	    

	    @AfterClass
	    public static void tearDown() {
	        //no tear down tasks as yet
	    }
	}
