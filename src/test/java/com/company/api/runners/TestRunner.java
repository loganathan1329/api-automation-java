package com.company.api.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;
import com.company.api.listeners.TestListener;

@Listeners({TestListener.class})
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.company.api.stepdefinitions"},
    plugin = {"pretty", "html:target/cucumber-html-report.html"},
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}

