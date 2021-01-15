# NABAssignment

Pre-setup: this project is built from
 - JDK 1.8
 - Maven 3.6.3

How to run:
1. Clone the project: git clone https://github.com/ttquang78/NABAssignment.git
2. Open the console, go to working folder and run the command: mvn clean test -PNABAssignment -Dwebruntime=[runtime] -Dos=[os] -Dbrowser=[browser] -DlocalDriverPath=[localdriverpath]
 - To run with default settings (local, windows, chrome): mvn clean test -PNABAssignment
 - Valid values of [runtime]: local (default), saucelabs
 - Valid values of [os]: windows (default), mac
 - Valid values of [browser]: 
	+ Windows: chrome (default), firefox, ie
	+ Mac: chrome (default), firefox
 - Default local driver path [runtime]: [working folder]\seleniumdrivers
 - Data files are put under [working folder]\Data with name convention: OW_[test name]_Data.csv
 - To run on Saucelabs, please use more params: -DsaucelabUser=[username], -DsaucelabAccessKey=[access key]
 - Report is under: [working folder]\target\surefire-reports\html\index.html