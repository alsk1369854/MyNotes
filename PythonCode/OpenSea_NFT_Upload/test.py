from time import sleep
from selenium.webdriver.common.by import By

from chrome_browser import get_driver
from metamask_connector import meta_mask_navigate
from login_opensea import login_opensea
from waiting_element_displayed import waiting_element_displayed

# SECRET_RECOVERY_PHRASE = 'YOUR SECRET_RECOVERY_PHRASE'
SECRET_RECOVERY_PHRASE = 'owner asthma provide hover vessel crisp sweet have response abandon dice pledge'
# NEW_PASSWORD = 'YOUR NEW_PASSWORD'
NEW_PASSWORD = 'alsk1369854Qpxm'
# COLLECTION_CREATE_PATH = 'https://opensea.io/collection/school-little-dinosaur/assets/create'
COLLECTION_CREATE_PATH = 'https://opensea.io/collection/school-little-dinosaur/assets/create'

Nide_search = 'LittleD'

url = 'https://opensea.io/ChiaMing?search[sortBy]=LISTING_DATE&search[query]=' + Nide_search + '&select=hide'


driver = get_driver()

if __name__ == '__main__':
    width = 546
    height = 735
    driver.set_window_size(width=width, height=height)
    meta_mask_navigate(driver, SECRET_RECOVERY_PHRASE, NEW_PASSWORD)
    login_opensea(driver, COLLECTION_CREATE_PATH)

    while True:
        try:
            driver.get(url)
            driver.execute_script('document.documentElement.scrollTop=800')
            sleep(1)
            item_list = waiting_element_displayed(driver, '//div[@role="grid"]/div//img')
            print(len(item_list))

            # driver.set_window_size()

            count = 0
            for item in item_list:
                if(count >= 19):
                    break
                item_list[count].click()
                count += 1

            waiting_element_displayed(driver,
                                      '//button[@class="Blockreact__Block-sc-1xf18x6-0 Buttonreact__StyledButton-sc-glfma3-0 bhqEJb fzwDgL"]'
                                      )[0].click()
        except:
            pass


