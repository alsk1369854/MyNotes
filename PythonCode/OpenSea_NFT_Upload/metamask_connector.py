from selenium.webdriver.common.by import By

from waiting_element_displayed import waiting_element_displayed

def meta_mask_navigate(driver, SECRET_RECOVERY_PHRASE, NEW_PASSWORD):
    driver.switch_to.window(driver.window_handles[0])

    start_button = waiting_element_displayed(driver ,'//button[@class="button btn--rounded btn-primary first-time-flow__button"]')[0]
    start_button.click()

    import_wallet_button = waiting_element_displayed(driver ,'//button [text()="匯入錢包"]')[0]
    import_wallet_button.click()

    agree_button = waiting_element_displayed(driver , '//button [text()="I Agree"]')[0]
    agree_button.click()

    input_list = waiting_element_displayed(driver, '//input')
    input_list[0].send_keys(SECRET_RECOVERY_PHRASE)
    input_list[1].send_keys(NEW_PASSWORD)
    input_list[2].send_keys(NEW_PASSWORD)
    checkbox = waiting_element_displayed(driver , '//div[@class="first-time-flow__checkbox first-time-flow__terms"]')[0]
    checkbox.click()

    waiting_element_displayed(driver, '//button [text()="匯入"]')[0].click()

    waiting_element_displayed(driver, '//button [text()="都完成了"]')[0].click()


# time.sleep(3)
# driver.switch_to.window(driver.window_handles[0])
# time.sleep(3)
# # driver.find_element(By.CLASS_NAME, "first-time-flow__button").click()
# # driver.find_element(By.XPATH, "//div[@class='welcome-page']/button[@role='button']").click()
# test = driver.find_element(By.XPATH, '//button[@class="button btn--rounded btn-primary first-time-flow__button"]')
# print(test.text)
# test.click()
# # driver.find_element_by_class_name("first-time-flow__button").click()
#
# time.sleep(2)
# driver.find_element(By.XPATH, "//button [text()='匯入錢包']").click()
#
# time.sleep(2)
# driver.find_element(By.XPATH, "//button [text()='I Agree']").click()
#
# time.sleep(2)
# inputs = driver.find_elements(By.XPATH, '//input')
# inputs[0].send_keys(SECRET_RECOVERY_PHRASE)
# inputs[1].send_keys(NEW_PASSWORD)
# inputs[2].send_keys(NEW_PASSWORD)
# checkbox_list = driver.find_elements(By.CLASS_NAME,
#                                      "first-time-flow__checkbox-label")
# checkbox_list[1].click()
#
# time.sleep(1)
# driver.find_element(By.XPATH, "//button [text()='匯入']").click()
#
# time.sleep(5)
# driver.find_element(By.XPATH, "//button [text()='都完成了']").click()
#
# time.sleep(1)
#
#
# def get_driver():
#     return driver