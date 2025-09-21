package testRunner;

import cucumber.api.testng.AbstractTestNGCucumberTests;

@cucumber.api.CucumberOptions(features = {"src/test/java/story/addTocart.feature"},
dryRun=true,//to mapping the feature fine into step definition file
glue={"steps","Priority"},//only give steption definition folder name, Hooks package name 
snippets=cucumber.api.SnippetType.CAMELCASE,//java is camelcase syntax language 
monochrome=true,//remove the junk character in console output
plugin = {"pretty",
		//get the report from json report
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
},
tags = "@Test"
)
public class Runner extends AbstractTestNGCucumberTests{
// we need to extends this class AbstractTestNGCucumberTests intergrate Cucumber +testNG
}
