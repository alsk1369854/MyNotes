import pandas as pd
import cv2
import numpy as np
import face_recognition
import os
from datetime import datetime
from PIL import Image, ImageDraw, ImageFont
# from PIL import ImageGrab

# path = r'C:\Users\ChiaMing\Desktop\SoC_Code\ESP32\ESP32 Cam\Face Detector\ATTENDANCE\image_folder'
path = os.path.join(os.getcwd(),'image_folder')

url='http://192.168.1.100/1024x768.jpg'
## ''' cam.bmp / cam-lo.jpg / cam-hi.jpg / cam.mjpeg '''

# if 'Attendance.csv' in os.listdir(os.path.join(os.getcwd(),'attendace')):
if 'Attendance.csv' in os.listdir(os.getcwd()):
    print("there iss..")
    os.remove("Attendance.csv")
# else:
df=pd.DataFrame(list())
df.to_csv("Attendance.csv")
    
def cv_imread(file_path):
    cv_img = cv2.imdecode(np.fromfile(file_path,dtype=np.uint8),-1)
    return cv_img

def brightness_value(img):
    hsv_img = cv2.cvtColor(img, cv2.COLOR_RGB2HSV)

    v_values = np.sum(hsv_img[:, :, 2])
    area = img.shape[0] * img.shape[1]

    avg_brightness = v_values/area

    return avg_brightness

def estimate_label(img, threshold):
    avg_brightness = brightness_value(img)

    predicted_label = 0
    threshold = threshold

    if avg_brightness > threshold:
        predicted_label = 1
    
    return predicted_label, avg_brightness

images = []
classNames = []
myList = os.listdir(path)
print(myList)
for cl in myList:
    curImg = cv2.imread(f'{path}/{cl}')
    # curImg = cv_imread(f'{path}/{cl}')
    images.append(curImg)
    classNames.append(os.path.splitext(cl)[0])
    # classNames.append(cl.split('.')[0])
print(classNames)


def findEncodings(images):
    encodeList = []

    for img in images:
        img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        encode = face_recognition.face_encodings(img)[0]
        encodeList.append(encode)
    return encodeList


def markAttendance(name):
    # with open('Attendance.csv', 'r+') as f:
    with open('Attendance.csv', 'r+', encoding="utf-8") as f:
        myDataList = f.readlines()

        nameList = []
        for line in myDataList:
            entry = line.split(',')
            nameList.append(entry[0])
            if name not in nameList:
                now = datetime.now()
                dtString = now.strftime('%H:%M:%S')
                f.writelines(f'\n{name},{dtString}')

#### FOR CAPTURING SCREEN RATHER THAN WEBCAM
# def captureScreen(bbox=(300,300,690+300,530+300)):
#     capScr = np.array(ImageGrab.grab(bbox))
#     capScr = cv2.cvtColor(capScr, cv2.COLOR_RGB2BGR)
#     return capScr

encodeListKnown = findEncodings(images)
print('Encoding Complete')

cap = cv2.VideoCapture(0)

while True:
    success, img = cap.read()
# img = captureScreen()
    imgS = cv2.resize(img, (0, 0), None, 0.25, 0.25)
    imgS = cv2.cvtColor(imgS, cv2.COLOR_BGR2RGB)
    # img = img.astype(np.float32) / 255 # 色值轉為浮點數

    facesCurFrame = face_recognition.face_locations(imgS)
    encodesCurFrame = face_recognition.face_encodings(imgS, facesCurFrame)

    for encodeFace, faceLoc in zip(encodesCurFrame, facesCurFrame):
        matches = face_recognition.compare_faces(encodeListKnown, encodeFace)
        faceDis = face_recognition.face_distance(encodeListKnown, encodeFace)
# print(faceDis)Q
        matchIndex = np.argmin(faceDis)

        if matches[matchIndex]:
            name = classNames[matchIndex].upper()
# print(name)
            y1, x2, y2, x1 = faceLoc
            y1, x2, y2, x1 = y1 * 4, x2 * 4, y2 * 4, x1 * 4
            cv2.rectangle(img, (x1, y1), (x2, y2), (0, 255, 0), 2)
            cv2.rectangle(img, (x1, y2 - 35), (x2, y2), (0, 255, 0), cv2.FILLED)
            cv2.putText(img, name, (x1 + 6, y2 - 6), cv2.FONT_HERSHEY_COMPLEX, 1, (255, 255, 255), 2)
            markAttendance(name)

    ## 檢測圖像亮度 threshold: 120|100|80
    predicted_label, avg_brightness = estimate_label(img, 100)
    print(predicted_label, avg_brightness)
    ## 如果太暗轉換灰階影像提亮
    if(predicted_label<1):
        gray_img = cv2.cvtColor(img, cv2.COLOR_RGB2GRAY)
        clahe = cv2.createCLAHE(clipLimit=2.0, tileGridSize=(8, 8))
        img = clahe.apply(gray_img)

        # img = cv2.equalizeHist(gray_img) # 只能傳入灰度圖
        # img = cv2.cvtColor(img, cv2.COLOR_RGB2GRAY)

    cv2.imshow('Webcam', img)
    key=cv2.waitKey(5)
    if key==ord('q'):
        break
cv2.destroyAllWindows()
# cv2.imread
