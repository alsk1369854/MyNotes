import tensorflow as tf
from tensorflow import keras
from tensorflow.keras import datasets, optimizers
import os

# 屏蔽系統紅字提示訊息，直保留 Error 訊息
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

# x: [60k, 28, 28]
# y: [60k]
(x, y), _ = datasets.mnist.load_data()

# x: [0~255] => [0~1.]
x = tf.convert_to_tensor(x, dtype=tf.float32) / 255
y = tf.convert_to_tensor(y, dtype=tf.int32)
print(x.shape, y.shape, x.dtype, y.dtype)

# Training data
train_db = tf.data.Dataset.from_tensor_slices((x, y)).batch(128)
train_iter = iter(train_db)
sample = next(train_iter)
print('batch:', sample[0].shape, sample[1].shape)

# Fully Connected Layer
# [b, 784] => [b, 256] => [b, 128] => [b, 10]
# [dim_in, dim_out], [dim_out]
# stddev: 降低方差防止梯度爆炸
w1 = tf.Variable(tf.random.truncated_normal([784, 256], stddev=0.1))
b1 = tf.Variable(tf.zeros([256]))
w2 = tf.Variable(tf.random.truncated_normal([256, 128], stddev=0.1))
b2 = tf.Variable(tf.zeros([128]))
w3 = tf.Variable(tf.random.truncated_normal([128, 10], stddev=0.1))
b3 = tf.Variable(tf.zeros([10]))

lr = 1e-3  # 0.003
# Start Training
for epoch in range(10):  # iterate db for 10
    for step, (x, y) in enumerate(train_db):  # for every batch
        # x: [128, 28, 28]
        # y: [128]

        # [b, 28, 28] => [b, 28*28]
        x = tf.reshape(x, [-1, 28 * 28])

        with tf.GradientTape() as tape:
            # x: [b, 28 * 28]
            # h1 = x@w1 + b1
            # [b, 748]@[784, 256] + [256] => [b, 256] + [256] => [b,256] + [b, 256]
            h1 = x @ w1 + tf.broadcast_to(b1, [x.shape[0], 256])
            h1 = tf.nn.relu(h1)
            # [b, 256] => [b, 128]
            h2 = h1 @ w2 + b2
            h2 = tf.nn.relu(h2)
            # [b, 128] => [b, 10]
            out = h2 @ w3 + b3

            # compute loss
            # out: [b, 10]
            # y: [b] => [b, 10]
            y_onehot = tf.one_hot(y, depth=10)

            # mse = mean(sum(y-out)^2)
            # [b, 10]
            loss = tf.square(y_onehot - out)
            # mean: scalar
            loss = tf.reduce_mean(loss)

        # compute gradients
        grads = tape.gradient(loss, [w1, b1, w2, b2, w3, b3])
        # w1 = w1 - lr * w1_grad
        w1.assign_sub(lr * grads[0])
        b1.assign_sub(lr * grads[1])
        w2.assign_sub(lr * grads[2])
        b2.assign_sub(lr * grads[3])
        w3.assign_sub(lr * grads[4])
        b3.assign_sub(lr * grads[5])

        if step % 100 == 0:
            print(epoch, step, 'loss', float(loss))

        # print(step, 'loss', float(loss))
