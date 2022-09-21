import numpy as np
import face_recognition as fr
import cv2
import os

image_folder_path = r'../image_folder'


def encoding_images(images, person_names):
    encoded_images = []
    for img, person_name in zip(images, person_names):
        img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        print(person_name + '...')
        # model= 'large' | 'hog'
        encode = fr.face_encodings(img, num_jitters=10, model='large')[0]
        encoded_images.append(encode)
    return encoded_images


def save_known_faces(person_names, encoded_images):
    with open('known_faces.npy', 'wb') as f:
        np.save(f, person_names)
        np.save(f, encoded_images)


if __name__ == '__main__':
    images = []
    person_names = []
    image_list = os.listdir(image_folder_path)
    print(len(image_list), image_list)

    for image_name in image_list:
        curImg = cv2.imread(f'{image_folder_path}/{image_name}')
        images.append(curImg)
        person_names.append(os.path.splitext(image_name)[0])

    print('encoding...')
    encoded_images = encoding_images(images, person_names)
    save_known_faces(person_names, encoded_images)
    print('\nDone')
