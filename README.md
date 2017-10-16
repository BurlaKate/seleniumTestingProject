# seleniumTestingProject
Testing project for IA.ca

Що не зробила:
1. "Change the Down Payment to 50 000 using the + button of the slider" - це зробити неможливо, так як перша поділка на шкалі - 100 000,
  що я і обрала, від цього змінилась фінальна сума

Для запуску тестів:
1. You need to install chromedriver-2.33 and add it to new environment variable
    with name -> webdriver.chrome.driver 
    and value -> path tp webdriver 
    also add it to PATH variable
    https://sites.google.com/a/chromium.org/chromedriver/downloads
2. The version of Chrome browser should be 60-62
3. Restart your PC
4. For start project use command - mvn test

Для запуску репортів:
1. test allure:serve

