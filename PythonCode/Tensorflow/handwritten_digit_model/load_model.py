import os
import tensorflow as tf
from tensorflow import keras
from tensorflow.keras import layers, optimizers, datasets
model_weights_path = "model_home/handwritten_digit_model.ckpt"

(x, y), (x_val, y_val) = datasets.mnist.load_data()
x = tf.convert_to_tensor(x, dtype=tf.float32) / 255.
y = tf.convert_to_tensor(y, dtype=tf.int32)
y = tf.one_hot(y, depth=10)
print(x.shape, y.shape)
train_dataset = tf.data.Dataset.from_tensor_slices((x, y))
train_dataset = train_dataset.batch(200)

model = keras.Sequential([
    layers.Dense(512, activation='relu'),
    layers.Dense(256, activation='relu'),
    layers.Dense(128, activation='relu'),
    layers.Dense(10),
])

def get_loss():
    # Step4.loop
    for step, (x, y) in enumerate(train_dataset):
        with tf.GradientTape() as tape:
            # [b, 28, 28] => [b, 784]
            x = tf.reshape(x, (-1, 28 * 28))
            # Step1. compute output
            # [b, 748] => [b, 10]
            out = model(x)
            # Step2. compute loss
            loss = tf.reduce_sum(tf.square(out - y) / x.shape[0])

    return loss


if __name__ == '__main__':
    print('loading model...')
    model.load_weights(model_weights_path)
    print('model loss:', get_loss())