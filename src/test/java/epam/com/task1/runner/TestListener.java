package epam.com.task1.runner;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

/**
 * Created by Admin on 22.11.2015.
 */
public class TestListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        System.out.println("method started: " + iInvokedMethod.getTestMethod().getMethodName());
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        System.out.println("method finished [" + iTestResult.getStatus() + "]:");
    }
}
