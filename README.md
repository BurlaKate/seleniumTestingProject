# seleniumTestingProject
Testing project for IA.ca

Що не зробила:
1. "Change the Down Payment to 50 000 using the + button of the slider" - це зробити неможливо, 
так як перша поділка на шкалі - 100 000, що я і обрала, від цього змінилась фінальна сума

For test execution:
1. You need to install chromedriver-2.33 and add it to new environment variable
    with name -> webdriver.chrome.driver and value -> path to webdriver also add it to PATH variable
2. The version of Chrome browser should be 60-62
3. Install geckodriver-0.19.0 and add it to new environment variable
       with name -> webdriver.gecko.driver and value -> path to gecko webdriver also add it to PATH variable
4. Firefox 55.0 (and greater)
5. Restart your PC
6. For test execution -> mvn clean test
7. For report execution -> mvn test allure:serve

