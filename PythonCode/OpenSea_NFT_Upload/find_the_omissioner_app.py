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
SECRET_RECOVERY_PHRASE = 'owner asthma provide hover vessel crisp sweet have response abandon dice pledge'
# NEW_PASSWORD = 'YOUR NEW_PASSWORD'
NEW_PASSWORD = 'alsk1369854Qpxm'
# COLLECTION_CREATE_PATH = 'https://opensea.io/collection/school-little-dinosaur/assets/create'
COLLECTION_CREATE_PATH = 'https://opensea.io/collection/school-little-dinosaur/assets/create'

IMAGES_DIR_PATH = "C:\py_wk\opensea_nft_uploader\png_data"
IMAGES_UPLOADED_DIR_PATH = "C:\py_wk\opensea_nft_uploader\png_data/uploaded"

image_name_title = 'School Little Dinosaur #'

driver = get_driver()
first_url = 'https://opensea.io/collection/school-little-dinosaur?search[query]='
last_url = '&search[sortAscending]=true&search[sortBy]=CREATED_DATE'

def check_item_exist(search_number):
    image = search_number + '.png'
    image_name = image_name_title + search_number

    driver.get(first_url + search_number + last_url)
    driver.execute_script('document.documentElement.scrollTop=800')

    if (driver.title == 'opensea.io | 504: Gateway time-out'):
        print(driver.title)
        check_item_exist(search_number)
        return
    try:
        item_list = driver.find_elements(By.XPATH, '//div[text()="' + image_name + '"]')
        # print(len(item_list))
        item_len = len(item_list)
        # result_array[i] += len(item_list)
        if (item_len == 0):
            print(image_name + ': ' + str(item_len))
            Path(IMAGES_DIR_PATH + '/uploaded/' + image).rename(IMAGES_DIR_PATH + '/' + image)
        elif (item_len >= 2):
            print(image_name + ': ' + str(item_len))
        else:
            print(image)
            Path(IMAGES_DIR_PATH + '/uploaded/' + image).rename(IMAGES_DIR_PATH + '\checked_uploaded/' + image)
    except:
        print('out ' + image_name + ': ' + str(item_len) + '............')
        print(driver.title + '\n')

if __name__ == '__main__':
    width = 546*3
    height = 735
    driver.set_window_size(width=width, height=height)
    meta_mask_navigate(driver, SECRET_RECOVERY_PHRASE, NEW_PASSWORD)
    login_opensea(driver, COLLECTION_CREATE_PATH)

    # sort image list
    sorted_image_list = []
    for image in os.listdir(IMAGES_UPLOADED_DIR_PATH):
        count = 0
        if (image.endswith(".png")):
            sorted_image_list.append(int(image.replace(".png", "")))
            count += 1
    sorted_image_list.sort()
    # result_array = [0]*len(sorted_image_list)
    for i in range (len(sorted_image_list)):
            search_number = str(sorted_image_list[i])


            check_item_exist(search_number)



