import face_recognition
import numpy as np
import face_recognition as fr
import cv2

known_faces_path = r'known_faces.npy'
image_width = 600
image_height = 800

# 開啟影片檔案
cap = cv2.VideoCapture(0)


def load_known_faces():
    with open(known_faces_path, 'rb') as f:
        person_names = np.load(f)
        encoded_images = np.load(f)
    return (person_names, encoded_images)


if __name__ == '__main__':
    # 讀取已知的人臉
    (person_names, known_faces) = load_known_faces()

    # start detection...
    while True:
        # 獲取鏡頭畫面
        ret, frame = cap.read()
        # 水平反轉影像
        frame = cv2.flip(frame, 1)
        # frame = cv2.resize(frame, (image_width, image_height))

        # 準備辨識人臉影像
        image = cv2.resize(frame, (0, 0), None, 0.25, 0.25)
        image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)

        # 擷取畫面中的人臉特徵
        face_location_list = face_recognition.face_locations(image)
        encoded_face_list = face_recognition.face_encodings(image, face_location_list)

        # 遍歷畫面中所找找到的臉部特徵
        for face_location, encoded_face in zip(face_location_list, encoded_face_list):
            y1, x2, y2, x1 = face_location
            y1, x2, y2, x1 = y1 * 4, x2 * 4, y2 * 4, x1 * 4
            matches = face_recognition.compare_faces(known_faces, encoded_face)
            face_distance = face_recognition.face_distance(known_faces, encoded_face)
            matchIndex = np.argmin(face_distance)

            if matches[matchIndex]:
                person_name = person_names[matchIndex]

                cv2.rectangle(frame, (x1, y1), (x2, y2), (0, 255, 0), 2)
                cv2.putText(frame, person_name, (x1 + 6, y2 - 6), cv2.FONT_HERSHEY_COMPLEX, 1, (255, 255, 255), 2)

            else:
                cv2.rectangle(frame, (x1, y1), (x2, y2), (0, 255, 255), 2)



        # Display the resulting frame
        cv2.imshow('frame', frame)
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break

    # When everything done, release the capture
    cap.release()
    cv2.destroyAllWindows()
