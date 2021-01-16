package com.pingone.automation.webportal.common;

public class CustomData {
    public enum OS {
        WINDOWS("test-"), MAC("ort-");

        private String os;

        OS(String name) {
            this.os = name;
        }

        public String getOs() {
            return this.os;
        }
    }

    public enum BrowserName {
        CHROME("chrome"), FIREFOX("firefox"), IE("Internet Explorer"), SAFARI("safari"), MSEDGE("microsoftedge");

        private String browser;

        BrowserName(String name) {
            this.browser = name;
        }

        public String getBrowser() {
            return this.browser;
        }
    }

}
