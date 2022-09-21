import dlib
import cv2
import imutils
from matplotlib import pyplot as plt

# http://dlib.net/files/shape_predictor_68_face_landmarks.dat.bz2 # DOWNLOAD LINK
# http://dlib.net/files/dlib_face_recognition_resnet_model_v1.dat.bz2
datFile = r"../content/shape_predictor_68_face_landmarks.dat"
img_path = r'../image_folder/Chia Ming.jpg'

# Dlib 的人臉偵測器
detector = dlib.get_frontal_face_detector()
# dlib 臉部特徵界標
sp = dlib.shape_predictor(datFile)

img = cv2.imread(img_path)
img = imutils.resize(img, height=748, width=748)

# 偵測人臉，輸出分數
face_rects, scores, idx = detector.run(img, 1)

for i, face in enumerate(face_rects):
    x1 = face.left()
    y1 = face.top()
    x2 = face.right()
    y2 = face.bottom()
    text = 'scores: ' + str(scores[i]) + ', idx:' + str(idx[i])

    cv2.rectangle(img, (x1, y1), (x2, y2), (0, 255, 255), 4, cv2.LINE_AA)

    # 標示分數
    cv2.putText(img, text, (x1, y1), cv2.FONT_HERSHEY_DUPLEX,
                0.7, (255, 255, 255), 1, cv2.LINE_AA)

cv2.imshow("Face Detection", img)

cv2.waitKey(0)
cv2.destroyAllWindows()
