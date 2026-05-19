package stepsdefinitions;


import base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;

public class Hooks {

    public static BaseClass base;

    @Before
    public void startBrowser() {

        if (base == null) {
            base = new BaseClass();
            base.setup();   // ✅ start only once
        }
    }

    @After
    public void closeBrowser() {
        // ❌ DON'T close every scenario
        // leave empty for now
    }
    @AfterAll
    public static void tearDown() {
        if (base != null) {
            base.tearDown();
        }
    }
}