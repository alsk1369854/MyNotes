# 475 * 625

# //button[@id="duration"] # 存活日期
# //input[@name="price"] # 價錢
# //div[@class="Overflowreact__OverflowContainer-sc-7qr9y8-0 jPSCbX"] # 6 months
# //button[@type="submit"] # 提交


# //div[@class='signature-request-message--root']

# //button[text()='簽署']

# //span[text()='Share your listing']


# 搜索網址
# https://opensea.io/collection/school-little-dinosaur?search[query]=
# School%20Littel%20Dinosaur%20%233 # 範例 School Littel Dinosaur #3
# &search[sortAscending]=true&search[sortBy]=CREATED_DATE

# 項目
# //a[@class='styles__StyledLink-sc-l6elh8-0 ekTmzq Asset--anchor']

# Sell button
# //a[text()='Sell']


from time import sleep
from selenium.webdriver.common.by import By
import os
from selenium.webdriver.common.keys import Keys
from pathlib import Path

from chrome_browser import get_driver
from metamask_connector import meta_mask_navigate
from login_opensea import login_opensea
from waiting_element_displayed import waiting_element_displayed


# SECRET_RECOVERY_PHRASE = 'YOUR SECRET_RECOVERY_PHRASE'
SECRET_RECOVERY_PHRASE = 'XXX'
# NEW_PASSWORD = 'YOUR NEW_PASSWORD'
NEW_PASSWORD = 'alsk1369854Qpxm'
# COLLECTION_CREATE_PATH = 'https://opensea.io/collection/school-little-dinosaur/assets/create'
COLLECTION_CREATE_PATH = 'https://opensea.io/collection/school-little-dinosaur/assets/create'

IMAGES_DIR_PATH = "C:\py_wk\opensea_nft_uploader\Sell_Item\Sell_images"
IMAGES_UPLOADED_DIR_PATH = "C:\py_wk\opensea_nft_uploader\Sell_Item\Sell_images\Waiting_Sell"

# 項目標頭
image_name_title = 'School Little Dinosaur #'

# 賣價 ETH
SELL_PRICE = '0.01'

# 搜索網址
first_url = 'https://opensea.io/collection/school-little-dinosaur?search[query]='
last_url = '&search[sortAscending]=true&search[sortBy]=CREATED_DATE'

driver = get_driver()

def find_item(item_number):
    image_name = item_number + '.png'
    item_name = image_name_title + item_number

    driver.get(first_url + item_number + last_url)
    driver.execute_script('document.documentElement.scrollTop=810')
    sleep(1.5)

    # https: // opensea.io / collection / school - little - dinosaur?search[query] = School % 20L
    # ittle % 20
    # Dinosaur % 20  # 132&search[sortAscending]=true&search[sortBy]=CREATED_DATE

    if (driver.title == 'opensea.io | 504: Gateway time-out'):
        print(driver.title)
        find_item(item_number)
        return
    try:
        item_list = driver.find_elements(By.XPATH, '//div[text()="' + item_name + '"]')
        item_len = len(item_list)
        if (item_len == 0):
            print(item_Item + ", 數量: " + str(item_len))
            sleep(5000);
            return
        else:
            # item = driver.find_element(By.XPATH, "//a[@class='styles__StyledLink-sc-l6elh8-0 ekTmzq Asset--anchor']")
            # item.click()
            item_list[0].click()
            sell_button = waiting_element_displayed(driver, "//a[text()='Sell']")[0]
            sell_button.click()
            item_sell(image_name)
    except:
        print('out ' + image_name + ': ' + str(item_len) + '............')
        print(driver.title + '\n')

    return

def item_sell(image_name):
    try:
        sleep(2)
        input_price = driver.find_element(By.XPATH, '//input[@name="price"]')
        input_price.send_keys(SELL_PRICE);

        # driver.execute_script('document.documentElement.scrollTop=1000')
        button_submit = driver.find_element(By.XPATH, '//button[@type="submit"]')
        button_submit.click();

        sleep(20)

        driver.switch_to.window(driver.window_handles[2])

        driver.find_element(By.XPATH, "//div[@class='signature-request-message--root']").click();
        driver.execute_script('document.documentElement.scrollTop=3000')
        driver.find_element(By.XPATH, "//button[text()='簽署']").click()

        # sleep(8)

        driver.switch_to.window(driver.window_handles[1])
        waiting_element_displayed(driver, "//span[text()='Share your listing']")
        Path(IMAGES_DIR_PATH + '/Waiting_Sell/' + image_name).rename(IMAGES_DIR_PATH + '\Sell_Finish/' + image_name)
    except:
        print('item_sell_except ' + image_name)

    return


if __name__ == '__main__':

    meta_mask_navigate(driver, SECRET_RECOVERY_PHRASE, NEW_PASSWORD)
    login_opensea(driver, COLLECTION_CREATE_PATH)

    width = 475
    height = 625
    driver.set_window_size(width=width, height=height)

    # sort image list
    sorted_image_list = []
    for image in os.listdir(IMAGES_UPLOADED_DIR_PATH):
        count = 0
        if (image.endswith(".png")):
            sorted_image_list.append(int(image.replace(".png", "")))
            count += 1
    sorted_image_list.sort()
    print(sorted_image_list);
    # result_array = [0]*len(sorted_image_list)
    for i in range (len(sorted_image_list)):
            item_number = str(sorted_image_list[i])
            find_item(item_number)
