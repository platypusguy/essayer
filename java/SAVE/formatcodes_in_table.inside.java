public class main {

    /*
        Check the current test case result.
     */
    private static int ckResult(TestCase testCase, String observed) {
        String reportFormat = "format: \"%s\", arg: %d, expected: %s, observed: %s";
        String details = String.format(reportFormat,
                testCase.fmt, testCase.arg, testCase.expected, observed);
        if (observed.equals(testCase.expected)) {
            System.out.printf("PASS: %s\n", details);
            return 0;
        } else {
            System.out.printf("*** FAIL: %s\n", details);
            return 1;
        }
    }

    /*
        Main entry point from command line.
        Drives off the testCases table.
     */
    public static void main(String[] args) {

        // Define test cases
        TestCase [] testCases = {
                new TestCase("%d", 12345, "12345"),
                new TestCase("%06d", 12345, "012345"),
                new TestCase("%x", 255, "ff"),
        };

        String observed;
        int errCount = 0;
        System.out.println("Next step: int countTestCases = testCases.length");
        int countTestCases = testCases.length;
        System.out.printf("Number of test cases = %d\n", countTestCases);

        // Run all the test cases.
        for (TestCase testCase : testCases) {
            observed = String.format(testCase.fmt, testCase.arg);
            errCount += ckResult(testCase, observed);
        }

        // Throw an AssertionError if the error count is nonzero.
        if (errCount > 0) {
            String errMsg = String.format("*** ERROR count = %d", errCount);
            throw new AssertionError(errMsg);
        }

        // Success!
        System.out.println("Success!");
    }
}

/*
    Test case definition. Oh, how I wish that Java had a struct type!
 */
class TestCase {
    String fmt;
    // SPECIFIC to argument type:
    int arg;
    String expected;

    TestCase(String aa, int bb, String cc) {
        this.fmt = aa;
        this.arg = bb;
        this.expected = cc;
    }
}
