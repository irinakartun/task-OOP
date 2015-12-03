package epam.com.task1.runner;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 22.11.2015.
 */
public class TestRunner {

    public static void main(String[] args) {
        TestListenerAdapter tla = new TestListenerAdapter();
        TestNG tng = new TestNG();
        tng.addListener(tla);
        tng.addListener(new TestListener());

        XmlSuite suite = new XmlSuite();
        suite.setName("oop task suite");

        List<String> files = new ArrayList<>();
        files.addAll(new ArrayList<String>() {{add("src/test/resources/suites.xml");
        }});
        suite.setSuiteFiles(files);
        suite.setParallel(XmlSuite.ParallelMode.METHODS);
        suite.setThreadCount(5);

        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);
        tng.setXmlSuites(suites);

        tng.run();
    }

 }
