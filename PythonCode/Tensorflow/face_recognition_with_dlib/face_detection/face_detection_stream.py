import dlib
import cv2
import imutils
from matplotlib import pyplot as plt
import numpy as np

# http://dlib.net/files/shape_predictor_68_face_landmarks.dat.bz2 # DOWNLOAD LINK

datFile = r"../content/shape_predictor_68_face_landmarks.dat"
img_path = '../image_folder/Chia Ming.jpg'
cap_width = 400
cap_height = 800

# Dlib 的人臉偵測器
detector = dlib.get_frontal_face_detector()
# dlib 臉部特徵界標
sp = dlib.shape_predictor(datFile)

# 開啟影片檔案
cap = cv2.VideoCapture(0)
cap.set(cv2.CAP_PROP_FRAME_WIDTH, cap_width)
cap.set(cv2.CAP_PROP_FRAME_HEIGHT, cap_height)

# 以迴圈從影片檔案讀取影格，並顯示出來
while (cap.isOpened()):
    ret, frame = cap.read()

    # 偵測人臉
    face_rects, scores, idx = detector.run(frame, 0)

    # 取出所有偵測的結果
    for i, d in enumerate(face_rects):
        x1 = d.left()
        y1 = d.top()
        x2 = d.right()
        y2 = d.bottom()
        text = 'scores: ' + str(scores[i]) + ', idx:' + str(idx[i])

        # 以方框標示偵測的人臉
        cv2.rectangle(frame, (x1, y1), (x2, y2), (0, 255, 255), 4, cv2.LINE_AA)

        # 標示分數
        cv2.putText(frame, text, (x1, y1), cv2.FONT_HERSHEY_DUPLEX,
                    0.7, (255, 255, 255), 1, cv2.LINE_AA)

    # 顯示結果
    cv2.imshow("Face Detection", frame)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()
