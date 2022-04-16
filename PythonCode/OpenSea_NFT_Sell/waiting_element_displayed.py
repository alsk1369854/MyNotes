from time import sleep
from selenium.webdriver.common.by import By

def waiting_element_displayed(driver, xpath_language):
    sleep_count = 0
    while True:
        try:
            elements = driver.find_elements(By.XPATH, xpath_language)
            if elements[0].is_displayed():
                return elements
        except:
            if(sleep_count >= 50):
                return elements[0].is_displayed()
            pass
        sleep_count += 1
        sleep(1)