# import tensorflow as tf


# # 創建一個輸出為 10 的全連接層
# # 方法一
# # =============================
# net = tf.keras.layers.Dense(10)

# # input net 
# data = tf.random.normal([1, 20])
# out = net(data) # [20] => [10]
# print(out.shape) # (1, 10)

# print(net.kernel.shape) # (20, 10)

# # 方法二
# # =============================
# net = tf.keras.layers.Dense(10)

# # 非必要, 實際在預測時會自動機算生成輸入層維度
# net.build(input_shape=(None, 20))

# print(net.kernel.shape) # (20, 10)


import tensorflow as tf
from tensorflow import keras

# input data
data = tf.random.normal([2, 256])

# [b, 256] => [b, 128] => [b, 64] => [b, 10]
model = tf.keras.Sequential([
    tf.keras.layers.Dense(128, activation='relu'),
    tf.keras.layers.Dense(64, activation='relu'),
    tf.keras.layers.Dense(10),
])

# 初始化 model
# 方法一
# model.build(input_shape=[None, 256])
# 方法二
model(data)

# 打印 model 訊息
model.summary()
# _________________________________________________________________
#  Layer (type)                Output Shape              Param #
# =================================================================
#  dense (Dense)               (2, 128)                  32896

#  dense_1 (Dense)             (2, 64)                   8256

#  dense_2 (Dense)             (2, 10)                   650

# =================================================================
# Total params: 41,802
# Trainable params: 41,802
# Non-trainable params: 0
# _________________________________________________________________

# 打印 model 層訊息
for p in model.trainable_variables:
    print(p.name, p.shape)
# dense/kernel:0 (256, 128)
# dense/bias:0 (128,)
# dense_1/kernel:0 (128, 64)
# dense_1/bias:0 (64,)
# dense_2/kernel:0 (64, 10)
# dense_2/bias:0 (10,)



