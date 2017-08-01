package com.epam.lab.gmail;

import org.junit.runner.RunWith;

import cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(format = {"pretty"}, glue = { "stepDefinition" })
public class RunCucumber {

}
